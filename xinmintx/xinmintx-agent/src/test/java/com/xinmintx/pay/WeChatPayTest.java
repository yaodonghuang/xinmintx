package com.xinmintx.pay;

import com.xinmintx.agent.configuration.pay.PayConfig;
import com.xinmintx.agent.mchpay.WXUnifiedOrder;
import com.xinmintx.agent.mchpay.WXUnifiedOrderResult;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.service.WechatOrderService;
import com.xinmintx.agent.service.WechatPayMchService;
import com.xinmintx.agent.service.impl.WechatOrderServiceImpl;
import com.xinmintx.agent.service.impl.WechatPayMchServiceImpl;
import com.xinmintx.agent.util.MapUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/8/10 0010
 * @time: 上午 11:16
 * @Description:
 */
public class WeChatPayTest {

    String wechatAppid = "wxfae6eed7965225be";
    String wechatMchId = "1556750781";
    String wechatKey = "8109b02e63e24f57b388c08c9204a7da";
    String appsecret = "80b91f4d7fc0f19eda15c60767ead8e3";

    @Test
    public void createOrder() {
        WechatOrderService wechatOrderService = new WechatOrderServiceImpl();
        WechatPayMchService wechatPayMchService = new WechatPayMchServiceImpl();

        Map<String, Object> wechatPayConfig = new HashMap<>();
        wechatPayConfig.put("appid", wechatAppid);
        wechatPayConfig.put("mch_id", wechatMchId);
        wechatPayConfig.put("appid_key", wechatKey);
        wechatPayConfig.put("openid", "o8hDDjpMDpYQJrUx1PdDN83eiFdA");
        UOrder uorder = new UOrder();
        uorder.setCreateTime(new Date());
        uorder.setOutTradeNo(DigestUtils.md5Hex(System.currentTimeMillis() + ""));
        uorder.setTotalFee(1L);
        uorder.setOrderNo(System.currentTimeMillis() + "");
        uorder.setGoodsDesc("添加商户");
        uorder.setGoodsDesc("添加商户");

        WXUnifiedOrder wxUnifiedOrder = wechatOrderService.createNativeWechatOrder(uorder, wechatPayConfig, PayConfig.WECHAT_NOTIFY_URL);
        WXUnifiedOrderResult wxUnifiedOrderResult = wechatPayMchService.payUnifiedorder(wxUnifiedOrder, wechatPayConfig.get("appid_key").toString());
        System.out.println("====result====:" + JSON.toJSONString(wxUnifiedOrderResult));
        System.out.println("====return_code====:" + wxUnifiedOrderResult.getReturn_code());
        System.out.println("====return_msg====:" + wxUnifiedOrderResult.getReturn_msg());
        System.out.println("====result_code====:" + wxUnifiedOrderResult.getResult_code());


        Map<String, String> params = new HashMap<>();
        long millis = System.currentTimeMillis();
        System.out.println("timeStamp= " + millis);
        params.put("appId", wechatAppid);
        params.put("timeStamp", String.valueOf(millis / 1000));
        params.put("nonceStr", String.valueOf(millis));
        params.put("package", "prepay_id=" + wxUnifiedOrderResult.getPrepay_id());
        params.put("signType", "MD5");
        System.out.println(JSON.toJSONString(params));
        String paySign = generateSign(params, wechatKey);
        System.out.println(paySign);
    }

    public String generateSign(Map<String, String> map, String paternerKey) {
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        String str = MapUtil.mapJoin(tmap, false, false);
        return DigestUtils.md5Hex(str + "&key=" + paternerKey).toUpperCase();
    }

    @Test
    public void getOpenId() throws Exception {
        String code = "021AZ7LD1nf1U40tMUHD1q74LD1AZ7L0";
        Map map = new HashMap();
        map.put("appid", wechatAppid);
        map.put("secret", appsecret);
        map.put("code", code);
        map.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wechatAppid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, "utf-8");
        System.out.println(json);
        Map map1 = JSON.parseObject(json, Map.class);
        Object openid = map1.get("openid");
        System.out.println(openid);
    }
}
