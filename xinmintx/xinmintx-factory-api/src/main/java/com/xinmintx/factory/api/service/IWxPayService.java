package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.common.ResultCode;

/**
 * 微信退款接口
 */
public interface IWxPayService {
    // 微信退款
    public ResultCode wxRefund(Integer orderId) throws Exception;
}
