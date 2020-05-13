package com.xinmintx.agent.service;


import com.xinmintx.agent.common.ResultCode;

import java.util.Map;

public interface RechargeService {
    ResultCode recharge(Map<String, String> paramMap);
}
