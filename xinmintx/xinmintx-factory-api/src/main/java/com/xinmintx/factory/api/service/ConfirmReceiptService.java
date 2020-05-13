package com.xinmintx.factory.api.service;



import com.xinmintx.factory.api.common.ResultCode;

import java.util.Map;

public interface ConfirmReceiptService {
    ResultCode confirmReceipt(Map<String, String> paramMap);
}
