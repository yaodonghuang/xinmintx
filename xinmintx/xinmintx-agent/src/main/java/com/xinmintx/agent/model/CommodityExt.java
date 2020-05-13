package com.xinmintx.agent.model;

import java.io.Serializable;

/**
 * @ClassName:.CommodityExt
 * @author:chf
 * @Date:2019/11/21：9:58
 * @developerKits： win 10     jdk1.8
 */
public class CommodityExt implements Serializable{
    /**
     *商品id
     */
    private int id;
    /**
     * 种类id
     */
    private int kindId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     *划线价格
     */
    private double linePrice;
    /**
     *实际价格
     */
    private double price;
    /**
     *销量
     */
    private int salesVolume;
    /**
     *详情页图片
     */
    private String ossUrl;
    /**
     * 种类图片
     */
    private String kindUrl;
    /**
     * 富文本
     */
    private String richUrl;
    /**
     *种类名称
     */
    private String kindName;
    /**
     *种类价格
     */
    private double kindPrice;
    /**
     * 种类型号
     */
    private String type;
    /**
     *库存
     */
    private int inventory;
    /**
     *商户名称
     */
    private String merchantName;
    /**
     *商户地址
     */
    private String address;
    /**
     * 商品参数
     */
    private String parameter;
    /**
     * 活动标题
     */
    private String activityTitle;
    /**
     * 销售额
     */
    private int market;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(double linePrice) {
        this.linePrice = linePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public double getKindPrice() {
        return kindPrice;
    }

    public void setKindPrice(double kindPrice) {
        this.kindPrice = kindPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getVendorName() {
        return merchantName;
    }

    public void setVendorName(String vendorName) {
        this.merchantName = vendorName;
    }

    public String getVendorUrl() {
        return address;
    }

    public void setVendorUrl(String vendorUrl) {
        this.address = vendorUrl;
    }

    public String getKindUrl() {
        return kindUrl;
    }

    public void setKindUrl(String kindUrl) {
        this.kindUrl = kindUrl;
    }

    public String getRichUrl() {
        return richUrl;
    }

    public void setRichUrl(String richUrl) {
        this.richUrl = richUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }
}
