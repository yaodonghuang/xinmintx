package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/11 0011
 * @time: 下午 22:08
 * @Description:
 */

public class GoodsBean {
    private Long id;
    private Long source;
    private Long relateId;
    private Long parentId;
    private Integer choiceness;
    private Integer hotSale;
    private Integer preorder;
    private String goodsListName;
    private String goodsName;
    private String content;
    private String turnsPhoto;
    private Integer speType;
    private Double price;
    private Double agencyPrice;
    private Double bazaarPrice;
    private Double procurementPrice;
    private Double linePrice;
    private Long giftBag;
    private Double twoPrice;
    private Double threePrice;
    private Double fourPrice;
    private Double fivePrice;
    private Double sixPrice;
    private Double sevenPrice;
    private Double eightPrice;
    private Double ninePrice;
    private Double tenPrice;
    private Double agentPrice;
    private Long stockNum;
    private Long salesInitial;
    private String activityTitle;
    private Long salesActual;
    private Integer status;
    private Long sort;
    private Integer isDelete;
    private String specName;
    private String[] specValue;
    private String[] skuId;
    private Integer[] skuStockNum;
    private Double[] skuPrice;
    private String[] skuPhoto;
    private String[] turnsPhotoList;
    private Double[] goodsWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChoiceness() {
        return choiceness;
    }

    public void setChoiceness(Integer choiceness) {
        this.choiceness = choiceness;
    }

    public Integer getHotSale() {
        return hotSale;
    }

    public void setHotSale(Integer hotSale) {
        this.hotSale = hotSale;
    }

    public Integer getPreorder() {
        return preorder;
    }

    public void setPreorder(Integer preorder) {
        this.preorder = preorder;
    }

    public String getGoodsListName() {
        return goodsListName;
    }

    public void setGoodsListName(String goodsListName) {
        this.goodsListName = goodsListName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTurnsPhoto() {
        return turnsPhoto;
    }

    public void setTurnsPhoto(String turnsPhoto) {
        this.turnsPhoto = turnsPhoto;
    }

    public Integer getSpeType() {
        return speType;
    }

    public void setSpeType(Integer speType) {
        this.speType = speType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getBazaarPrice() {
        return bazaarPrice;
    }

    public void setBazaarPrice(Double bazaarPrice) {
        this.bazaarPrice = bazaarPrice;
    }

    public Double getProcurementPrice() {
        return procurementPrice;
    }

    public void setProcurementPrice(Double procurementPrice) {
        this.procurementPrice = procurementPrice;
    }

    public Double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(Double linePrice) {
        this.linePrice = linePrice;
    }

    public Long getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(Long giftBag) {
        this.giftBag = giftBag;
    }

    public Double getTwoPrice() {
        return twoPrice;
    }

    public void setTwoPrice(Double twoPrice) {
        this.twoPrice = twoPrice;
    }

    public Double getThreePrice() {
        return threePrice;
    }

    public void setThreePrice(Double threePrice) {
        this.threePrice = threePrice;
    }

    public Double getFourPrice() {
        return fourPrice;
    }

    public void setFourPrice(Double fourPrice) {
        this.fourPrice = fourPrice;
    }

    public Double getFivePrice() {
        return fivePrice;
    }

    public void setFivePrice(Double fivePrice) {
        this.fivePrice = fivePrice;
    }

    public Double getSixPrice() {
        return sixPrice;
    }

    public void setSixPrice(Double sixPrice) {
        this.sixPrice = sixPrice;
    }

    public Double getSevenPrice() {
        return sevenPrice;
    }

    public void setSevenPrice(Double sevenPrice) {
        this.sevenPrice = sevenPrice;
    }

    public Double getEightPrice() {
        return eightPrice;
    }

    public void setEightPrice(Double eightPrice) {
        this.eightPrice = eightPrice;
    }

    public Double getNinePrice() {
        return ninePrice;
    }

    public void setNinePrice(Double ninePrice) {
        this.ninePrice = ninePrice;
    }

    public Double getTenPrice() {
        return tenPrice;
    }

    public void setTenPrice(Double tenPrice) {
        this.tenPrice = tenPrice;
    }

    public Double getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Double agentPrice) {
        this.agentPrice = agentPrice;
    }

    public Long getStockNum() {
        return stockNum;
    }

    public void setStockNum(Long stockNum) {
        this.stockNum = stockNum;
    }

    public Long getSalesInitial() {
        return salesInitial;
    }

    public void setSalesInitial(Long salesInitial) {
        this.salesInitial = salesInitial;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Long getSalesActual() {
        return salesActual;
    }

    public void setSalesActual(Long salesActual) {
        this.salesActual = salesActual;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String[] getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String[] specValue) {
        this.specValue = specValue;
    }

    public String[] getSkuId() {
        return skuId;
    }

    public void setSkuId(String[] skuId) {
        this.skuId = skuId;
    }

    public Integer[] getSkuStockNum() {
        return skuStockNum;
    }

    public void setSkuStockNum(Integer[] skuStockNum) {
        this.skuStockNum = skuStockNum;
    }

    public Double[] getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double[] skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String[] getSkuPhoto() {
        return skuPhoto;
    }

    public void setSkuPhoto(String[] skuPhoto) {
        this.skuPhoto = skuPhoto;
    }

    public String[] getTurnsPhotoList() {
        return turnsPhotoList;
    }

    public void setTurnsPhotoList(String[] turnsPhotoList) {
        this.turnsPhotoList = turnsPhotoList;
    }

    public Double[] getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double[] goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Double getAgencyPrice() {
        return agencyPrice;
    }

    public void setAgencyPrice(Double agencyPrice) {
        this.agencyPrice = agencyPrice;
    }
}
