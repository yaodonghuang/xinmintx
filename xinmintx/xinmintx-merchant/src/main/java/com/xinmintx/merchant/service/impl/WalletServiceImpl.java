package com.xinmintx.merchant.service.impl;

import com.google.gson.Gson;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.mapper.PoboNotifyMapper;
import com.xinmintx.merchant.model.*;
import com.xinmintx.merchant.service.WalletService;
import com.xinmintx.merchant.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName:.WalletServiceImpl
 * @author:chf
 * @Date:2020/2/17：13:10
 * @developerKits： win 10     jdk1.8
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private PoboNotifyMapper poboNotifyMapper;

    @Resource
    private UrlConfig urlConfig;

    @Override
    public Merchant queryBalance(String token) {
        return merchantMapper.selectById(token);
    }

    @Override
    public List<PoboNotify> queryWithdrawDeposit(String token) {
        Merchant merchant = merchantMapper.queryByToken(token);
        List<CommunityCashInfo> communityCashInfo = merchantMapper.queryByRequest(merchant.getId());
        if (merchant == null) {
            return null;
        }
        if (communityCashInfo == null) {
            return null;
        }
        List<PoboNotify> poboNotifyList = new ArrayList<>();
        for (CommunityCashInfo cashInfo : communityCashInfo) {
            PoboNotify poboNotify = poboNotifyMapper.selectList(cashInfo.getRequestSn());
            if (poboNotify != null) {
                poboNotifyList.add(poboNotify);
            }
        }
        Collections.sort(poboNotifyList);
        return poboNotifyList;
    }

    @Override
    public ResultCode factoryGetMoney(Merchant merchant) {
        ResultCode rc = new ResultCode();
        // 输入的提现金额
        BigDecimal inputCashAmount = StringUtils.isNotEmpty(merchant.getInputCashAmount()) ? new BigDecimal(merchant.getInputCashAmount()) : BigDecimal.ZERO;
        // 根据工厂id查询出工厂可以使用的提现金额
        Merchant getFc = merchantMapper.queryByToken(merchant.getToken());
        if (getFc == null) {
            rc.setCode(500);
            rc.setMsg("商户不存在！");
            return rc;
        }
        Integer bankCardId = merchant.getBankCardId();
        if (bankCardId != null) {
            MerchantBankCard mbc = merchantMapper.getBankCardInfoById(bankCardId);
            if (mbc != null) {
                getFc.setBankCard(mbc.getCardNum());
                getFc.setName(mbc.getName());
            } else {
                rc.setCode(500);
                rc.setMsg("请绑定银行卡！");
                return rc;
            }
        }
        // 获取提现起提金额
        BigDecimal amountRaised = getFc.getAmountRaised();
        amountRaised = amountRaised == null ? BigDecimal.ZERO : amountRaised;
        // 判断提现金额是否小于起提金额
        if (inputCashAmount.compareTo(amountRaised) == -1) {
            // 小于提现金额则报错：起提金额为：xx元
            rc.setCode(500);
            rc.setMsg("起提金额为：" + amountRaised + "元！");
            return rc;
        }
        // 可用金额扣除手续费(1元)之后作比较
        BigDecimal cashAmount = getFc.getCashAmount().subtract(new BigDecimal("1"));
        // 输入的金额小于可用金额才可以提现
        if (cashAmount != null && cashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(cashAmount) <= 0) {
            Map<String, String> paramMap = new HashMap<>();
            String string = dealCash(getFc, inputCashAmount, paramMap);// 处理提现逻辑
            rc = new Gson().fromJson(string, ResultCode.class);
            if (rc != null && rc.getCode() == 200) {
                // 对商户余额进行处理
                BigDecimal money = inputCashAmount.add(new BigDecimal("1"));
                merchantMapper.updateMerchantMoney(money, paramMap.get("requestSN"));
                // 保存merchant可用金额扣除明细
                dealMerchantCashInfo(money, paramMap.get("requestSN"));
            }
        } else {
            rc.setCode(500);
            rc.setMsg("可提现金额不足(大于1元才能提现)");
        }
        return rc;
    }

    @Override
    public List<MerchantAmountLog> queryTransaction(String token, Integer type) {
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant == null) {
            return null;
        }
        List<MerchantAmountLog> memberAmountLog = merchantMapper.queryTransaction(merchant.getId(), type);
        if (memberAmountLog == null || memberAmountLog.size() < 0) {
            return null;
        }
        return memberAmountLog;
    }

    // 处理提现逻辑
    private String dealCash(Merchant getFc, BigDecimal inputCashAmount, Map<String, String> paramMap) {
        paramMap.put("requestSN", UUID.randomUUID().toString().replace("-", ""));
        // "http://factory-api.xinmintx.cn/merchants/poboNotify"
        paramMap.put("notifyUrl", urlConfig.getPoboNotify());
        paramMap.put("txnAmt", String.valueOf(inputCashAmount));
        paramMap.put("recvAccName", getFc.getName());
        paramMap.put("acctNo", getFc.getBankCard());
        // 厂家提现表保存相关信息
        merchantMapper.insertFactoryCashInfo(getFc.getId(), paramMap.get("requestSN"));
        // "https://factory-api.xinmintx.cn/merchants/pobo"
        String url = urlConfig.getPobo();
        return HttpClientUtil.doPost(url, paramMap);// 调用代付接口提现;
    }

    // 保存商户可用金额日志
    private void dealMerchantCashInfo(BigDecimal money, String requestSN) {
        if (money.compareTo(BigDecimal.ZERO) == 1) {
            // 查询商户id
            Merchant m = merchantMapper.getMerchantInfo(requestSN);
            if (m != null) {
                MerchantAmountLog mal = new MerchantAmountLog();
                mal.setMerchantId(Long.valueOf(m.getId()));
                mal.setType("2");
                mal.setPrice(money.multiply(new BigDecimal("-1")));
                mal.setBalance(m.getCashAmount());
                mal.setRemark("商户提现");
                merchantMapper.insertMerchantLogs(mal);
            }
        }
    }
}
