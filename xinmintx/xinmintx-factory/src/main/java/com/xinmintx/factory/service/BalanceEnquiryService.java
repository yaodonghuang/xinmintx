package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface BalanceEnquiryService {

    ResultCode balanceEnquiry(Map<String, String> paramMap);
}
