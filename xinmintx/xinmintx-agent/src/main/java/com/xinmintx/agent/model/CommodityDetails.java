package com.xinmintx.agent.model;

import java.util.Date;

public class CommodityDetails {
    private Integer id;

    private Integer commodityId;

    private String ossUrl;

    private Integer detailsType;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl == null ? null : ossUrl.trim();
    }

    public Integer getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(Integer detailsType) {
        this.detailsType = detailsType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}