package com.xinmintx.factory.model;

/**
 * @ClassName: com.xinmintx.factory.model.ThirdPartyPayment
 * @Author:Pikachu
 * @Date: 2019/12/19 14:16
 * @Version: v1.0
 */

public class ThirdPartyPayment {
    private String orderId;//(商户平台生成的用于标识该笔订单的唯一号码)
    private Double orderAmount;//(订单金额 以人民币分为单位)
    private String prdName;//(商品名称)
    private String prdDesc;//(商品描述)
    private Long openid;//(用户openid)
    private String retUrl;//(结果返回URL，仅适用于立即返回处理结果的接口)
    private String refundOrdNo;//(退款订单号)
    private Double rfAmt;//(以人民币分为单位)
    private String rfSake;//(退款理由)
    private String notifyUrl;//(针对该交易的交易状态同步通知接收URL)

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getPrdDesc() {
        return prdDesc;
    }

    public void setPrdDesc(String prdDesc) {
        this.prdDesc = prdDesc;
    }

    public Long getOpenid() {
        return openid;
    }

    public void setOpenid(Long openid) {
        this.openid = openid;
    }

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl;
    }

    public String getRefundOrdNo() {
        return refundOrdNo;
    }

    public void setRefundOrdNo(String refundOrdNo) {
        this.refundOrdNo = refundOrdNo;
    }

    public Double getRfAmt() {
        return rfAmt;
    }

    public void setRfAmt(Double rfAmt) {
        this.rfAmt = rfAmt;
    }

    public String getRfSake() {
        return rfSake;
    }

    public void setRfSake(String rfSake) {
        this.rfSake = rfSake;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
