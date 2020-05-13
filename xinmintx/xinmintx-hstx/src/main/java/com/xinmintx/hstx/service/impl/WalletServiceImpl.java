package com.xinmintx.hstx.service.impl;

import com.google.gson.Gson;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.FactoryCashInfoMapper;
import com.xinmintx.hstx.pojo.po.FactoryCashInfo;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.UpToTheAmountService;
import com.xinmintx.hstx.service.WalletService;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {


    @Autowired
    private UpToTheAmountService upToTheAmountService;
    @Autowired
    private FactoryCashInfoMapper factoryCashInfoMapper;
    @Autowired
    private PayConfig payConfig;


    @Override
    public ResultCode factoryGetMoney(MemberBankcard bankCard, String money, Member member) {
        ResultCode rc = new ResultCode();
        // 输入的提现金额
        BigDecimal inputCashAmount = StringUtils.isNotEmpty(money) ? new BigDecimal(money) : BigDecimal.ZERO;
        // 根据member id查询出惠商用户可以使用的提现金额
        // 获取提现起提金额
        BigDecimal amountRaised = upToTheAmountService.upToTheAmount().getDepositSum();

        amountRaised = amountRaised == null ? BigDecimal.ZERO : amountRaised;
        // 判断提现金额是否小于起提金额
        if (inputCashAmount.compareTo(amountRaised) == -1) {
            // 小于提现金额则报错：起提金额为：xx元
            rc.setCode(500);
            rc.setMsg("起提金额为：" + amountRaised + "元！");
            return rc;
        }
        // 可用金额扣除手续费(1元)之后作比较
        BigDecimal cashAmount = member.getCashAmount().subtract(new BigDecimal("1"));
        // 输入的金额小于可用金额才可以提现
        if (cashAmount != null && cashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(cashAmount) <= 0) {
            String string = dealCash(bankCard, inputCashAmount);// 处理提现逻辑

            rc = new Gson().fromJson(string, ResultCode.class);
        } else {
            rc.setCode(500);
            rc.setMsg("可提现金额不足(大于1元才能提现)");
        }
        return rc;
    }

    // 处理提现逻辑
    private String dealCash(MemberBankcard getFc, BigDecimal inputCashAmount) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("requestSN", UUID.randomUUID().toString().replace("-", ""));
        paramMap.put("notifyUrl", payConfig.getPoboNotifyUrl());
        paramMap.put("txnAmt", String.valueOf(inputCashAmount));
        paramMap.put("recvAccName", getFc.getName());
        paramMap.put("acctNo", getFc.getBankCard());
        //用户提现表保存相关信息
        FactoryCashInfo factoryCashInfo = new FactoryCashInfo();
        factoryCashInfo.setMemberId(getFc.getMemberId());
        factoryCashInfo.setRequestSn(paramMap.get("requestSN"));
        factoryCashInfo.setCreateDate(new Date());
        factoryCashInfoMapper.insert(factoryCashInfo);
//        HttpClientUtil.doPost(url,paramMap);// 调用代付接口提现
        return HttpClientUtil.doPost(payConfig.getPoboUrl(), paramMap);// 调用代付接口提现;
    }
}
