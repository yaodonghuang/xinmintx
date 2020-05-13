package com.xinmintx.system.domain;

import java.util.List;

public class SpecSelect {

    private int id;
    private String name;
    private String textCode;
    private String code;
    private int nameCode;
    private List<SpecSelectValue> values;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextCode() {
        return textCode;
    }

    public List<SpecSelectValue> getValues() {
        return values;
    }

    public void setValues(List<SpecSelectValue> values) {
        this.values = values;
    }

    public void setTextCode(String textCode) {
        this.textCode = textCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNameCode() {
        return nameCode;
    }

    public void setNameCode(int nameCode) {
        this.nameCode = nameCode;
    }
}
