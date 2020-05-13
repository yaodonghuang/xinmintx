package com.xinmintx.factory.model;

import java.math.BigDecimal;
import java.util.Date;

public class MemberCurrencyRecord {
    private Integer id;

    private Integer memberId;

    private BigDecimal currencyChange;

    private String description;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getCurrencyChange() {
        return currencyChange;
    }

    public void setCurrencyChange(BigDecimal currencyChange) {
        this.currencyChange = currencyChange;
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
}
