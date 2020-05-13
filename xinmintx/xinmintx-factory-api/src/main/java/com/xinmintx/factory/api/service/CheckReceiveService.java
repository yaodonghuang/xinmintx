package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.common.ResultCode;

public interface CheckReceiveService {
    // 确认收货接口
    ResultCode checkReceive(Integer orderId);
}
