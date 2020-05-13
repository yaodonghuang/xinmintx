package com.xinmintx.merchant.model;

/**
 * @ClassName: com.xinmintx.merchant.model.OrderExt
 * @Author:Pikachu
 * @Date: 2020/2/22 19:21
 * @Version: v1.0
 */

public class OrderExt {
    private Integer id;
    private Integer goodsId;
    private Integer quantity;
    private Double price;
    private String goodsPic;
    private String name;
    private Long bigdecimal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBigdecimal() {
        return bigdecimal;
    }

    public void setBigdecimal(Long bigdecimal) {
        this.bigdecimal = bigdecimal;
    }

}
