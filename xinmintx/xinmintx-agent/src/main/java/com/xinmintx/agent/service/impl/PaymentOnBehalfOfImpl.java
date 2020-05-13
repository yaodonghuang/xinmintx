package com.xinmintx.agent.service.impl;
import java.util.Date;


import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.common.parity.HexConver;
import com.xinmintx.agent.common.parity.MerchantMD5;
import com.xinmintx.agent.common.parity.StringUtil;
import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.PaymentOnBehalfOf;
import com.xinmintx.agent.util.DateUtils;
import com.xinmintx.agent.util.XMLConvertor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class PaymentOnBehalfOfImpl implements PaymentOnBehalfOf {
    @Autowired
    private PoboNotifyMapper poboNotifyMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserRequestsnMapper userRequestsnMapper;

    @Autowired
    private DepositSpecificationMapper depositSpecificationMapper;

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;
    /**
     * 代付接口
     *
     * @param paramMap
     */
    @Override
    public ResultCode paymentOnBehalfOf(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 调用扣费方法
        return pobo(paramMap);
    }

    /**
     * 代付回调接口
     *
     * @param pn
     * @return
     */
    @Override
    @Transactional
    public ResultCode paymentOnBehalfOfNotify(PoboNotify pn) {
        log.info("回调========");
        ResultCode rc = new ResultCode();
        rc.setCode(200);
        if (pn != null) {
            rc.setData(pn);
            // 保存代付回调信息
            poboNotifyMapper.insert(pn);
            //支付成功
            if("01".equals(pn.getOrderStatus())){
                UserRequestsnExample userRequestsnExample = new UserRequestsnExample();
                UserRequestsnExample.Criteria criteria1 = userRequestsnExample.createCriteria();
                criteria1.andRequestsnEqualTo(pn.getRequestSN());
                List<UserRequestsn> userRequestsns = userRequestsnMapper.selectByExample(userRequestsnExample);
                UserRequestsn user = userRequestsns.get(0);

                //惠商手续费
                DepositSpecificationExample depositSpecificationExample = new DepositSpecificationExample();
                List<DepositSpecification> depositSpecifications = depositSpecificationMapper.selectByExample(depositSpecificationExample);
                DepositSpecification depositSpecification = depositSpecifications.get(0);
                //  v 惠商手续费 /100 元为单位
                double v = depositSpecification.getServiceCharge() * (Double.parseDouble(pn.getTxnAmt()) / 100);
                if(v < 1){
                    v = 1;
                }
                //提现金额+ 惠商手续费
                double money = (Double.parseDouble(pn.getTxnAmt())/100)+v;

                UserAccountExample userAccountExample = new UserAccountExample();
                UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
                criteria.andUserIdEqualTo(user.getUserId());
                List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
                UserAccount userAccount1 = userAccounts.get(0);
                userAccount1.setMoney(userAccount1.getMoney()-money);
                //余额更改
                userAccountMapper.updateByPrimaryKeySelective(userAccount1);
                //记录
                UserAccountRecord userAccountRecord = new UserAccountRecord();
                userAccountRecord.setUserId(user.getUserId());
                userAccountRecord.setUserAccountId(userAccount1.getId());
                userAccountRecord.setMoneyChange(new BigDecimal(money));
                userAccountRecord.setCreateTime(DateUtils.getNowDate());
                userAccountRecord.setChangPrice(new BigDecimal(v));
                userAccountRecord.setDescription("提现");
                userAccountRecordMapper.insertSelective(userAccountRecord);
            }
        }
        return rc;
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("merchantId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("signType", "MD5");// 加密类型
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/MerchantSinglePay.do");// 请求地址
    }

    // 调用代付接口
    private ResultCode pobo(Map<String, String> paramMap) {
        ResultCode rc = new ResultCode();
        try {
            HttpClient httpclient = new HttpClient();
            // post赋值
            PostMethod post = initPostValue(paramMap);
            // 请求
            int httpcode = httpclient.executeMethod(post);
            System.out.println("httpcode---->" + httpcode);
            String info = new String(post.getResponseBody(), StandardCharsets.UTF_8);
            System.out.println("返回信息---->" + info);
            // 处理返回结果
            XMLConvertor.dealResult(info, rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        paramMap.remove("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String orderDate = sdf.format(new Date());
        paramMap.put("orderDate", orderDate);// 订单日期
        if(StringUtils.isNotEmpty(paramMap.get("txnAmt"))){
            paramMap.put("txnAmt", StringUtil.getPrettyNumber(new BigDecimal(paramMap.get("txnAmt"))));// 订单金额
        }
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        paramMap.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(v)) {
                if (XMLConvertor.isContainChinese(v)) {
                    post.addParameter(k, HexConver.Str2Hex(v));
                } else {
                    post.addParameter(k, v);
                }
            }
        });
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        String md5Key = paramMap.get("md5Key");
        paramMap.remove("md5Key");
        paramMap.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(v)) {
                if (XMLConvertor.isContainChinese(v)) {
                    map.put(k, HexConver.Str2Hex(v));
                } else {
                    map.put(k, v);
                }
            }
        });
        Map<String, String> params = StringUtil.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        StringUtil.buildPayParams(buf, params, false);
        String payStr = buf.toString();

        String signature;
        System.out.println("payStr:" + payStr);
        System.out.println("source+key:==>" + payStr + md5Key);
        signature = MerchantMD5.MD5(payStr + md5Key);
        System.out.println("signature:" + signature);
        String str = payStr + "&signature=" + signature;
        System.out.println("发送--加密前参数==" + str);
        return signature;
    }

}
