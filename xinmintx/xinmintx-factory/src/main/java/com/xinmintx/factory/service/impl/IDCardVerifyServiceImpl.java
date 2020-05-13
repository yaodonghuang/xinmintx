package com.xinmintx.factory.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.factory.parity.StringUtil;
import com.xinmintx.factory.service.IDCardVerifyService;
import com.xinmintx.factory.util.ConfigInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName:.IDCardVerifyServiceImpl
 * @author:chf
 * @Date:2019/12/23：14:10
 * @developerKits： win 10     jdk1.8
 */
@Service
public class IDCardVerifyServiceImpl implements IDCardVerifyService {
    @Override
    public void IDCardVerify(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 生成post方法
        pay(paramMap);
    }
    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("platformId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("signType", "CFCA");// 加密类型
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/warrantrUsrUploadIdCard.do");
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

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String platformId = paramMap.get("platformId");// 支付平台统一分配的商户编号
        String signType = paramMap.get("signType");// 加签方式MD5
        String userId = paramMap.get("userId");//用户Id
        String crad1Url = paramMap.get("crad1Url");//身份证正面
        String crad2Url = paramMap.get("crad2Url");//身份证反面
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("platformId", platformId);
        post.addParameter("version", version);
        post.addParameter("userId",userId);
        post.addParameter("crad1Url",crad1Url);
        post.addParameter("crad2Url",crad2Url);
        post.addParameter("signType", signType);
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("platformId", paramMap.get("platformId"));
        map.put("version", paramMap.get("version"));
        map.put("userId",paramMap.get("userId"));
        map.put("crad1Url",paramMap.get("crad1Url"));
        map.put("crad2Url",paramMap.get("crad2Url"));
        map.put("signType", paramMap.get("signType"));

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
