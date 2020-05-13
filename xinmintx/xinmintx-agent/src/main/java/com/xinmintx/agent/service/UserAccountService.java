package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;

import java.util.Map;

public interface UserAccountService {

    ResultCode userAccount(Map<String, String> paramMap);
}

