package com.xinmintx.agent.service;


import com.xinmintx.agent.common.ResultCode;

import java.util.Map;

public interface WithdrawService {
    ResultCode withdraw(Map<String, String> paramMap);
}
