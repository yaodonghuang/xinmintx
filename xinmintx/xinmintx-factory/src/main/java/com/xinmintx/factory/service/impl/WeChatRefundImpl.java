package com.xinmintx.factory.service.impl;

import com.xinmintx.common.parity.MerchantMD5;
import com.xinmintx.common.parity.StringUtil;
import com.xinmintx.factory.service.IWeChatRefund;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信退款申请对接接口
 */
@Service
public class WeChatRefundImpl implements IWeChatRefund {
    /**
     *  退款接口
     * @param paramMap
     *        参数:
     *        1.orderId(原支付成功订单号)
     *        2.refundOrdNo(退款订单号)
     *        3.rfAmt(以人民币分为单位)
     *        4.rfSake(退款理由)
     *        5.notifyUrl(针对该交易的交易状态同步通知接收URL)
     */
    @Override
    public void doRefund(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 调用扣费方法
        refund(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("merchantId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("signType", "MD5");// 加密类型
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/MerchantmerchantRefund.do");// 请求地址
    }

    // 调用支付接口
    private void refund(Map<String, String> paramMap) {
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
            dealResult(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String merchantId = paramMap.get("merchantId");// 支付平台统一分配的商户编号
        String orderId = paramMap.get("orderId");// 商户平台生成的用于标识该笔订单的唯一号码
        String refundOrdNo = paramMap.get("refundOrdNo"); // 退款订单号
        String rfAmt = paramMap.get("rfAmt");// 以人民币分为单位
        String rfSake = paramMap.get("rfSake"); // 退款理由
        String notifyUrl = paramMap.get("notifyUrl");    //针对该交易的交易状态同步通知接收URL。
        String signType = paramMap.get("signType");// 加签方式MD5
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("merchantId", merchantId);
        post.addParameter("orderId", orderId);
        post.addParameter("refundOrdNo", refundOrdNo);
        post.addParameter("rfAmt", rfAmt);
        post.addParameter("rfSake", rfSake);
        post.addParameter("notifyUrl", notifyUrl);
        post.addParameter("signType", signType);
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("merchantId", paramMap.get("merchantId"));
        map.put("orderId", paramMap.get("orderId"));
        map.put("refundOrdNo", paramMap.get("refundOrdNo"));
        map.put("rfAmt", paramMap.get("rfAmt"));
        map.put("rfSake", paramMap.get("rfSake"));
        map.put("notifyUrl", paramMap.get("notifyUrl"));
        map.put("signType", paramMap.get("signType"));

        Map<String, String> params = StringUtil.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        StringUtil.buildPayParams(buf, params, false);
        String payStr = buf.toString();

        String signature;
        System.out.println("payStr:" + payStr);
        String md5Key = paramMap.get("md5Key");
        System.out.println("source+key:==>" + payStr + md5Key);
        signature = MerchantMD5.MD5(payStr + md5Key);
        System.out.println("signature:" + signature);
        String str = payStr + "&signature=" + signature;
        System.out.println("发送--加密前参数==" + str);
        return signature;
    }

    // 处理返回结果
    private void dealResult(String info) {
        if (info != null && !"".equals(info)) {
            try {
                Document doc = DocumentHelper.parseText(info);
                Element root = doc.getRootElement();
                if (root.attributeValue("retCode").equals("0001")) {
                    System.out.println("success");
                    System.out.println(root.attributeValue("payInfo"));
                } else {
                    System.out.println("fail");
                }
                System.out.println(root.attributeValue("retMsg"));
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }
        }
    }
}
