package com.xinmintx.factory.model.dto;

import com.xinmintx.factory.model.po.FactoryName;
import lombok.Data;

import java.util.List;

@Data
public class FactoryNameDto extends FactoryName {
    //轮播图地址
    private List<String> list;
}
