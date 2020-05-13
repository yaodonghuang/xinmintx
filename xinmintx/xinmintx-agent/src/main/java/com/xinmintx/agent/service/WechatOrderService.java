package com.xinmintx.agent.service;

import com.xinmintx.agent.mchpay.CloseOrder;
import com.xinmintx.agent.mchpay.WXUnifiedOrder;
import com.xinmintx.agent.model.UOrder;

import java.util.Map;

/**
 * Created by bao on 7/5/16.
 */
public interface WechatOrderService {

    WXUnifiedOrder createNativeWechatOrder(UOrder uOrder, Map<String, Object> wechatConfig, String wechatNotifyUrl);

    CloseOrder createCloseOrder(UOrder uorder, Map<String, Object> wechatConfig);


}
