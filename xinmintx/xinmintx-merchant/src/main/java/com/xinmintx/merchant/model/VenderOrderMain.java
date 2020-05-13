package com.xinmintx.merchant.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: com.xinmintx.factory.model.VenderOrderMain
 * @Author:Pikachu
 * @Date: 2020/1/13 16:19
 * @Version: v1.0
 */

public class VenderOrderMain {
    private String orderId;
    private String receiveName;//买家姓名
    private String receivePhone;//买家电话
    private String receiveAddress;
    private Long uOrderId;// 支付订单id
    private String deputyInfo;// 内帮办配送费用
    private String consigneeInfo;// 提货点费用信息
    private Long merchantId;// 商户id
    private BigDecimal totalAmount;// 订单总金额
    private Long orderStatu;
    private Date createTime;
    private Integer printNumber;
    private Integer deputyHelp;
    private List<OrderExt> voList;

    public VenderOrderMain(){
        this.orderId = orderId;
    }

    public VenderOrderMain(String orderId, List<OrderExt> voList){
        this.orderId = orderId;
        this.voList = voList;
    }

    public String getConsigneeInfo() {
        return consigneeInfo;
    }

    public void setConsigneeInfo(String consigneeInfo) {
        this.consigneeInfo = consigneeInfo;
    }

    public String getDeputyInfo() {
        return deputyInfo;
    }

    public void setDeputyInfo(String deputyInfo) {
        this.deputyInfo = deputyInfo;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getuOrderId() {
        return uOrderId;
    }

    public void setuOrderId(Long uOrderId) {
        this.uOrderId = uOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderExt> getVoList() {
        return voList;
    }

    public void setVoList(List<OrderExt> voList) {
        this.voList = voList;
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

    public Long getOrderStatu() {
        return orderStatu;
    }

    public void setOrderStatu(Long orderStatu) {
        this.orderStatu = orderStatu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
    }

    public Integer getDeputyHelp() {
        return deputyHelp;
    }

    public void setDeputyHelp(Integer deputyHelp) {
        this.deputyHelp = deputyHelp;
    }
}
