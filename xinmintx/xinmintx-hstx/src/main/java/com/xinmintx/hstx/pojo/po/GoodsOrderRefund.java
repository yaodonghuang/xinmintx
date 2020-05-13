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
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsOrderRefund对象", description="")
public class GoodsOrderRefund extends Model<GoodsOrderRefund> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单退款表主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "微信订单号")
    private String transactionId;

    @ApiModelProperty(value = "随机字符串，不长于32位")
    private String nonceStr;

    @ApiModelProperty(value = "签名")
    private String sign;

    @ApiModelProperty(value = "当return_code为FAIL时返回信息为错误原因 ，例如")
    private String returnMsg;

    @ApiModelProperty(value = "微信支付分配的商户号")
    private String mchId;

    @ApiModelProperty(value = "微信退款单号")
    private String refundId;

    @ApiModelProperty(value = "现金支付金额")
    private Integer cashFee;

    @ApiModelProperty(value = "商户退款单号")
    private String outTradeNo;

    @ApiModelProperty(value = "现金退款金额，单位为分，只能为整数")
    private Integer couponRefundFee;

    @ApiModelProperty(value = "退款渠道")
    private String refundChannel;

    @ApiModelProperty(value = "微信分配的公众账号ID")
    private String appid;

    @ApiModelProperty(value = "退款总金额,单位为分")
    private Integer refundFee;

    @ApiModelProperty(value = "订单总金额，单位为分，只能为整数")
    private Integer totalFee;

    @ApiModelProperty(value = "SUCCESS/FAIL")
    private String resultCode;

    @ApiModelProperty(value = "退款代金券使用数量")
    private Integer couponRefundCount;

    @ApiModelProperty(value = "现金退款金额，单位为分，只能为整数")
    private Integer cashRefundFee;

    @ApiModelProperty(value = "SUCCESS/FAIL")
    private String returnCode;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
