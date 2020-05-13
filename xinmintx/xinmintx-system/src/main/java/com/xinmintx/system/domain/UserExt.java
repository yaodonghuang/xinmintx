package com.xinmintx.system.domain;

import com.xinmintx.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:.UserExt
 * @author:chf
 * @Date:2020/1/14：16:58
 * @developerKits： win 10     jdk1.8
 */
public class UserExt extends BaseEntity {

    private Integer id;

    private String goodsDesc;

    private Integer payType;

    private Integer payStatus;

    private BigDecimal moneyChange;

    private String description;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(BigDecimal moneyChange) {
        this.moneyChange = moneyChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
