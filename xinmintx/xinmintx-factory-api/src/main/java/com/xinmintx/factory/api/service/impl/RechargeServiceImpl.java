package com.xinmintx.factory.api.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.parity.StringUtil;
import com.xinmintx.factory.api.service.RechargeService;
import com.xinmintx.factory.api.util.ConfigInfo;
import com.xinmintx.factory.api.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName: com.xinmintx.factory.service.impl.rechargeServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/24 10:15
 * @Version: v1.0
 */
@Service
public class RechargeServiceImpl implements RechargeService {
    @Override
    public ResultCode recharge(Map<String, String> paramMap) {
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
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/warrantrRecharge.do");
        paramMap.put("wechatFlg", "1");// 微信支付方式
        paramMap.put("subChnMerno", "332084536");// 渠道子商户
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
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String platformId = paramMap.get("platformId");// 支付平台统一分配的商户编号
        String signType = paramMap.get("signType");// 加签方式MD5
        String userId = paramMap.get("userId");//用户编号
        String orderId = paramMap.get("orderId");//订单编号
        String bindId = paramMap.get("bindId");//如果是用户，则绑卡为必填
        String pwd = paramMap.get("pwd");//支付密码
        String orderAmt = paramMap.get("orderAmt");//充值金额
        String retUrl = paramMap.get("retUrl");//异步通知
        String mession = paramMap.get("mession");//短信编号	用此字段验证是否为当前验证短信
        String smsCode = paramMap.get("smsCode");//短信验证码		短信验证码
        //支付密码RSA加密
        pwd = StringUtil.encryptWarrantr(pwd);
        paramMap.put("pwd",pwd);
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("platformId", platformId);
        post.addParameter("version", version);
        post.addParameter("userId",userId);
        post.addParameter("orderId",orderId);
        post.addParameter("bindId",bindId);
        post.addParameter("pwd",pwd);
        post.addParameter("retUrl",retUrl);
        post.addParameter("orderAmt",orderAmt);
        post.addParameter("signType", signType);
        post.addParameter("signature", signature);
        post.addParameter("mession",mession);
        post.addParameter("smsCode",smsCode);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("platformId", paramMap.get("platformId"));
        map.put("version", paramMap.get("version"));
        map.put("userId",paramMap.get("userId"));
        map.put("orderId",paramMap.get("orderId"));
        map.put("bindId",paramMap.get("bindId"));
        map.put("pwd",paramMap.get("pwd"));
        map.put("retUrl",paramMap.get("retUrl"));
        map.put("orderAmt",paramMap.get("orderAmt"));
        map.put("signType", paramMap.get("signType"));
        map.put("mession",paramMap.get("mession"));
        map.put("smsCode",paramMap.get("smsCode"));
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
