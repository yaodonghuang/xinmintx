package com.xinmintx.system.domain;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/12 0012
 * @time: 下午 17:00
 * @Description:
 */
public class SpecValue {

    private Integer specId;
    private Integer valueCode;
    private String valueName;

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getValueCode() {
        return valueCode;
    }

    public void setValueCode(Integer valueCode) {
        this.valueCode = valueCode;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
