package com.xinmintx.merchant.model;

import java.util.Date;

/**
 * @ClassName: com.xinmintx.merchant.model.communityOrder
 * @Author:Pikachu
 * @Date: 2020/2/22 14:01
 * @Version: v1.0
 */

public class CommunityOrder {
    private Integer id;//订单id
    private Integer memberId;//会员Id
    private Integer goodsId;//商品id
    private String goodsName;//商品名
    private String goodsPic;//商品图片
    private Long communityId;//社区id
    private String name;//用户名
    private Integer quantity;//数量
    private Double price;//单价
    private Date createTime;//下单时间
    private Long bigdecimal;//重量
    private Long total;//总计
    private Double totalAmount;//总计价格
    private Integer orderStatu;//订单状态
    private Integer orderState;//订单状态
    private Integer acceptOrder;//接受订单
 //   private Double totalPrice;//总计价格
    private String receiveName;//买家姓名
    private String receivePhone;//买家电话
    private String communityAddress;//收货地址



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBigdecimal() {
        return bigdecimal;
    }

    public void setBigdecimal(Long bigdecimal) {
        this.bigdecimal = bigdecimal;
    }

    public Long getTotal(){
        return total;
    }

    public void setTotal(CommunityOrder communityOrder) {
        this.total=communityOrder.getQuantity()*communityOrder.getBigdecimal();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderStatu() {
        return orderStatu;
    }

    public void setOrderStatu(Integer orderStatu) {
        this.orderStatu = orderStatu;
    }

    public Integer getAcceptOrder() {
        return acceptOrder;
    }

    public void setAcceptOrder(Integer acceptOrder) {
        this.acceptOrder = acceptOrder;
    }

/*    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }*/

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }


}
