package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:.GoodsExt
 * @author:chf
 * @Date:2019/12/11：11:15
 * @developerKits： win 10     jdk1.8
 */
public class GoodsExt {
    private Integer id;

    private Integer source;

    private Integer relateId;

    private Integer typeId;

    private Integer choiceness;

    private Integer hotSale;

    private Integer preorder;

    private String typeName;

    private String goodsListName;

    private String goodsName;

    private String content;

    private String turnsPhoto;

    private Integer speType;

    private BigDecimal agencyPrice;

    private BigDecimal bazaarPrice;

    private BigDecimal procurementPrice;

    private BigDecimal price;

    private BigDecimal linePrice;

    private Integer stockNum;

    private Integer salesInitial;

    private String activityTitle;

    private Integer salesActual;

    private Integer status;

    private Integer sort;

    private Integer isDelete;

    private Long giftBag;

    public BigDecimal getAgencyPrice() {
        return agencyPrice;
    }

    public void setAgencyPrice(BigDecimal agencyPrice) {
        this.agencyPrice = agencyPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBazaarPrice() {
        return bazaarPrice;
    }

    public void setBazaarPrice(BigDecimal bazaarPrice) {
        this.bazaarPrice = bazaarPrice;
    }

    public BigDecimal getProcurementPrice() {
        return procurementPrice;
    }

    public void setProcurementPrice(BigDecimal procurementPrice) {
        this.procurementPrice = procurementPrice;
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

    public Integer getSalesInitial() {
        return salesInitial;
    }

    public void setSalesInitial(Integer salesInitial) {
        this.salesInitial = salesInitial;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getSalesActual() {
        return salesActual;
    }

    public void setSalesActual(Integer salesActual) {
        this.salesActual = salesActual;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(Long giftBag) {
        this.giftBag = giftBag;
    }
}
