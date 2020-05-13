package com.xinmintx.factory.api.pojo;


import java.math.BigDecimal;
import java.util.Date;

public class GoodsOrder {

  private Integer id;//订单主键id
  private Integer memberId;//会员id
  private String orderNum;//订单编号
  private Integer orderState;//订单状态(1待付款,2待发货,3待收货,4待评价)
  private Date createTime;//创建时间
  private Date updateTime;//更新时间
  private Integer ifPay;//是否付款,0:待付款,1:已付款,2:已退款
  private String receiveAddress;//收件地址
  private String receiveName;//买家姓名
  private String receivePhone;//买家电话
  private String receiveMessage;//买家留言
  private BigDecimal totalAmount;//总金额(元)
  private Integer uOrderId;//支付订单id
  private Integer goodsId;//商品id
  private Integer skuId;
  private Integer factoryId;//厂家id
  private double price;//商品单价(元)
  private Integer quantity;//购买数量
  private String goodsName;//商品名称
  private String goodsPic;//商品图片
  private String evaluate;//商品评价
  private String courierNumber;//快递单号
  private Integer ifDelete;//是否删除,0(未删除)1(已删除)
  private Integer ifRemind;//用来标记是否被提醒,默认为0未提醒,1代表已提醒
  private Date refundTime;//申请退款时间

  public Date getRefundTime() {
    return refundTime;
  }

  public void setRefundTime(Date refundTime) {
    this.refundTime = refundTime;
  }

  public Integer getuOrderId() {
    return uOrderId;
  }

  public void setuOrderId(Integer uOrderId) {
    this.uOrderId = uOrderId;
  }

  public Integer getIfRemind() {
    return ifRemind;
  }

  public void setIfRemind(Integer ifRemind) {
    this.ifRemind = ifRemind;
  }

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


  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }


  public Integer getOrderState() {
    return orderState;
  }

  public void setOrderState(Integer orderState) {
    this.orderState = orderState;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }


  public Integer getIfPay() {
    return ifPay;
  }

  public void setIfPay(Integer ifPay) {
    this.ifPay = ifPay;
  }


  public String getReceiveAddress() {
    return receiveAddress;
  }

  public void setReceiveAddress(String receiveAddress) {
    this.receiveAddress = receiveAddress;
  }


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


  public String getReceiveMessage() {
    return receiveMessage;
  }

  public void setReceiveMessage(String receiveMessage) {
    this.receiveMessage = receiveMessage;
  }


  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }


  public Integer getUOrderId() {
    return uOrderId;
  }

  public void setUOrderId(Integer uOrderId) {
    this.uOrderId = uOrderId;
  }


  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }


  public Integer getSkuId() {
    return skuId;
  }

  public void setSkuId(Integer skuId) {
    this.skuId = skuId;
  }


  public Integer getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Integer factoryId) {
    this.factoryId = factoryId;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
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


  public String getEvaluate() {
    return evaluate;
  }

  public void setEvaluate(String evaluate) {
    this.evaluate = evaluate;
  }


  public String getCourierNumber() {
    return courierNumber;
  }

  public void setCourierNumber(String courierNumber) {
    this.courierNumber = courierNumber;
  }


  public Integer getIfDelete() {
    return ifDelete;
  }

  public void setIfDelete(Integer ifDelete) {
    this.ifDelete = ifDelete;
  }

}
