package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface ReimburseService {
    ResultCode reimburse(Map<String, String> paramMap);
}
