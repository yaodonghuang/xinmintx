package com.xinmintx.system.domain;

/**
 * @ClassName:.GoodsTypeBean
 * @author:chf
 * @Date:2019/12/12：17:31
 * @developerKits： win 10     jdk1.8
 */
public class GoodsTypeBean {
    private Integer id;

    private String oneTypeName;

    private Integer oneTypeId;
    private String twoTypeName;

    private Integer twoTypeId;
    private Integer threeTypeId;
    private String threeTypeName;
    private String typePhoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOneTypeName() {
        return oneTypeName;
    }

    public void setOneTypeName(String oneTypeName) {
        this.oneTypeName = oneTypeName;
    }

    public Integer getOneTypeId() {
        return oneTypeId;
    }

    public void setOneTypeId(Integer oneTypeId) {
        this.oneTypeId = oneTypeId;
    }

    public String getTwoTypeName() {
        return twoTypeName;
    }

    public void setTwoTypeName(String twoTypeName) {
        this.twoTypeName = twoTypeName;
    }

    public Integer getTwoTypeId() {
        return twoTypeId;
    }

    public void setTwoTypeId(Integer twoTypeId) {
        this.twoTypeId = twoTypeId;
    }

    public Integer getThreeTypeId() {
        return threeTypeId;
    }

    public void setThreeTypeId(Integer threeTypeId) {
        this.threeTypeId = threeTypeId;
    }

    public String getThreeTypeName() {
        return threeTypeName;
    }

    public void setThreeTypeName(String threeTypeName) {
        this.threeTypeName = threeTypeName;
    }

    public String getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(String typePhoto) {
        this.typePhoto = typePhoto;
    }
}
