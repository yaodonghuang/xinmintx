package com.xinmintx.factory.service;

import java.util.Map;

/**
 * 微信公众号扣费调第三方支付接口
 */
public interface IMerchantWxPublicPay {
    /**
     * 微信公众号和小程序支付扣费方法
     *
     * @param paramMap 參數paramMap
     *                 必传参数:
     *                 1.orderId(商户平台生成的用于标识该笔订单的唯一号码)
     *                 2.orderAmount(订单金额 以人民币分为单位)
     *                 3.prdName(商品名称)
     *                 4.prdDesc(商品描述)
     *                 5.openid(用户openid)
     *                 6.retUrl(结果返回URL，仅适用于立即返回处理结果的接口)
     */
    public void weChatPay(Map<String, String> paramMap);
}
