package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.Date;

public class Merchant {
    private Integer id;

    private Integer orderId;

    private String name;

    private String idcard;

    private Integer recommender;

    private String merchantName;

    private String cellphone;

    private String bankCard;

    private Integer merchantType;

    private Integer merchantTable;

    private String address;

    private String regionName;

    private String regionCode;

    private Integer merchantBranchOfficeId;

    private BigDecimal turnover;

    private Integer isReview;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer merchantCategory;

    private BigDecimal perCapita;

    private String token;

    private BigDecimal freezingAmount;

    private BigDecimal cashAmount;

    private String shopName;

    private String shopAddress;

    private String avatar;

    private String announcement;

    private String idcardFront;

    private String idcardBack;

    private String bankCardFront;

    private String bankCardBack;

    private String doorHeadPhoto;

    private String storePhotoOne;

    private String storePhotoTwo;

    private String businessLicense;

    private String otherPhoto;

    private Long latitude;

    private String longitude;

    private String shopSpecification;

    private String businessModel;

    private BigDecimal amountRaised;

    private String merchantCategoryDetail;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getMerchantTable() {
        return merchantTable;
    }

    public void setMerchantTable(Integer merchantTable) {
        this.merchantTable = merchantTable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public Integer getMerchantBranchOfficeId() {
        return merchantBranchOfficeId;
    }

    public void setMerchantBranchOfficeId(Integer merchantBranchOfficeId) {
        this.merchantBranchOfficeId = merchantBranchOfficeId;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Integer getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(Integer merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public BigDecimal getPerCapita() {
        return perCapita;
    }

    public void setPerCapita(BigDecimal perCapita) {
        this.perCapita = perCapita;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement == null ? null : announcement.trim();
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront == null ? null : idcardFront.trim();
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack == null ? null : idcardBack.trim();
    }

    public String getBankCardFront() {
        return bankCardFront;
    }

    public void setBankCardFront(String bankCardFront) {
        this.bankCardFront = bankCardFront == null ? null : bankCardFront.trim();
    }

    public String getBankCardBack() {
        return bankCardBack;
    }

    public void setBankCardBack(String bankCardBack) {
        this.bankCardBack = bankCardBack == null ? null : bankCardBack.trim();
    }

    public String getDoorHeadPhoto() {
        return doorHeadPhoto;
    }

    public void setDoorHeadPhoto(String doorHeadPhoto) {
        this.doorHeadPhoto = doorHeadPhoto == null ? null : doorHeadPhoto.trim();
    }

    public String getStorePhotoOne() {
        return storePhotoOne;
    }

    public void setStorePhotoOne(String storePhotoOne) {
        this.storePhotoOne = storePhotoOne == null ? null : storePhotoOne.trim();
    }

    public String getStorePhotoTwo() {
        return storePhotoTwo;
    }

    public void setStorePhotoTwo(String storePhotoTwo) {
        this.storePhotoTwo = storePhotoTwo == null ? null : storePhotoTwo.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getOtherPhoto() {
        return otherPhoto;
    }

    public void setOtherPhoto(String otherPhoto) {
        this.otherPhoto = otherPhoto == null ? null : otherPhoto.trim();
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getShopSpecification() {
        return shopSpecification;
    }

    public void setShopSpecification(String shopSpecification) {
        this.shopSpecification = shopSpecification == null ? null : shopSpecification.trim();
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel == null ? null : businessModel.trim();
    }

    public BigDecimal getAmountRaised() {
        return amountRaised;
    }

    public void setAmountRaised(BigDecimal amountRaised) {
        this.amountRaised = amountRaised;
    }

    public String getMerchantCategoryDetail() {
        return merchantCategoryDetail;
    }

    public void setMerchantCategoryDetail(String merchantCategoryDetail) {
        this.merchantCategoryDetail = merchantCategoryDetail == null ? null : merchantCategoryDetail.trim();
    }
}