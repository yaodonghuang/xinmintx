package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CarVo {

    //主键
    private Integer id;

    //商品id
    private Integer goodsId;

    //商户id
    private Integer shopId;

    //商户名字
    private String shopName;

    //商品名字
    private String goodsName;

    //规格名称
    private String spec;

    //价格
    private double price;

    //数量
    private Integer total;

    //创建时间
    private Date createTime;

    //商品图片
    private String imge;

    //skuId
    private Integer skuId;

    //库存
    private Integer stock;

    //商品类型
    private Integer type;

    private boolean checked = false;
}
