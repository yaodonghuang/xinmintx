package com.xinmintx.agent.model;

public class ProcurementCommodities {
    private Integer id;

    private Integer merchantId;

    private String sampleName;

    private String sampleType;

    private String trackingNumber;

    private String supplier;

    private String cellphone;

    private String detailedAddress;

    private Double marketValue;

    private Double procurementPrice;

    private Integer pictureId;

    private String commonone;

    private String commontwo;

    private Integer isReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName == null ? null : sampleName.trim();
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType == null ? null : sampleType.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getProcurementPrice() {
        return procurementPrice;
    }

    public void setProcurementPrice(Double procurementPrice) {
        this.procurementPrice = procurementPrice;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getCommonone() {
        return commonone;
    }

    public void setCommonone(String commonone) {
        this.commonone = commonone == null ? null : commonone.trim();
    }

    public String getCommontwo() {
        return commontwo;
    }

    public void setCommontwo(String commontwo) {
        this.commontwo = commontwo == null ? null : commontwo.trim();
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }
}