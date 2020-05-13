package com.xinmintx.system.domain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/12 0012
 * @time: 下午 16:53
 * @Description:
 */
public class SpecParam {

    private String specId;
    private String specName;
    private List<SpecValue> values;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public List<SpecValue> getValues() {
        return values;
    }

    public void setValues(List<SpecValue> values) {
        this.values = values;
    }
}
