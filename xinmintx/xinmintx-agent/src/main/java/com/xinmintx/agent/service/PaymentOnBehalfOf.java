package com.xinmintx.agent.service;


import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.PoboNotify;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface PaymentOnBehalfOf {
    // 代付接口
    ResultCode paymentOnBehalfOf(Map<String, String> paramMap);

    // 代付回调接口
    ResultCode paymentOnBehalfOfNotify(PoboNotify pn);
}
