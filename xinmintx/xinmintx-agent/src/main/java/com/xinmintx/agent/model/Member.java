package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.Date;

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

    private Integer branchOfficeCount;

    private Integer birthGiftCount;

    private Integer integral;

    private BigDecimal newCurrency;

    private BigDecimal newBeans;

    private Integer userId;

    private BigDecimal freezeBeans;

    private BigDecimal freezeCurrency;

    private Integer giftStart;

    private String treeCode;

    private String regionCode;

    private Date birthday;

    private Integer cardId;

    private Integer cardStatus;

    private Date cardIndate;

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

    public Integer getBranchOfficeCount() {
        return branchOfficeCount;
    }

    public void setBranchOfficeCount(Integer branchOfficeCount) {
        this.branchOfficeCount = branchOfficeCount;
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

    public BigDecimal getNewBeans() {
        return newBeans;
    }

    public void setNewBeans(BigDecimal newBeans) {
        this.newBeans = newBeans;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getFreezeBeans() {
        return freezeBeans;
    }

    public void setFreezeBeans(BigDecimal freezeBeans) {
        this.freezeBeans = freezeBeans;
    }

    public BigDecimal getFreezeCurrency() {
        return freezeCurrency;
    }

    public void setFreezeCurrency(BigDecimal freezeCurrency) {
        this.freezeCurrency = freezeCurrency;
    }

    public Integer getGiftStart() {
        return giftStart;
    }

    public void setGiftStart(Integer giftStart) {
        this.giftStart = giftStart;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode == null ? null : treeCode.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCardIndate() {
        return cardIndate;
    }

    public void setCardIndate(Date cardIndate) {
        this.cardIndate = cardIndate;
    }
}