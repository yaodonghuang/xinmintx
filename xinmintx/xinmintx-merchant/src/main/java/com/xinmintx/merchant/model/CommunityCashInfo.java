package com.xinmintx.merchant.model;


import java.util.Date;

public class CommunityCashInfo {

  private long id;
  private long merchantId;
  private String requestSn;
  private Date createDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }


  public String getRequestSn() {
    return requestSn;
  }

  public void setRequestSn(String requestSn) {
    this.requestSn = requestSn;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

}
