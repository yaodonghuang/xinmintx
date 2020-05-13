package com.xinmintx.factory.model;


import java.math.BigDecimal;
import java.util.Date;

public class Factory {
  private Long id;
  private Long factoryId;
  private String type;
  private String factoryCode;
  private String name;
  private String avatar;
  private String address;
  private String phone;
  private String password;
  private String salt;
  private Date createTime;
  private String delFlag;
  private String token;
  private BigDecimal freezingAmount;// 冻结金额
  private BigDecimal cashAmount;// 可用金额
  private String inputCashAmount;// 输入的金额
  private String bankCard;//
  private String personname;
  private String idcard;
  private String regionName;
  private String regionCode;
  private String referrerid;
  private String reservedPhoto;
  private String phoneId;

  public String getPersonname() {
    return personname;
  }

  public void setPersonname(String personname) {
    this.personname = personname;
  }

  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  public String getReferrerid() {
    return referrerid;
  }

  public void setReferrerid(String referrerid) {
    this.referrerid = referrerid;
  }

  public String getReservedPhoto() {
    return reservedPhoto;
  }

  public void setReservedPhoto(String reservedPhoto) {
    this.reservedPhoto = reservedPhoto;
  }

  public String getPhoneId() {
    return phoneId;
  }

  public void setPhoneId(String phoneId) {
    this.phoneId = phoneId;
  }

  public String getBankCard() {
    return bankCard;
  }

  public void setBankCard(String bankCard) {
    this.bankCard = bankCard;
  }

  public String getInputCashAmount() {
    return inputCashAmount;
  }

  public void setInputCashAmount(String inputCashAmount) {
    this.inputCashAmount = inputCashAmount;
  }

  public BigDecimal getFreezingAmount() {
    return freezingAmount;
  }

  public void setFreezingAmount(BigDecimal freezingAmount) {
    this.freezingAmount = freezingAmount;
  }

  public BigDecimal getCashAmount() {
    return cashAmount;
  }

  public void setCashAmount(BigDecimal cashAmount) {
    this.cashAmount = cashAmount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Long factoryId) {
    this.factoryId = factoryId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getFactoryCode() {
    return factoryCode;
  }

  public void setFactoryCode(String factoryCode) {
    this.factoryCode = factoryCode;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
