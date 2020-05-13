package com.xinmintx.factory.api.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.StringUtil;

import com.xinmintx.factory.api.mapper.UserMapper;
import com.xinmintx.factory.api.service.UserAccountService;
import com.xinmintx.factory.api.util.ConfigInfo;
import com.xinmintx.factory.api.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付对接
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Resource
    private UserMapper userMapper;

    /**
     * 开户方法
     *
     * @param paramMap 參數paramMap
     */
    @Override
    public Map userAccount(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 调用扣费方法
        return pay(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("platformId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("signType", "CFCA");// 加密类型
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/warrantrUsrRegist.do");
    }
    // 调用支付接口
    private Map pay(Map<String, String> paramMap) {
        //ResultCode rc = new ResultCode();
        Map<String,String> map = new HashMap<>();
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
             map= XMLConvertor.xml2Map(info);
            //XMLConvertor.dealResult(info, rc);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String platformId = paramMap.get("platformId");// 支付平台统一分配的商户编号
        String signType = paramMap.get("signType");// 加签方式MD5
        String userName = paramMap.get("userName");
        String IdCard = paramMap.get("IdCard");
        String phone = paramMap.get("phone");
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("platformId", platformId);
        post.addParameter("version", version);
        post.addParameter("userName",HexConver.Str2Hex(userName));
        post.addParameter("IdCard",IdCard);
        post.addParameter("phone",phone);
        post.addParameter("signType", signType);
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("platformId", paramMap.get("platformId"));
        map.put("version", paramMap.get("version"));
        map.put("userName",HexConver.Str2Hex(paramMap.get("userName")));
        map.put("IdCard",paramMap.get("IdCard"));
        map.put("phone",paramMap.get("phone"));
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
}
