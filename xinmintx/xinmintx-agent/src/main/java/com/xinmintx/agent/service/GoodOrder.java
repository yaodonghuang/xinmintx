package com.xinmintx.agent.service;

import com.xinmintx.agent.model.GoodsOrder;
import com.xinmintx.agent.model.GoodsOrderDetail;

public interface GoodOrder {

    void insert(GoodsOrder goodsOrder);

    void insertDetail(GoodsOrderDetail detail);
}
