package com.xinmintx.factory.service.impl;

import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.MerchantMD5;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.AmountLogMapper;
import com.xinmintx.factory.mapper.FactoryCashInfoMapper;
import com.xinmintx.factory.mapper.MemberMapper;
import com.xinmintx.factory.mapper.PoboNotifyMapper;
import com.xinmintx.factory.model.*;
import com.xinmintx.factory.service.HsPaymentOnBehalfOf;
import com.xinmintx.factory.util.StringUtil;
import com.xinmintx.factory.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HsPaymentOnBehalfOfImpl implements HsPaymentOnBehalfOf {

    @Autowired
    private PoboNotifyMapper poboNotifyMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AmountLogMapper amountLogMapper;
    @Autowired
    private FactoryCashInfoMapper factoryCashInfoMapper;

    /**
     * 代付接口
     *
     * @param paramMap
     */
//    @Override
//    public ResultCode paymentOnBehalfOf(Map<String, String> paramMap) {
//        // 初始化Map
//        initMap(paramMap);
//        // 调用扣费方法
//        return pobo(paramMap);
//    }

    /**
     * 代付回调接口
     *
     * @param pn
     * @return
     */
    @Override
    public ResultCode hsPaymentOnBehalfOfNotify(PoboNotify pn) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
        if (pn != null) {
            rc.setData(pn);
            // 保存代付回调信息
            poboNotifyMapper.insert(pn);
            // 对用户余额进行处理
            dealFactoryCash(pn);
        }
        return rc;
    }

    private void dealFactoryCash(PoboNotify pn) {
        // 对余额进行处理
        if ("01".equals(pn.getOrderStatus())) {// 成功的情况,扣除金额
            BigDecimal xnAmt = StringUtils.isNotEmpty(pn.getTxnAmt()) ? new BigDecimal(pn.getTxnAmt()) : BigDecimal.ZERO;
            BigDecimal fee = StringUtils.isNotEmpty(pn.getFee()) ? new BigDecimal(pn.getFee()) : BigDecimal.ZERO;
            BigDecimal money = (xnAmt.add(fee)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
            memberMapper.updateFactoryMoney(money, pn.getRequestSN());
            MemberAmountLog memberAmountLog = new MemberAmountLog();
            ArrayList<MemberAmountLog> list = new ArrayList<>();
            //获取memberid
            FactoryCashInfoExample factoryCashInfoExample = new FactoryCashInfoExample();
            FactoryCashInfoExample.Criteria criteria = factoryCashInfoExample.createCriteria();
            criteria.andRequestSnEqualTo(pn.getRequestSN());
            List<FactoryCashInfo> factoryCashInfos = factoryCashInfoMapper.selectByExample(factoryCashInfoExample);
            Member member = memberMapper.selectByPrimaryKey(factoryCashInfos.get(0).getFactoryId().intValue());
            memberAmountLog.setMemberId(factoryCashInfos.get(0).getFactoryId());
            memberAmountLog.setCreateTime(new Date());
            memberAmountLog.setType("2");
            memberAmountLog.setPrice(money);
            memberAmountLog.setBalance(member.getCashAmount());
            memberAmountLog.setRemark("惠商用户提现");
            list.add(memberAmountLog);
            amountLogMapper.insertLogs(list);
        }
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
        if (StringUtils.isNotEmpty(paramMap.get("txnAmt"))) {
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
