package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.model.VenderOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DeliveryService {
    List query(String token, Integer statu);

    List queryEvaluate(String token);

    List queryByTime(String token,String beginDate, String endDate);

    List queryAll( String token);

    int upStatu(int statu,String orderNum);

    GoodsOrder queryByOrderId(String orderId);

    int upMainStatu(int statu, String orderNum);

    void upSonStatu(long order, int statu);


    VenderOrder queryById(String orderNum);
}
