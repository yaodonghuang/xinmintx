package com.xinmintx.merchant.model;


import java.util.Date;

public class GoodsOrder {

  private long id;
  private long memberId;
  private String orderNum;
  private long orderState;
  private Date createTime;
  private Date updateTime;
  private long ifPay;
  private String receiveAddress;
  private String receiveName;
  private String receivePhone;
  private String receiveMessage;
  private double totalAmount;
  private long uOrderId;
  private long goodsId;
  private long skuId;
  private long factoryId;
  private long merchantId;
  private long communityId;
  private double price;
  private long quantity;
  private String goodsName;
  private String goodsPic;
  private String evaluate;
  private String courierNumber;
  private long ifDelete;
  private double weight;
  private long userDelete;
  private long ifRemind;
  private String refundMsg;
  private long orderType;
  private Date sendDate;
  private long ifDelayed;
  private long addressId;
  private long dividedState;
  private String refundInformation;
  private String returnMessage;
  private Date refundTime;
  private String deputyInfo;
  private Integer PrintNumber;
  private Integer deputyHelp;
  private String consigneeInfo;

  public String getConsigneeInfo() {
    return consigneeInfo;
  }

  public void setConsigneeInfo(String consigneeInfo) {
    this.consigneeInfo = consigneeInfo;
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


  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }


  public long getOrderState() {
    return orderState;
  }

  public void setOrderState(long orderState) {
    this.orderState = orderState;
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


  public long getIfPay() {
    return ifPay;
  }

  public void setIfPay(long ifPay) {
    this.ifPay = ifPay;
  }


  public String getReceiveAddress() {
    return receiveAddress;
  }

  public void setReceiveAddress(String receiveAddress) {
    this.receiveAddress = receiveAddress;
  }


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


  public String getReceiveMessage() {
    return receiveMessage;
  }

  public void setReceiveMessage(String receiveMessage) {
    this.receiveMessage = receiveMessage;
  }


  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }


  public long getUOrderId() {
    return uOrderId;
  }

  public void setUOrderId(long uOrderId) {
    this.uOrderId = uOrderId;
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


  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }


  public long getCommunityId() {
    return communityId;
  }

  public void setCommunityId(long communityId) {
    this.communityId = communityId;
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


  public String getCourierNumber() {
    return courierNumber;
  }

  public void setCourierNumber(String courierNumber) {
    this.courierNumber = courierNumber;
  }


  public long getIfDelete() {
    return ifDelete;
  }

  public void setIfDelete(long ifDelete) {
    this.ifDelete = ifDelete;
  }


  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public long getUserDelete() {
    return userDelete;
  }

  public void setUserDelete(long userDelete) {
    this.userDelete = userDelete;
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


  public long getOrderType() {
    return orderType;
  }

  public void setOrderType(long orderType) {
    this.orderType = orderType;
  }


  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }


  public long getIfDelayed() {
    return ifDelayed;
  }

  public void setIfDelayed(long ifDelayed) {
    this.ifDelayed = ifDelayed;
  }


  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }


  public long getDividedState() {
    return dividedState;
  }

  public void setDividedState(long dividedState) {
    this.dividedState = dividedState;
  }


  public String getRefundInformation() {
    return refundInformation;
  }

  public void setRefundInformation(String refundInformation) {
    this.refundInformation = refundInformation;
  }


  public String getReturnMessage() {
    return returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }


  public Date getRefundTime() {
    return refundTime;
  }

  public void setRefundTime(Date refundTime) {
    this.refundTime = refundTime;
  }


  public String getDeputyInfo() {
    return deputyInfo;
  }

  public void setDeputyInfo(String deputyInfo) {
    this.deputyInfo = deputyInfo;
  }

  public Integer getPrintNumber() {
    return PrintNumber;
  }

  public void setPrintNumber(Integer printNumber) {
    PrintNumber = printNumber;
  }

  public Integer getDeputyHelp() {
    return deputyHelp;
  }

  public void setDeputyHelp(Integer deputyHelp) {
    this.deputyHelp = deputyHelp;
  }
}
