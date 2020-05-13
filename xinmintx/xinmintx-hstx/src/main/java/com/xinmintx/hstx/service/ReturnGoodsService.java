package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;

public interface ReturnGoodsService {

    void returnGoods (GoodsOrderDetail goodsOrderDetail,int flag);
}
