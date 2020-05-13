package com.xinmintx.agent.model;

/**
 * @ClassName:.commodityKindExt
 * @author:chf
 * @Date:2019/11/27：15:43
 * @developerKits： win 10     jdk1.8
 */
public class commodityKindExt {
    /**
     * 种类id
     */
    private int kindId;
    /**
     * 种类名称
     */
    private String kindName;
    /**
     * 种类颜色
     */
    private String type;
    /**
     * 颜色id
     */
    private int typeId;
    /**
     * 种类价格
     */
    private double kindPrice;
    /**
     * 种类库存
     */
    private int kindRepertory;
    /**
     * 种类图片
     */
    private String kindPicture;

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getKindPrice() {
        return kindPrice;
    }

    public void setKindPrice(double kindPrice) {
        this.kindPrice = kindPrice;
    }

    public int getKindRepertory() {
        return kindRepertory;
    }

    public void setKindRepertory(int kindRepertory) {
        this.kindRepertory = kindRepertory;
    }

    public String getKindPicture() {
        return kindPicture;
    }

    public void setKindPicture(String kindPicture) {
        this.kindPicture = kindPicture;
    }
}
