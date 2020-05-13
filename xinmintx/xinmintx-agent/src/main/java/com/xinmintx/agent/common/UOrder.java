//package com.xinmintx.agent.common;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//import org.springframework.ui.Model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * <p>
// * 订单表
// * </p>
// *
// * @author wcj
// * @since 2019-12-25
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//public class UOrder  {
//
//    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    /**
//     * 创建订单的用户id
//     */
//    private Integer userId;
//
//    /**
//     * 订单号
//     */
//    private String orderNo;
//
//    /**
//     * 订单交易号
//     */
//    private String outTradeNo;
//
//    /**
//     * 第三方交易标示(微信，支付宝下单返回)
//     */
//    private String prepayId;
//
//    /**
//     * 订单描述(商品名称)
//     */
//    private String goodsDesc;
//
//    /**
//     * 支付金额(单位分)
//     */
//    private Long totalFee;
//
//    /**
//     * 支付方式(1,微信;2支付宝,3,银行卡)
//     */
//    private String payType;
//
//    /**
//     * 支付状态(0,未支付;1,已支付;2,已退款)
//     */
//    private String payStatus;
//
//    /**
//     * 支付交易号(微信支付宝支付完成后交易流水号)
//     */
//    private String transactionId;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 支付时间
//     */
//    private Date payTime;
//
//    /**
//     * 服务版本号
//     */
//    private String versionId;
//
//    /**
//     * 商户编号
//     */
//    private String merchantId;
//
//    /**
//     * 商品订单号
//     */
//    private String orderId;
//
//    /**
//     * 对账日期
//     */
//    private Date settleDate;
//
//    /**
//     * 完成时间
//     */
//    private Date completeDate;
//
//    /**
//     * 订单状态
//     */
//    private Integer status;
//
//    /**
//     * 通知类型
//     */
//    private Integer notifyTyp;
//
//    /**
//     * 支付系统交易号
//     */
//    private String payOrdNo;
//
//    /**
//     * 订单总金额
//     */
//    private BigDecimal orderAmt;
//
//    /**
//     * 异步通知URL
//     */
//    private String notifyUrl;
//
//    /**
//     * 签名方式
//     */
//    private String signType;
//
//    /**
//     * 签名信息
//     */
//    private String signature;
//
//    /**
//     * 附属信息
//     */
//    private String attach;
//
//
//
//}
