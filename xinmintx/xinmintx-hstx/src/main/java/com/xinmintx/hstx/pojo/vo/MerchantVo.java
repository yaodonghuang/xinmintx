package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

@Data
public class MerchantVo {
    //用户token
    private String token;
    //身份证正面
    private String card1;
    //身份证反面
    private String card2;
    //名字
    private String name;
    //身份证号码
    private String idcard;
    //地址
    private String address;
    //手机号
    private String cellphone;
    //区域代码
    private String regionCode;
    //地区名称
    private String regionName;
    //入住类型
    private Integer select1;
    //商户类别
    private Integer select2;
    //银行卡号
    private String bank;
    //银行卡正面
    private String bank1;
    //银行卡反面
    private String bank2;
    //店内照片1
    private String card3;
    //店内照片1
    private String card4;
    //店内照片1
    private String card5;
    //营业执照照片
    private String card6;


}
