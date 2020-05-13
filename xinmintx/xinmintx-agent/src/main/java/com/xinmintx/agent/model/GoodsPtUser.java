package com.xinmintx.agent.model;

import java.util.Date;

public class GoodsPtUser {
    private Integer id;

    private Integer userId;

    private Integer goodPtId;

    private String goodsId;

    private String commonB;

    private Date createTime;

    private Date updateTime;

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

    public Integer getGoodPtId() {
        return goodPtId;
    }

    public void setGoodPtId(Integer goodPtId) {
        this.goodPtId = goodPtId;
    }

    public String getgoodsId() {
        return goodsId;
    }

    public void setgoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getCommonB() {
        return commonB;
    }

    public void setCommonB(String commonB) {
        this.commonB = commonB == null ? null : commonB.trim();
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
}