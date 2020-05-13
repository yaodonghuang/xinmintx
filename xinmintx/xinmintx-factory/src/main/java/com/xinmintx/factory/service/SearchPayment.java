package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface SearchPayment {
    // 单笔支付结果查询接口
    ResultCode searchPayment(Map<String, String> paramMap);
}
