package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsOrderDetail {
    private Integer id;

    private Integer memberId;

    private Integer orderId;

    private Integer goodsId;

    private Integer skuId;

    private Integer factoryId;

    private BigDecimal price;

    private Integer quantity;

    private String goodsName;

    private String goodsPic;

    private String evaluate;

    private Date createTime;

    private Date updateTime;

    private String courierNumber;

    private Integer orderState;

    private BigDecimal totalAmount;

    private Integer lack;

    private Double weight;

    private Integer ifRemind;

    private String refundMsg;

    private Integer ifPay;

    private String goodsDetailsName;

    private String specValue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getLack() {
        return lack;
    }

    public void setLack(Integer lack) {
        this.lack = lack;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getIfRemind() {
        return ifRemind;
    }

    public void setIfRemind(Integer ifRemind) {
        this.ifRemind = ifRemind;
    }

    public String getRefundMsg() {
        return refundMsg;
    }

    public void setRefundMsg(String refundMsg) {
        this.refundMsg = refundMsg == null ? null : refundMsg.trim();
    }

    public Integer getIfPay() {
        return ifPay;
    }

    public void setIfPay(Integer ifPay) {
        this.ifPay = ifPay;
    }

    public String getGoodsDetailsName() {
        return goodsDetailsName;
    }

    public void setGoodsDetailsName(String goodsDetailsName) {
        this.goodsDetailsName = goodsDetailsName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
}