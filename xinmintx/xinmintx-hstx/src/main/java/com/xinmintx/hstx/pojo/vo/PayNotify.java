package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

/**
 * 支付回调实体类
 */
@Data
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
}
