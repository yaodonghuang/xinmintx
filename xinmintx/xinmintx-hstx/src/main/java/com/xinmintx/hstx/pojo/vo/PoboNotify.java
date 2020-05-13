package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

/**
 * 代付回调实体类
 */
@Data
public class PoboNotify {
    private String id;
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
}
