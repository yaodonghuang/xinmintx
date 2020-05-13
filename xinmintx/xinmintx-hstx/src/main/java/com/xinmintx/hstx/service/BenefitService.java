package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.UOrder;
import com.xinmintx.hstx.pojo.po.User;

public interface BenefitService {

    void benfit(GoodsOrder goodsOrder);

    void benfit(GoodsOrder goodsOrder,Integer id);
}
