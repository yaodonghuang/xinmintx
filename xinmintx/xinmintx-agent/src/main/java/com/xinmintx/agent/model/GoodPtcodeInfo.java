package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodPtcodeInfo {
    private Integer id;

    private Integer uid;

    private Integer skuId;

    private Integer pid;

    private String ptcode;

    private Integer ptgoodId;

    private BigDecimal perPrice;

    private Integer dreessId;

    private String dreessName;

    private Date addtimeDatetime;

    private Date endtimeDatetime;

    private Integer number;

    private Integer isHeader;

    private Integer lack;

    private Integer isJoin;

    private Integer goodsOrderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPtcode() {
        return ptcode;
    }

    public void setPtcode(String ptcode) {
        this.ptcode = ptcode == null ? null : ptcode.trim();
    }

    public Integer getPtgoodId() {
        return ptgoodId;
    }

    public void setPtgoodId(Integer ptgoodId) {
        this.ptgoodId = ptgoodId;
    }

    public BigDecimal getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(BigDecimal perPrice) {
        this.perPrice = perPrice;
    }

    public Integer getDreessId() {
        return dreessId;
    }

    public void setDreessId(Integer dreessId) {
        this.dreessId = dreessId;
    }

    public String getDreessName() {
        return dreessName;
    }

    public void setDreessName(String dreessName) {
        this.dreessName = dreessName == null ? null : dreessName.trim();
    }

    public Date getAddtimeDatetime() {
        return addtimeDatetime;
    }

    public void setAddtimeDatetime(Date addtimeDatetime) {
        this.addtimeDatetime = addtimeDatetime;
    }

    public Date getEndtimeDatetime() {
        return endtimeDatetime;
    }

    public void setEndtimeDatetime(Date endtimeDatetime) {
        this.endtimeDatetime = endtimeDatetime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(Integer isHeader) {
        this.isHeader = isHeader;
    }

    public Integer getLack() {
        return lack;
    }

    public void setLack(Integer lack) {
        this.lack = lack;
    }

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }

    public Integer getGoodsOrderId() {
        return goodsOrderId;
    }

    public void setGoodsOrderId(Integer goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }
}