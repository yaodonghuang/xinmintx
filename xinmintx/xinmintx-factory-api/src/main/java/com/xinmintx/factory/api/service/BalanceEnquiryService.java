package com.xinmintx.factory.api.service;



import com.xinmintx.factory.api.common.ResultCode;

import java.util.Map;

public interface BalanceEnquiryService {

    ResultCode balanceEnquiry(Map<String, String> paramMap);
}
