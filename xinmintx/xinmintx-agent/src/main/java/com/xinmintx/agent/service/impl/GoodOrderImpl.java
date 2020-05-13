package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.GoodOrderMapper;
import com.xinmintx.agent.model.GoodsOrder;
import com.xinmintx.agent.model.GoodsOrderDetail;
import com.xinmintx.agent.service.GoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodOrderImpl implements GoodOrder {
    @Autowired
    private GoodOrderMapper goodOrderMapper;

    @Override
    public void insert(GoodsOrder goodsOrder) {
        goodOrderMapper.insertGoodOrder(goodsOrder);
    }

    @Override
    public void insertDetail(GoodsOrderDetail detail) {
        goodOrderMapper.insertDetail(detail);
    }
}
