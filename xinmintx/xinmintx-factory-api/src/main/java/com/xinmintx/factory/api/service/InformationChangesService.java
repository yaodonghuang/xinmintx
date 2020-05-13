package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.common.ResultCode;

import java.util.Map;

public interface InformationChangesService {
    ResultCode informationChanges(Map<String, String> paramMap);
}
