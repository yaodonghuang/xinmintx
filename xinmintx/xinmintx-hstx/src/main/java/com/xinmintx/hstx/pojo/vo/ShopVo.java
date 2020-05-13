package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/3 0003
 * @time: 上午 11:13
 * @Description:
 */
@Data
public class ShopVo {

    private String token;
    /**
     * 订单id
     */
    private Integer id;
    /**
     * 商品sku id
     */
    private Integer skuId;
    /**
     * 数量
     */
    private Integer total;
    /**
     * 收获地址
     */
    private Integer addressId;
    /**
     * 留言
     */
    private String message;
    /**
     * 购物车详情id
     */
    private Integer[] cartId;
    /**
     * 购买方式,1,消费者购买,2,代理自购
     */
    private Integer buyType;
    /**
     * 拼团商品id
     */
    private Integer ptGoodId;
    /**
     * 拼团团长id
     */
    private Integer ptCodeId;
    /**
     * 礼包id
     */
    private Integer giftId;
    /**
     * 代理商拼团id
     */
    private Integer ptUserId;
    /**
     * 抢购商品id
     */
    private Integer panicId;
    /**
     * 支付方式,(1,微信;2支付宝,3,银行卡,4余额)
     */
    private Integer payType;
    /**
     * 支付密码
     */
    private String password;
}
