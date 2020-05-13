package com.xinmintx.agent.model;

/**
 * 支付回调实体类
 */
public class PayNotify {
    private String versionId;// 服务版本号
    private String merchantId;// 商户编号
    private String orderId;// 商品订单号
    private String settleDate;// 对账日期
    private String completeDate;// 完成时间
    private String status;// 订单状态
    private String notifyTyp;// 通知类型
    private String payOrdNo;// 支付系统交易号
    private String orderAmt;// 订单总金额
    private String notifyUrl;// 异步通知URL
    private String signType;// 签名方式
    private String signature;// 签名信息
    private String attach;// 附属信息

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotifyTyp() {
        return notifyTyp;
    }

    public void setNotifyTyp(String notifyTyp) {
        this.notifyTyp = notifyTyp;
    }

    public String getPayOrdNo() {
        return payOrdNo;
    }

    public void setPayOrdNo(String payOrdNo) {
        this.payOrdNo = payOrdNo;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return "PayNotify{" +
                "versionId='" + versionId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", settleDate='" + settleDate + '\'' +
                ", completeDate='" + completeDate + '\'' +
                ", status='" + status + '\'' +
                ", notifyTyp='" + notifyTyp + '\'' +
                ", payOrdNo='" + payOrdNo + '\'' +
                ", orderAmt='" + orderAmt + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", signType='" + signType + '\'' +
                ", signature='" + signature + '\'' +
                ", attach='" + attach + '\'' +
                '}';
    }
}
