package com.xinmintx.factory.common.property;

import lombok.Data;

/**
 **/
@Data
public class WxProperties {

    /*app id*/
    private String app_id;

    /*商户id*/
    private String mch_id;

    /*app secret*/
    private String app_secret;

    /*app key*/
    private String app_key;

    /*api key*/
    private String api_key;

    // 商户密钥,退款签名加密用的那个密钥key,很重要
    private String api_secret;

    /*交易类型*/
    private String trade_type;

    /*合作商key*/
    private String partner_key;

    /*退款url*/
    private String notify_url;

    /*退款通知url*/
    private String refund_notify_url;

    /*创建订单url*/
    private String create_order_url;

    /*退款url*/
    private String refund_url;

    /*授权url*/
    private String auth_url;

    /*获取token的url*/
    private String token_url;

    /*获取openid的url*/
    private String sessionKey_url;

    /*证书路径*/
    private String certificate_path;

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPartner_key() {
        return partner_key;
    }

    public void setPartner_key(String partner_key) {
        this.partner_key = partner_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getRefund_notify_url() {
        return refund_notify_url;
    }

    public void setRefund_notify_url(String refund_notify_url) {
        this.refund_notify_url = refund_notify_url;
    }

    public String getCreate_order_url() {
        return create_order_url;
    }

    public void setCreate_order_url(String create_order_url) {
        this.create_order_url = create_order_url;
    }

    public String getRefund_url() {
        return refund_url;
    }

    public void setRefund_url(String refund_url) {
        this.refund_url = refund_url;
    }

    public String getAuth_url() {
        return auth_url;
    }

    public void setAuth_url(String auth_url) {
        this.auth_url = auth_url;
    }

    public String getToken_url() {
        return token_url;
    }

    public void setToken_url(String token_url) {
        this.token_url = token_url;
    }

    public String getSessionKey_url() {
        return sessionKey_url;
    }

    public void setSessionKey_url(String sessionKey_url) {
        this.sessionKey_url = sessionKey_url;
    }

    public String getCertificate_path() {
        return certificate_path;
    }

    public void setCertificate_path(String certificate_path) {
        this.certificate_path = certificate_path;
    }
}
