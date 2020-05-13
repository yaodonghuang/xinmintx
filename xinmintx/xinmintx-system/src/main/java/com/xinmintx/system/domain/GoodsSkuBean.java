package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:.GoodsSkuBean
 * @author:chf
 * @Date:2019/12/19：15:29
 * @developerKits： win 10     jdk1.8
 */
public class GoodsSkuBean {
    private Integer id;

    private String skuId;

    private String specName;

    private String specValue;

    private Integer goodsId;

    private String specValueId;

    private BigDecimal price;

    private BigDecimal agentPrice;

    private BigDecimal linePrice;

    private Integer stockNum;

    private Integer photoId;

    private String photo;

    private Integer goodsSales;

    private Double goodsWeight;

    private Date createTime;

    private Date updateTime;

    private BigDecimal ePrice;

    private BigDecimal glodPrice;

    private BigDecimal purchasePrice;

    private List<GoodsSkuBean> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(String specValueId) {
        this.specValueId = specValueId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(Integer goodsSales) {
        this.goodsSales = goodsSales;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
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

    public BigDecimal getePrice() {
        return ePrice;
    }

    public void setePrice(BigDecimal ePrice) {
        this.ePrice = ePrice;
    }

    public BigDecimal getGlodPrice() {
        return glodPrice;
    }

    public void setGlodPrice(BigDecimal glodPrice) {
        this.glodPrice = glodPrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public List<GoodsSkuBean> getValues() {
        return values;
    }

    public void setValues(List<GoodsSkuBean> values) {
        this.values = values;
    }
}
