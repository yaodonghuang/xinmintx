package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.UOrder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/24 0024
 * @time: 下午 17:21
 * @Description:
 */
public interface UOrderSerivce {

    void goodsOrderRefund(GoodsOrder goodsOrder, UOrder uOrder);
}
