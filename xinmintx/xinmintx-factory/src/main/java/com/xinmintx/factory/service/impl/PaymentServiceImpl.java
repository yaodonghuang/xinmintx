package com.xinmintx.factory.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.StringUtil;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.service.PaymentService;
import com.xinmintx.factory.util.ConfigInfo;
import com.xinmintx.factory.util.PaymentUtils;
import com.xinmintx.factory.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class PaymentServiceImpl implements PaymentService {
    Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public ResultCode payment(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 生成post方法
        return pay(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("platformId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("signType", "CFCA");// 加密类型
        if (StringUtils.isNotEmpty(paramMap.get("flag"))) {
            paramMap.put("flag", paramMap.get("flag"));
        } else {
            paramMap.put("flag", "0");
        }

        paramMap.put("pwdFree", "0");//0：免密，1：非免密
        paramMap.put("commision", "0");//佣金费用 拆单必填，以人民币分为单位
        paramMap.put("chaFlag", "0");//渠道标识 0：渠道支付，1：钱包支付
        paramMap.put("allowance", "0");//补贴金额		拆单必填，
        paramMap.put("subChnMerno", "332084536");// 渠道子商户号
        if (StringUtils.isNotEmpty(paramMap.get("gatherId"))) {
            paramMap.put("gatherId", paramMap.get("gatherId"));
        } else {
            paramMap.put("gatherId", "00000000035514");// 如果有多个收款户用逗号隔开，如果cust1,cust2,cust3
        }
        if (StringUtils.isNotEmpty(paramMap.get("flag"))) {
            paramMap.put("flag", paramMap.get("flag"));
        } else {
            paramMap.put("flag", "0");
        }
        paramMap.put("freeAmt", "0");// 免密额度,pwdFree为0，必输  以人民币分为单位
        // 1：微信公众号，2：微信h5支付,3：微信app支付,4:微信扫码，6：微信刷卡，7：支付宝扫码，8：支付宝
        if (StringUtils.isNotEmpty(paramMap.get("wechatFlg"))) {// app支付
            paramMap.put("wechatFlg", paramMap.get("wechatFlg"));
            paramMap.put("appid", "wxcc8981ae24e3573c");//商户app对应的微信开发平台移动应用APPID
        } else {
            paramMap.put("wechatFlg", "1");
            paramMap.put("appid", "wxfae6eed7965225be");//商户app对应的微信开发平台移动应用APPID
        }
        paramMap.put("postUrl", "https://www.56zhifu.com/user/warrantrPay.do");
//        paramMap.put("retUrl","http://hsapi.xinmintx.cn/hs/wx/goodsPay");// 回调地址
    }

    // 调用支付接口
    private ResultCode pay(Map<String, String> paramMap) {
        ResultCode rc = new ResultCode();
        try {
            HttpClient httpclient = new HttpClient();
            // post赋值
            PostMethod post = initPostValue(paramMap);
            // 请求
            int httpcode = httpclient.executeMethod(post);
            System.out.println("httpcode---->" + httpcode);
            String info = new String(post.getResponseBody(), StandardCharsets.UTF_8);
            log.info(info);
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
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String platformId = paramMap.get("platformId");// 支付平台统一分配的商户编号
        String signType = paramMap.get("signType");// 加签方式MD5
        String gatherId = paramMap.get("gatherId");//收款账户编号
        String orderId = paramMap.get("orderId");//商品订单号
        String orderAmt = paramMap.get("orderAmt");//订单总金额
        String orderSplit = paramMap.get("orderSplit");//拆分订单金额
        String commision = paramMap.get("commision");//佣金费用 拆单必填，以人民币分为单位
        String chaFlag = paramMap.get("chaFlag");//渠道标识 0：渠道支付，1：钱包支付
        String retUrl = paramMap.get("retUrl");//异步通知URL  chaFlag为0时必输
        String allowance = paramMap.get("allowance");//补贴金额		拆单必填，
        String mession = paramMap.get("mession");//短信编号	用此字段验证是否为当前验证短信
        String smsCode = paramMap.get("smsCode");//短信验证码		短信验证码
        String flag = paramMap.get("flag");//0：要求确认收货完成支付，1：不需要确认收货完成支付
        String pwdFree = paramMap.get("pwdFree");//0：免密，1：非免密
        String freeAmt = paramMap.get("freeAmt");//免密额度
        String wechatFlg = paramMap.get("wechatFlg");//1：微信公众号，2：微信h5支付,3：微信app支付,4:微信扫码，6：微信刷卡，7：支付宝扫码，8：支付宝刷卡
        String openid = paramMap.get("openid");//获取用户的openid
        String appid = paramMap.get("appid");//商户app对应的微信开发平台移动应用APPID
        String prdDesc = paramMap.get("prdDesc");//商品描述
        String subChnMerno = paramMap.get("subChnMerno");// 渠道子商户号
        String attach = paramMap.get("attach");
        String bindId = paramMap.get("bindId");
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("platformId", platformId);
        post.addParameter("version", version);
        post.addParameter("signType", signType);
        post.addParameter("gatherId", gatherId);
        post.addParameter("orderId", orderId);
        post.addParameter("orderAmt", orderAmt);
        post.addParameter("chaFlag", chaFlag);
        post.addParameter("retUrl", retUrl);
        post.addParameter("allowance", allowance);
        post.addParameter("flag", flag);
        post.addParameter("pwdFree", pwdFree);
        post.addParameter("freeAmt", freeAmt);
        post.addParameter("wechatFlg", wechatFlg);
        post.addParameter("commision", commision);
        post.addParameter("openid", openid);
        post.addParameter("appid", appid);
        if(StringUtils.isNotEmpty(prdDesc)){
            post.addParameter("prdDesc", HexConver.Str2Hex(prdDesc));
        }
        post.addParameter("subChnMerno", subChnMerno);
        post.addParameter("signature", signature);
        if (StringUtils.isNotEmpty(attach)) {
            post.addParameter("attach", HexConver.Str2Hex(attach));
        }
        if (StringUtils.isNotEmpty(bindId)) {
            post.addParameter("bindId", bindId);
        }
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("platformId", paramMap.get("platformId"));
        map.put("version", paramMap.get("version"));
        map.put("signType", paramMap.get("signType"));
        map.put("gatherId", paramMap.get("gatherId"));
        map.put("orderId", paramMap.get("orderId"));
        map.put("orderAmt", paramMap.get("orderAmt"));
        map.put("chaFlag", paramMap.get("chaFlag"));
        map.put("retUrl", paramMap.get("retUrl"));
        map.put("allowance", paramMap.get("allowance"));
        map.put("flag", paramMap.get("flag"));
        map.put("pwdFree", paramMap.get("pwdFree"));
        map.put("freeAmt", paramMap.get("freeAmt"));
        map.put("commision", paramMap.get("commision"));
        map.put("openid", paramMap.get("openid"));
        map.put("appid", paramMap.get("appid"));
        if(StringUtils.isNotEmpty(paramMap.get("prdDesc"))){
            map.put("prdDesc", HexConver.Str2Hex(paramMap.get("prdDesc")));
        }
        map.put("wechatFlg", paramMap.get("wechatFlg"));
        map.put("subChnMerno", paramMap.get("subChnMerno"));
        if (paramMap.get("attach") != null) {
            map.put("attach", HexConver.Str2Hex(paramMap.get("attach")));
        }
        if (paramMap.get("bindId") != null) {
            map.put("bindId", paramMap.get("bindId"));
        }
        Map<String, String> params = StringUtil.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        StringUtil.buildPayParams(buf, params, false);
        String payStr = buf.toString();

        String signature;
        System.out.println("payStr:" + payStr);
        String md5Key = paramMap.get("md5Key");
        System.out.println("source+key:==>" + payStr + md5Key);
        try {
            SignatureUtil2 signUtil = new SignatureUtil2();
            byte[] CFCAsignature = signUtil.p7SignMessageDetach("SHA1withRSAEncryption", payStr.getBytes("UTF-8"),
                    ConfigInfo.getPrivateKey(), ConfigInfo.getX509Cert(), ConfigInfo.getSession());
            signature = new String(CFCAsignature, "UTF-8");
            System.out.println("signature:" + signature);
            String str = payStr + "&signature=" + signature;
            System.out.println("发送--加密前参数==" + str);
            return signature;
        } catch (UnsupportedEncodingException | PKIException e) {
            e.printStackTrace();
        }
        return null;
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
