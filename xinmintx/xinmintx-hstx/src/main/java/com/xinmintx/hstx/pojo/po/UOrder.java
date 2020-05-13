package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UOrder对象", description="订单表")
public class UOrder extends Model<UOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建订单的用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;

    @ApiModelProperty(value = "第三方交易标示(微信，支付宝下单返回)")
    private String prepayId;

    @ApiModelProperty(value = "订单描述(商品名称)")
    private String goodsDesc;

    @ApiModelProperty(value = "支付金额(单位分)")
    private Long totalFee;

    @ApiModelProperty(value = "支付方式(1,微信;2支付宝,3,银行卡)")
    private String payType;

    @ApiModelProperty(value = "支付状态(0,未支付;1,已支付;2,已退款)")
    private String payStatus;

    @ApiModelProperty(value = "支付交易号(微信支付宝支付完成后交易流水号)")
    private String transactionId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "服务版本号")
    private String versionId;

    @ApiModelProperty(value = "商户编号")
    private String merchantId;

    @ApiModelProperty(value = "商品订单号")
    private String orderId;

    @ApiModelProperty(value = "对账日期")
    private Date settleDate;

    @ApiModelProperty(value = "完成时间")
    private Date completeDate;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "通知类型")
    private Integer notifyTyp;

    @ApiModelProperty(value = "支付系统交易号")
    private String payOrdNo;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmt;

    @ApiModelProperty(value = "异步通知URL")
    private String notifyUrl;

    @ApiModelProperty(value = "签名方式")
    private String signType;

    @ApiModelProperty(value = "签名信息")
    private String signature;

    @ApiModelProperty(value = "附属信息")
    private String attach;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
