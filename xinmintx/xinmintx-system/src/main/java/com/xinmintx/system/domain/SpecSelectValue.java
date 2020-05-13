package com.xinmintx.system.domain;

public class SpecSelectValue implements Comparable<SpecSelectValue>{

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(SpecSelectValue o) {
        return this.code.compareTo(o.getCode());
    }
}
