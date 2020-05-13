package com.xinmintx.agent.model;

/**
 * 代付回调实体类
 */
public class PoboNotify {
    private String requestSN;// 请求流水号
    private String retCode;// 交易返回码
    private String retMsg;// 交易返回信息
    private String merchantId;// 商户号
    private String orderDate;// 订单日期
    private String orderStatus;// 订单状态
    private String orderTime;// 订单交易时间
    private String txnAmt;// 代付金额
    private String fee;// 手续费
    private String notifyUrl;// 异步通知地址
    private String signType;// 加密算法
    private String signature;// 验签字段

    public String getRequestSN() {
        return requestSN;
    }

    public void setRequestSN(String requestSN) {
        this.requestSN = requestSN;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
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

    @Override
    public String toString() {
        return "PoboNotify{" +
                "requestSN='" + requestSN + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", fee='" + fee + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", signType='" + signType + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
