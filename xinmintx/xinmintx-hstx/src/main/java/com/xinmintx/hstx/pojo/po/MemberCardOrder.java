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
 * 会员卡订单
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberCardOrder对象", description="会员卡订单")
public class MemberCardOrder extends Model<MemberCardOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单表id")
    private Integer orderId;

    @ApiModelProperty(value = "收获地址id")
    private Integer addressId;

    @ApiModelProperty(value = "用户留言")
    private String message;

    @ApiModelProperty(value = "订单类型(1,购买会员卡,2续费,3制卡,4补卡)")
    private Integer orderType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败)")
    private Integer payStatus;

    @ApiModelProperty(value = "订单状态订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败)")
    private Integer orderStatus;

    @ApiModelProperty(value = "快递单号")
    private String courierNumber;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
