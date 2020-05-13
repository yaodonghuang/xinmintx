package com.xinmintx.factory.api.pojo;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.factory.model.VenderOrder
 * @Author:Pikachu
 * @Date: 2019/12/4 14:28
 * @Version: v1.0
 */

public class VenderOrder {
    private String receiveName;//买家姓名
    private String receivePhone;//买家电话
    private String receiveAddress;//买家地址
    private String goodsName;//商品名称
    private double price;//商品单价
    private Integer quantity;//商品数量
    private String specValueId;//商品sku记录索引
    private Map<String,String> paramMap;//规格
    private Date createTime;//创建时间
    private String dateTime;//时间戳
    private String goodsPic;//商品图片
    private String evaluate;//商品评价
    private Integer ifRemind;//用来标记是否被提醒,默认为0未提醒,1代表已提醒
    private String refundMsg;// 申请退款信息
    private Integer id;//子订单Id
    private Integer orderState;//订单状态
    private String returnsSingleNumber;//退货单号
    private String refundInformation;//退货原因
    private String returnMessage;//退款留言
    private String orderId;//主订单号
    private String courierNumber;//发货快递单号

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnsSingleNumber() {
        return returnsSingleNumber;
    }

    public void setReturnsSingleNumber(String returnsSingleNumber) {
        this.returnsSingleNumber = returnsSingleNumber;
    }

    public String getRefundInformation() {
        return refundInformation;
    }

    public void setRefundInformation(String refundInformation) {
        this.refundInformation = refundInformation;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefundMsg() {
        return refundMsg;
    }

    public void setRefundMsg(String refundMsg) {
        this.refundMsg = refundMsg;
    }

    public Integer getIfRemind() {
        return ifRemind;
    }

    public void setIfRemind(Integer ifRemind) {
        this.ifRemind = ifRemind;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
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

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(String specValueId) {
        this.specValueId = specValueId;
    }
}
