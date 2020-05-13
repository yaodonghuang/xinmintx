package com.xinmintx.system.domain;


import java.util.List;

public class Spec {

    private int goodId;
    private List<Specification> spec;
    private List<SpecParam> specParams;

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public List<Specification> getSpec() {
        return spec;
    }

    public void setSpec(List<Specification> spec) {
        this.spec = spec;
    }

    public List<SpecParam> getSpecParams() {
        return specParams;
    }

    public void setSpecParams(List<SpecParam> specParams) {
        this.specParams = specParams;
    }
}
