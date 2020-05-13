package com.xinmintx.merchant.model;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.factory.model.VenderOrder
 * @Author:Pikachu
 * @Date: 2019/12/4 14:28
 * @Version: v1.0
 */

public class VenderOrder {
    private String receiveName;//买家姓名
    private String receivePhone;//买家电话
    private String receiveAddress;//买家地址
    private String goodsName;//商品名称
    private double price;//商品单价
    private Integer quantity;//商品数量
    private String goodsPic;//商品图片
    private Integer goodsId;//商品Id
    private Integer orderState;//订单状态
    private String orderId;//主订单号

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
