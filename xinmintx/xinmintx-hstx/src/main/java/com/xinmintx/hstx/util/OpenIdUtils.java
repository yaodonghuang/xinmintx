package com.xinmintx.hstx.util;

import com.alibaba.fastjson.JSON;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/15 0015
 * @time: 下午 15:51
 * @Description:
 */
@Component
@Slf4j
public class OpenIdUtils {

    @Value("${wechat.appid}")
    private String wechatAppid;

    @Value("${wechat.appsecret}")
    private String appsecret;

    public Map getUnionID(String code) {
        Map map = getAccessToken(code);
        String openid = "";
        if (map != null && map.containsKey("openid")) {
            openid = map.get("openid").toString();
        }
        if (StringUtils.isNotBlank(openid)) {
            String token = getJsapiTicketAccEssToken();
            boolean subscribe = getUnionID(token, openid);
            map.put("subscribe", subscribe);
            map.put("openid", openid);
            return map;
        }
        return null;
    }

    /**
     * 通过code获取openid
     *
     * @param code
     * @return
     */
    public String getOpenId(String code) {
        Map token = getAccessToken(code);
        return token.get("openid").toString();
    }

    /**
     * 通过code获取用户信息
     *
     * @param code
     * @return
     */
    public Map getUserInfo(String code) {
        Map token = getAccessToken(code);
        if (token == null) {
            return null;
        }
        String accessToken = token.get("access_token").toString();
        String openid = token.get("openid").toString();
        Map userInfo = getUserInfo(openid, accessToken);
        if (userInfo != null) {
            userInfo.put("refresh_token", token.get("refresh_token"));
        }
        return userInfo;
    }

    /**
     * 根据用户openid和refresh_token获取用户信息
     *
     * @param openId
     * @param refreshToken
     * @return
     */
    public Map getUserInfoByOpenIdAndRefreshToken(String openId, String refreshToken) {
        //根绝refresh_token刷新access_token
        String accessToken = refreshToken(refreshToken);
        if (accessToken == null) {
            return null;
        }
        return getUserInfo(openId, accessToken);
    }

    /**
     * 通过code获取微信access_token
     *
     * @param code code
     * @return
     */
    public Map getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wechatAppid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("openid")) {
            return map;
        }
        return null;

    }

    /**
     * 根据access_token和openid获取用户信息
     *
     * @param openid      openid
     * @param accessToken access_token
     * @return 用户信息
     */
    public Map getUserInfo(String openid, String accessToken) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("openid")) {
            String nickname = map.get("nickname").toString();
            map.put("nickname", EmojiUtil.emojiConvertToUtf(nickname));
            return map;
        }
        return null;

    }

    /**
     * 通过refreshToken刷新access_token
     *
     * @param refreshToken refresh_token
     * @return access_token
     */
    private String refreshToken(String refreshToken) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + wechatAppid + "&grant_type=refresh_token&refresh_token=" + refreshToken;
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("access_token")) {
            return map.get("access_token").toString();
        }
        return null;

    }

    public String getJsapiTicket() {
        String accessToken = getJsapiTicketAccEssToken();
        return getJsapiTicket(accessToken);
    }

    /**
     * 微信给指定openid发送文本消息
     *
     * @param openid
     * @param message
     */
    public void sendMessage(String openid, String message) {
        String access_token = getJsapiTicketAccEssToken();
        if (StringUtils.isBlank(access_token)) {
            return;
        }
        Map<String, String> text = new HashMap<>();
        text.put("content", message);
        Map<String, Object> msg = new HashMap<>();
        msg.put("touser", openid);
        msg.put("msgtype", "text");
        msg.put("text", text);
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token;
        HttpClientUtil.doPost(url, null, null, JSON.toJSONString(msg));
    }

    /**
     * 微信给指定openid发送模板消息
     * ZnOAsrWvdWz5TUyvNibyta8dsCt668__hJ3yQMlGIAU
     *
     * @param openid
     * @param message
     */
    public void sendMessageByTemplate(String templateId, String openid, String message) {
        String access_token = getJsapiTicketAccEssToken();
        if (StringUtils.isBlank(access_token)) {
            return;
        }

        Map<String, String> first = new HashMap<>();
        first.put("value", "商品购买成功");
//        first.put("color", "#173177");

        Map<String, String> keyword1 = new HashMap<>();
        keyword1.put("value", "饼干零食");
//        keyword1.put("color", "#173177");

        Map<String, String> keyword2 = new HashMap<>();
        keyword2.put("value", "20170410161322777663");
//        keyword2.put("color", "#173177");

        Map<String, String> keyword3 = new HashMap<>();
        keyword3.put("value", "0.1元");
//        keyword3.put("color", "#173177");

        Map<String, String> keyword4 = new HashMap<>();
        keyword4.put("value", "2020-03-19 09:58:23");
//        keyword3.put("color", "#173177");

        Map<String, String> remark = new HashMap<>();
        remark.put("value", "感谢您的光临!");
//        remark.put("color", "#173177");

        Map<String, Object> data = new HashMap<>();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("remark", remark);

        Map<String, Object> msg = new HashMap<>();
        msg.put("touser", "osGY-w6uGse-E1RkOBx-jRYDvTm4");
        msg.put("template_id", "ZnOAsrWvdWz5TUyvNibyta8dsCt668__hJ3yQMlGIAU");
        msg.put("url", "");
        msg.put("data", data);

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        HttpClientUtil.doPost(url, null, null, JSON.toJSONString(msg));
    }

    /**
     * 微信给指定openid发送模板消息
     *
     * @param openid    用户openid
     * @param body      商品描述
     * @param goodsName 商品名称
     * @param orderId   订单号
     * @param price     价格
     * @param date      订单日期
     * @param remarks   备注
     * @param linkUrl   跳转链接
     */
    public void sendMessageByTemplate(String openid, String body, String goodsName, String orderId, String price, String date, String remarks, String linkUrl) {
        String access_token = getJsapiTicketAccEssToken();
        if (StringUtils.isBlank(access_token)) {
            return;
        }

        Map<String, String> first = new HashMap<>();
        first.put("value", body);

        Map<String, String> keyword1 = new HashMap<>();
        keyword1.put("value", goodsName);

        Map<String, String> keyword2 = new HashMap<>();
        keyword2.put("value", orderId);

        Map<String, String> keyword3 = new HashMap<>();
        keyword3.put("value", price);

        Map<String, String> keyword4 = new HashMap<>();
        keyword4.put("value", date);

        Map<String, String> remark = new HashMap<>();
        remark.put("value", remarks);

        Map<String, Object> data = new HashMap<>();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("remark", remark);

        Map<String, Object> msg = new HashMap<>();
        msg.put("touser", openid);
        msg.put("template_id", "ZnOAsrWvdWz5TUyvNibyta8dsCt668__hJ3yQMlGIAU");
        msg.put("url", linkUrl);
        msg.put("data", data);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        HttpClientUtil.doPost(url, null, null, JSON.toJSONString(msg));
    }

    public String getJsapiTicketAccEssToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wechatAppid + "&secret=" + appsecret;
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("access_token")) {
            return map.get("access_token").toString();
        }
        return null;
    }


    private String getJsapiTicket(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("ticket")) {
            return map.get("ticket").toString();
        }
        return null;
    }

    private boolean getUnionID(String token, String openId) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openId + "&lang=zh_CN";
        String json = HttpClientUtil.doGet(url);
        Map map = JSON.parseObject(json, Map.class);
        if (map.containsKey("subscribe")) {
            Integer subscribe = (Integer) map.get("subscribe");
            return subscribe == 1;
        }
        return false;
    }
}
