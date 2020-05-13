package com.xinmintx.agent.model;

/**
 * @ClassName:.DepositQuery
 * @author:chf
 * @Date:2019/12/25：15:55
 * @developerKits： win 10     jdk1.8
 */
public class DepositInform {
    //请求流水号
    private String requestSN;
    //交易返回码
    private String retCode;
    //交易返回信息
    private String retMsg;
    //商户号
    private String merchantId;
    //订单日期
    private String orderDate;
    //订单状态
    private String orderStatus;
    //订单交易时间
    private String orderTime;
    //代付金额
    private String txnAmt;
    //手续费
    private String fee;
    //验签字段
    private String signature;
    //异步通知URL
    private String notifyUrl;
    //加密方式
    private String signType;

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
}
