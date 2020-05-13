package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface RechargeService {
    ResultCode recharge(Map<String, String> paramMap);
}
