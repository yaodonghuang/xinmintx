package com.xinmintx.factory.api.pojo;


import java.util.Date;

public class GoodsOrderDetail {

  private long id;
  private long memberId;
  private long orderId;
  private long goodsId;
  private long skuId;
  private long factoryId;
  private double price;
  private long quantity;
  private String goodsName;
  private String goodsPic;
  private String evaluate;
  private Date createTime;
  private Date updateTime;
  private String courierNumber;
  private long orderState;
  private double totalAmount;
  private long lack;
  private double weight;
  private long ifRemind;
  private String refundMsg;
  private String returnsSingleNumber;
  private long ifPay;
  private String refundInformation;
  private String returnMessage;

  public String getReturnMessage() {
    return returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }

  public String getRefundInformation() {
    return refundInformation;
  }

  public void setRefundInformation(String refundInformation) {
    this.refundInformation = refundInformation;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMemberId() {
    return memberId;
  }

  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getSkuId() {
    return skuId;
  }

  public void setSkuId(long skuId) {
    this.skuId = skuId;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


  public String getGoodsPic() {
    return goodsPic;
  }

  public void setGoodsPic(String goodsPic) {
    this.goodsPic = goodsPic;
  }


  public String getEvaluate() {
    return evaluate;
  }

  public void setEvaluate(String evaluate) {
    this.evaluate = evaluate;
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
    this.courierNumber = courierNumber;
  }


  public long getOrderState() {
    return orderState;
  }

  public void setOrderState(long orderState) {
    this.orderState = orderState;
  }


  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }


  public long getLack() {
    return lack;
  }

  public void setLack(long lack) {
    this.lack = lack;
  }


  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public long getIfRemind() {
    return ifRemind;
  }

  public void setIfRemind(long ifRemind) {
    this.ifRemind = ifRemind;
  }


  public String getRefundMsg() {
    return refundMsg;
  }

  public void setRefundMsg(String refundMsg) {
    this.refundMsg = refundMsg;
  }


  public String getReturnsSingleNumber() {
    return returnsSingleNumber;
  }

  public void setReturnsSingleNumber(String returnsSingleNumber) {
    this.returnsSingleNumber = returnsSingleNumber;
  }


  public long getIfPay() {
    return ifPay;
  }

  public void setIfPay(long ifPay) {
    this.ifPay = ifPay;
  }

}
