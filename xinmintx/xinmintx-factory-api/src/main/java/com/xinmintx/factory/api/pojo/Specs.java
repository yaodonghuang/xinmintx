package com.xinmintx.factory.api.pojo;

/**
 * @ClassName: com.xinmintx.factory.model.Specs
 * @Author:Pikachu
 * @Date: 2019/12/12 11:39
 * @Version: v1.0
 */

public class Specs {
    private Integer id;
    private Integer specId;
    private String value;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
