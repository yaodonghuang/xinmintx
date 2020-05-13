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
@ApiModel(value="MemberOrder对象", description="订单表")
public class MemberOrder extends Model<MemberOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建订单的用户id")
    private Integer memberId;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
