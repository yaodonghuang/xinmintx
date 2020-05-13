package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.PoboNotify;

public interface HsPaymentOnBehalfOf {

    ResultCode hsPaymentOnBehalfOfNotify(PoboNotify pn);
}
