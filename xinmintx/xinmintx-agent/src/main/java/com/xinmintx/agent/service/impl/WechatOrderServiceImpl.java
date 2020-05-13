package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mchpay.CloseOrder;
import com.xinmintx.agent.mchpay.WXUnifiedOrder;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.service.WechatOrderService;
import com.xinmintx.agent.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wechatOrderService")
public class WechatOrderServiceImpl implements WechatOrderService {

    @Override
    public WXUnifiedOrder createNativeWechatOrder(UOrder uorder, Map<String, Object> wechatConfig, String wechatNotifyUrl) {
        WXUnifiedOrder unifiedOrder = new WXUnifiedOrder();
        unifiedOrder.setAppid(wechatConfig.get("appid").toString());
        unifiedOrder.setMch_id(wechatConfig.get("mch_id").toString());
        unifiedOrder.setNonce_str(uorder.getOrderNo());
        unifiedOrder.setBody(uorder.getGoodsDesc());
        unifiedOrder.setOut_trade_no(uorder.getOutTradeNo());
        unifiedOrder.setTotal_fee(String.valueOf(uorder.getTotalFee()));
        unifiedOrder.setSpbill_create_ip("8.8.8.8");
        unifiedOrder.setOpenid(wechatConfig.get("openid").toString());
        unifiedOrder.setTime_start(DateUtil.getNowTime(DateUtil.DATE_KEY_STR));
        unifiedOrder.setNotify_url(wechatNotifyUrl);
        unifiedOrder.setTrade_type("JSAPI");
        unifiedOrder.setSign_type("MD5");
        return unifiedOrder;
    }

    @Override
    public CloseOrder createCloseOrder(UOrder uorder, Map<String, Object> wechatConfig) {
        CloseOrder closeOrder = new CloseOrder();
        closeOrder.setAppid(wechatConfig.get("appid").toString());
        closeOrder.setMch_id(wechatConfig.get("mch_id").toString());
        closeOrder.setOut_trade_no(uorder.getOutTradeNo());
        closeOrder.setNonce_str(System.currentTimeMillis() + "");
        return closeOrder;
    }
}
