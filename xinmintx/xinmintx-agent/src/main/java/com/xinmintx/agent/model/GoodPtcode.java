package com.xinmintx.agent.model;

import java.util.Date;

public class GoodPtcode {
    private Integer id;

    private Integer uid;

    private Integer ptgoodsId;

    private String ptcode;

    private Integer ptnumber;

    private Date addtimeDatetime;

    private Date endtimeDatetime;

    private Integer common;

    private Integer status;

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

    public Integer getPtgoodsId() {
        return ptgoodsId;
    }

    public void setPtgoodsId(Integer ptgoodsId) {
        this.ptgoodsId = ptgoodsId;
    }

    public String getPtcode() {
        return ptcode;
    }

    public void setPtcode(String ptcode) {
        this.ptcode = ptcode == null ? null : ptcode.trim();
    }

    public Integer getPtnumber() {
        return ptnumber;
    }

    public void setPtnumber(Integer ptnumber) {
        this.ptnumber = ptnumber;
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

    public Integer getCommon() {
        return common;
    }

    public void setCommon(Integer common) {
        this.common = common;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}