package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface ConfirmReceiptService {
    ResultCode confirmReceipt(Map<String, String> paramMap);
}
