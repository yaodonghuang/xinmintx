package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface PaymentService {
    ResultCode payment(Map<String, String> paramMap);
}
