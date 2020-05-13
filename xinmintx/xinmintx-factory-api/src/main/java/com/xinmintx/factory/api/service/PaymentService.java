package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.common.ResultCode;

import java.util.Map;

public interface PaymentService {
    ResultCode payment(Map<String, String> paramMap);
}
