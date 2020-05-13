package com.xinmintx.factory.api.service.impl;

import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.MerchantMD5;
import com.xinmintx.common.parity.StringUtil;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.service.SearchPayment;
import com.xinmintx.factory.api.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 单笔代付结果查询
 */
@Service
public class SearchPaymentImpl implements SearchPayment {
    @Override
    public ResultCode searchPayment(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 调用单笔余额查询方法
        return searchPobo(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("merchantId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("signType", "MD5");// 加密类型
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/MerchantSinglePayQuery.do");// 请求地址
    }

    // 调用单笔代付结果查询接口
    private ResultCode searchPobo(Map<String, String> paramMap) {
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
