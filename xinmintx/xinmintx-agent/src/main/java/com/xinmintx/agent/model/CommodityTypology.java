package com.xinmintx.agent.model;

public class CommodityTypology {
    private Integer id;

    private Integer kindId;

    private String kindType;

    private Double kindPrice;

    private Integer kindRepertory;

    private String kindPicture;

    private Double seckillPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType == null ? null : kindType.trim();
    }

    public Double getKindPrice() {
        return kindPrice;
    }

    public void setKindPrice(Double kindPrice) {
        this.kindPrice = kindPrice;
    }

    public Integer getKindRepertory() {
        return kindRepertory;
    }

    public void setKindRepertory(Integer kindRepertory) {
        this.kindRepertory = kindRepertory;
    }

    public String getKindPicture() {
        return kindPicture;
    }

    public void setKindPicture(String kindPicture) {
        this.kindPicture = kindPicture == null ? null : kindPicture.trim();
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }
}