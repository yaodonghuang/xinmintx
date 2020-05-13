package com.xinmintx.community.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息
 */
public class Member {
    private Integer id;

    private Integer orderId;

    private String name;

    private String cellphone;

    private Integer gender;

    private String idcard;

    private Integer recommender;

    private Integer memberType;

    private Integer isReview;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String openid;

    private String token;

    private String avatarUrl;

    private Integer platformCount;

    private Integer merchantCount;

    private Integer integral;

    private BigDecimal newCurrency;

    private Integer birthGiftCount;

    private String regionCode;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getBirthGiftCount() {
        return birthGiftCount;
    }

    public void setBirthGiftCount(Integer birthGiftCount) {
        this.birthGiftCount = birthGiftCount;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getNewCurrency() {
        return newCurrency;
    }

    public void setNewCurrency(BigDecimal newCurrency) {
        this.newCurrency = newCurrency;
    }

    public Integer getPlatformCount() {
        return platformCount;
    }

    public void setPlatformCount(Integer platformCount) {
        this.platformCount = platformCount;
    }

    public Integer getMerchantCount() {
        return merchantCount;
    }

    public void setMerchantCount(Integer merchantCount) {
        this.merchantCount = merchantCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getRecommender() {
        return recommender;
    }

    public void setRecommender(Integer recommender) {
        this.recommender = recommender;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}
