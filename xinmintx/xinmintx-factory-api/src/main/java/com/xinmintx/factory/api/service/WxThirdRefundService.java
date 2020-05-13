package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.common.ResultCode;

/**
 * 微信第三方退款
 */
public interface WxThirdRefundService {
    /**
     * 微信第三方退款（根据详情id退款）
     * @param detailOrderId 订单详情表id
     * @return
     */
    public ResultCode wxThirdRefund(Integer detailOrderId);

    /**
     * 微信第三方退款（根据主订单id退款）
     * @param orderId
     * @return
     */
    public ResultCode wxThirdRefundMain(Integer orderId);
}
