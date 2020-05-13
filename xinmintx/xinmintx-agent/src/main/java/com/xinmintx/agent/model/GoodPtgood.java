package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodPtgood {
    private Integer ptgoodsId;

    private Integer goodsId;

    private String ptgoodsName;

    private BigDecimal price;

    private BigDecimal ptPrice;

    private Integer ptSize;

    private Double ptValidhours;

    private Date startTime;

    private Date endTime;

    private Integer ptgoodsNumber;

    private String description;

    private String content;

    private String ptgoodsThumb;

    private String ptgoodsImgs;

    private Date addtime;

    private Date uptime;

    private Integer isSale;

    private Integer ptTimes;

    private Integer groupType;

    private String common;

    private String nameActivity;

    public Integer getPtgoodsId() {
        return ptgoodsId;
    }

    public void setPtgoodsId(Integer ptgoodsId) {
        this.ptgoodsId = ptgoodsId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getPtgoodsName() {
        return ptgoodsName;
    }

    public void setPtgoodsName(String ptgoodsName) {
        this.ptgoodsName = ptgoodsName == null ? null : ptgoodsName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPtPrice() {
        return ptPrice;
    }

    public void setPtPrice(BigDecimal ptPrice) {
        this.ptPrice = ptPrice;
    }

    public Integer getPtSize() {
        return ptSize;
    }

    public void setPtSize(Integer ptSize) {
        this.ptSize = ptSize;
    }

    public Double getPtValidhours() {
        return ptValidhours;
    }

    public void setPtValidhours(Double ptValidhours) {
        this.ptValidhours = ptValidhours;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPtgoodsNumber() {
        return ptgoodsNumber;
    }

    public void setPtgoodsNumber(Integer ptgoodsNumber) {
        this.ptgoodsNumber = ptgoodsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPtgoodsThumb() {
        return ptgoodsThumb;
    }

    public void setPtgoodsThumb(String ptgoodsThumb) {
        this.ptgoodsThumb = ptgoodsThumb == null ? null : ptgoodsThumb.trim();
    }

    public String getPtgoodsImgs() {
        return ptgoodsImgs;
    }

    public void setPtgoodsImgs(String ptgoodsImgs) {
        this.ptgoodsImgs = ptgoodsImgs == null ? null : ptgoodsImgs.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public Integer getPtTimes() {
        return ptTimes;
    }

    public void setPtTimes(Integer ptTimes) {
        this.ptTimes = ptTimes;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common == null ? null : common.trim();
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity == null ? null : nameActivity.trim();
    }
}