package com.xinmintx.agent.service;
import com.xinmintx.agent.common.ResultCode;

import java.util.Map;

public interface QueryTiedCardService {
    ResultCode queryTiedCard(Map<String, String> paramMap);
}
