package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;

public interface CheckReceiveService {
    // 确认收货接口
    ResultCode checkReceive(Integer orderId);
}
