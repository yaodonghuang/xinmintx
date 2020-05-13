package com.xinmintx.agent.service;


import com.xinmintx.agent.mchpay.*;

public interface WechatPayMchService {

    /**
     * 统一下单
     *
     * @param unifiedorder
     * @param key
     * @return
     */
    WXUnifiedOrderResult payUnifiedorder(WXUnifiedOrder unifiedorder, String key);

    /**
     * 刷卡支付 提交被扫支付API
     *
     * @param micropay
     * @param key
     * @return
     */
    MicroPayResult payMicropay(MicroPay micropay, String key);

    /**
     * 查询订单
     *
     * @param mchOrderquery
     * @param key
     * @return
     */
    MchOrderInfoResult payOrderQuery(MchOrderQuery mchOrderquery, String key);

    /**
     * 关闭订单
     *
     * @param closeorder
     * @param key        商户支付密钥
     * @return
     */
    MchBaseResult payCloseOrder(CloseOrder closeorder, String key);

    /**
     * 申请退款
     * <p>
     * 注意： 1.交易时间超过半年的订单无法提交退款；
     * 2.微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。
     * 总退款金额不能超过用户实际支付金额。
     *
     * @param secapiPayRefund
     * @param key             商户支付密钥
     * @return
     */
    SecapiPayRefundResult secapiPayRefund(SecapiPayRefund secapiPayRefund, String key, byte[] keyStoreP12);

    /**
     * 查询退款
     * <p>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款 20 分钟内到账，银行卡支付的退款3 个工作日后重新查询退款状态。
     *
     * @param refundquery
     * @param key         商户支付密钥
     * @return
     */
    RefundQueryResult payRefundQuery(RefundQuery refundquery, String key);

}