package com.xinmintx.system.service.impl;

import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.MerchantMD5;
import com.xinmintx.common.parity.StringUtil;
import com.xinmintx.system.service.IMerchantWxPublicPay;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付对接
 */
@Service
public class MerchantWxPublicPayImpl implements IMerchantWxPublicPay {

    /**
     * 微信公众号和小程序支付扣费方法
     *
     * @param paramMap 參數paramMap
     */
    @Override
    public void weChatPay(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 调用扣费方法
        pay(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("merchantId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("subChnMerno", "332084536");// 渠道子商户
        paramMap.put("prodCode", "CP00000111");// 商品购买产品号(统一分配)
        paramMap.put("appid", "wxfae6eed7965225be");// 商户app对应的微信开发平台移动应用APPID
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("signType", "MD5");// 加密类型
        paramMap.put("postUrl", "https://www.56zhifu.com/user/MerchantWxPublicPay.do");// 请求地址
    }

    // 调用支付接口
    private void pay(Map<String, String> paramMap) {
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

    // 获取当前日期
    private static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(calendar.getTime());
    }

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String merchantId = paramMap.get("merchantId");// 支付平台统一分配的商户编号
        String subChnMerno = paramMap.get("subChnMerno"); // 渠道子商户
        String prodCode = paramMap.get("prodCode");// 商品购买产品号(统一分配)
        String orderId = paramMap.get("orderId");// 商户平台生成的用于标识该笔订单的唯一号码
        String orderAmount = paramMap.get("orderAmount"); // 订单金额 以人民币分为单位
        String orderDate = getCurrentDateTime(); // 订单日期
        paramMap.put("orderDate", orderDate);
        String openid = paramMap.get("openid"); // 用户id
        String appid = paramMap.get("appid");    //商户app对应的微信开发平台移动应用APPID
        String retUrl = paramMap.get("retUrl");// 异步通知URL,controller的URL
        String prdName = paramMap.get("prdName");// 商品名称
        String prdDesc = paramMap.get("prdDesc");// 商品描述
        String signType = paramMap.get("signType");// 加签方式MD5
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("merchantId", merchantId);
        post.addParameter("version", version);
        post.addParameter("subChnMerno", subChnMerno);
        post.addParameter("prodCode", prodCode);
        post.addParameter("orderId", orderId);
        post.addParameter("orderAmount", orderAmount);
        post.addParameter("orderDate", orderDate);
        post.addParameter("appid", appid);
        post.addParameter("retUrl", retUrl);
        post.addParameter("prdName", HexConver.Str2Hex(prdName));
        post.addParameter("prdDesc", HexConver.Str2Hex(prdDesc));
        post.addParameter("signType", signType);
        post.addParameter("openid", openid);
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("merchantId", paramMap.get("merchantId"));
        map.put("version", paramMap.get("version"));
        map.put("subChnMerno", paramMap.get("subChnMerno"));
        map.put("prodCode", paramMap.get("prodCode"));
        map.put("orderId", paramMap.get("orderId"));
        map.put("orderAmount", paramMap.get("orderAmount"));
        map.put("orderDate", paramMap.get("orderDate"));
        map.put("appid", paramMap.get("appid"));
        map.put("retUrl", paramMap.get("retUrl"));
        map.put("prdName", HexConver.Str2Hex(paramMap.get("prdName")));
        map.put("prdDesc", HexConver.Str2Hex(paramMap.get("prdDesc")));
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
