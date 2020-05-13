package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;

/**
 * @ClassName:.GoodsTypeExt
 * @author:chf
 * @Date:2019/12/13：17:24
 * @developerKits： win 10     jdk1.8
 */
public class GoodsTypeExt {
    private Long id;
    //一级
    private Long parentId;
    //二级
    private Long twoParentId;

    private String typeName;

    private Long level;

    private String typephoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTwoParentId() {
        return twoParentId;
    }

    public void setTwoParentId(Long twoParentId) {
        this.twoParentId = twoParentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTypephoto() {
        return typephoto;
    }

    public void setTypephoto(String typephoto) {
        this.typephoto = typephoto;
    }
}
