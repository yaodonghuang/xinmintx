package com.xinmintx.agent.model;

public class StaffProfit {
    private Integer id;

    private Integer userId;

    private Double profit;

    private Integer merchantType;

    private Double percent;

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

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}