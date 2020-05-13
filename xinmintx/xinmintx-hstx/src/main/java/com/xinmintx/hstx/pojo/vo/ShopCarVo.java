package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ShopCarVo {
    private String shopName;
    private Integer shopId;
    private boolean check = false;
    private List<CarVo> cars;
}
