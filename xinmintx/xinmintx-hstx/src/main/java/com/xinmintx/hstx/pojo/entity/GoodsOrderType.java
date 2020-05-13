package com.xinmintx.hstx.pojo.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/12 0012
 * @time: 上午 10:14
 * @Description: 订单类型
 */
public class GoodsOrderType {
    /**
     * 购物车结算
     */
    public static final Integer SHOPPING_CART_SETTLE = 0;
    /**
     * 商品购买结算
     */
    public static final Integer GOODS_SETTLE = 1;
    /**
     * 礼包商品结算
     */
    public static final Integer GIFT_GOODS_SETTLE = 2;
    /**
     * 拼团
     */
    public static final Integer GOODS_GROUP_SETTLE = 3;
    /**
     * 拼团
     */
    public static final Integer GOODS_PANIC_SETTLE = 4;
}
