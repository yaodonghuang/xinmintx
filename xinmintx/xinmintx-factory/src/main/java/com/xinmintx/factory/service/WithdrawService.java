package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface WithdrawService {
    ResultCode withdraw(Map<String,String> paramMap);
}
