package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.PoboNotify;

import java.util.Map;

public interface PaymentOnBehalfOf {
    // 代付接口
    ResultCode paymentOnBehalfOf(Map<String, String> paramMap);

    // 代付回调接口
    ResultCode paymentOnBehalfOfNotify(PoboNotify pn);

}
