package com.xinmintx.merchant.model;

import java.util.Date;


public class StoreInformation {
    private Integer id;//店铺Id
    private String merchantName;//店铺名称
    private Double favorableRate;//好评率
    private Double perCapita;//人均消费
    private String type;//店铺分类
    private String giftName;//礼包名称
    private Double cashCoupon;//代金价格
    private Integer quantity;//剩余数量
    private Date endDate;//截止时间
    private Integer goodsId;//商品ID
    private String remark;//套餐备注
    private String photoUrl;//店铺图片
    private String giftPic;//礼包图片
    private String giftType;//礼包分类
    private String fullReducePrice;//满减价格
    private String address;//店铺地址

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullReducePrice() {
        return fullReducePrice;
    }

    public void setFullReducePrice(String fullReducePrice) {
        this.fullReducePrice = fullReducePrice;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getCashCoupon() {
        return cashCoupon;
    }

    public void setCashCoupon(Double cashCoupon) {
        this.cashCoupon = cashCoupon;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Double getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(Double favorableRate) {
        this.favorableRate = favorableRate;
    }

    public Double getPerCapita() {
        return perCapita;
    }

    public void setPerCapita(Double perCapita) {
        this.perCapita = perCapita;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
}
