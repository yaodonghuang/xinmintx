package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;

    private Integer source;

    private Integer relateId;

    private Integer typeId;

    private String typeName;

    private Integer choiceness;

    private Integer hotSale;

    private Integer preorder;

    private String goodsListName;

    private String goodsName;

    private String content;

    private String turnsPhoto;

    private Integer speType;

    private BigDecimal price;

    private BigDecimal agencyPrice;

    private BigDecimal bazaarPrice;

    private BigDecimal procurementPrice;

    private BigDecimal linePrice;

    private Integer stockNum;

    private Integer salesInitial;

    private String activityTitle;

    private Integer salesActual;

    private Integer status;

    private Integer sort;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Long giftBag;

    private Long twoPrice;

    private Long threePrice;

    private Long fourPrice;

    private Long fivePrice;

    private Long sixPrice;

    private Long sevenPrice;

    private Long eightPrice;

    private Long ninePrice;

    private Long tenPrice;

    private String parameter;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        this.goodsListName = goodsListName == null ? null : goodsListName.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTurnsPhoto() {
        return turnsPhoto;
    }

    public void setTurnsPhoto(String turnsPhoto) {
        this.turnsPhoto = turnsPhoto == null ? null : turnsPhoto.trim();
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

    public BigDecimal getAgencyPrice() {
        return agencyPrice;
    }

    public void setAgencyPrice(BigDecimal agencyPrice) {
        this.agencyPrice = agencyPrice;
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
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
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

    public Long getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(Long giftBag) {
        this.giftBag = giftBag;
    }

    public Long getTwoPrice() {
        return twoPrice;
    }

    public void setTwoPrice(Long twoPrice) {
        this.twoPrice = twoPrice;
    }

    public Long getThreePrice() {
        return threePrice;
    }

    public void setThreePrice(Long threePrice) {
        this.threePrice = threePrice;
    }

    public Long getFourPrice() {
        return fourPrice;
    }

    public void setFourPrice(Long fourPrice) {
        this.fourPrice = fourPrice;
    }

    public Long getFivePrice() {
        return fivePrice;
    }

    public void setFivePrice(Long fivePrice) {
        this.fivePrice = fivePrice;
    }

    public Long getSixPrice() {
        return sixPrice;
    }

    public void setSixPrice(Long sixPrice) {
        this.sixPrice = sixPrice;
    }

    public Long getSevenPrice() {
        return sevenPrice;
    }

    public void setSevenPrice(Long sevenPrice) {
        this.sevenPrice = sevenPrice;
    }

    public Long getEightPrice() {
        return eightPrice;
    }

    public void setEightPrice(Long eightPrice) {
        this.eightPrice = eightPrice;
    }

    public Long getNinePrice() {
        return ninePrice;
    }

    public void setNinePrice(Long ninePrice) {
        this.ninePrice = ninePrice;
    }

    public Long getTenPrice() {
        return tenPrice;
    }

    public void setTenPrice(Long tenPrice) {
        this.tenPrice = tenPrice;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }
}