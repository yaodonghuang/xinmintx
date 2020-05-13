package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.pojo.GoodsOrder;
import com.xinmintx.factory.api.pojo.VenderOrder;

import java.util.List;

public interface DeliveryService {
    List query(String token, Integer statu);

    List queryEvaluate(String token);

    List queryByTime(String token, String beginDate, String endDate);

    List queryAll(String token);

    int upStatu(int statu, String orderNum);

    GoodsOrder queryByOrderId(String orderId);

    int upMainStatu(int statu, String orderNum);

    void upSonStatu(long order, int statu);


    VenderOrder queryById(String orderNum);
}
