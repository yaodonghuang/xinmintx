package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;

import java.util.Map;

public interface QueryOrderService {
    ResultCode queryOrder(Map<String, String> paramMap);
}
