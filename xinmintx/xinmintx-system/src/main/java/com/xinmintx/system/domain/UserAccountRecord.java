package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccountRecord {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer userAccountId;

    private BigDecimal moneyChange;

    private String description;

    private Date createTime;

    private BigDecimal changPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
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
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getChangPrice() {
        return changPrice;
    }

    public void setChangPrice(BigDecimal changPrice) {
        this.changPrice = changPrice;
    }
}