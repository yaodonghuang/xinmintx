package com.xinmintx.merchant.model;


public class PoboNotify implements Comparable<PoboNotify>{

  private long id;
  private String requestSN;
  private String retCode;
  private String retMsg;
  private String merchantId;
  private String orderDate;
  private String orderStatus;
  private String orderTime;
  private String txnAmt;
  private String fee;
  private String notifyUrl;
  private String signType;
  private String signature;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRequestSN() {
    return requestSN;
  }

  public void setRequestSN(String requestSN) {
    this.requestSN = requestSN;
  }

  public String getRetCode() {
    return retCode;
  }

  public void setRetCode(String retCode) {
    this.retCode = retCode;
  }


  public String getRetMsg() {
    return retMsg;
  }

  public void setRetMsg(String retMsg) {
    this.retMsg = retMsg;
  }


  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }


  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }


  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }


  public String getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }


  public String getTxnAmt() {
    return txnAmt;
  }

  public void setTxnAmt(String txnAmt) {
    this.txnAmt = txnAmt;
  }


  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }


  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }


  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }


  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  @Override
  public int compareTo(PoboNotify o) {
    return o.getCreateTime().compareTo(this.createTime);
  }
}
