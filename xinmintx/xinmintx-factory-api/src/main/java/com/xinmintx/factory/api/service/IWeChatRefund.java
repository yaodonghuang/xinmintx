package com.xinmintx.factory.api.service;

import java.util.Map;

public interface IWeChatRefund {
    /**
     *
     * @param paramMap
     *        参数:
     *        1.orderId(原支付成功订单号)
     *        2.refundOrdNo(退款订单号)
     *        3.rfAmt(以人民币分为单位)
     *        4.rfSake(退款理由)
     *        5.notifyUrl(针对该交易的交易状态同步通知接收URL)
     */
    public void doRefund(Map<String, String> paramMap);
}
