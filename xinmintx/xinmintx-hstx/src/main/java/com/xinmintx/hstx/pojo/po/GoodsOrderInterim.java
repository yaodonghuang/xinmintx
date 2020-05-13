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
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsOrderInterim对象", description="")
public class GoodsOrderInterim extends Model<GoodsOrderInterim> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上级id")
    private Integer orderId;

    @ApiModelProperty(value = "会员id")
    private Integer memberId;

    @ApiModelProperty(value = "订单编号")
    private String orderNum;

    @ApiModelProperty(value = "订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败)")
    private Integer orderState;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否付款,0:待付款,1:已付款,2:退款中,3已退款,4退款失败")
    private Integer ifPay;

    @ApiModelProperty(value = "收件地址")
    private String receiveAddress;

    @ApiModelProperty(value = "买家姓名")
    private String receiveName;

    @ApiModelProperty(value = "买家电话")
    private String receivePhone;

    @ApiModelProperty(value = "买家留言")
    private String receiveMessage;

    @ApiModelProperty(value = "总金额(元)")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "支付订单id")
    private Integer uOrderId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "skuId")
    private Integer skuId;

    @ApiModelProperty(value = "厂家id")
    private Integer factoryId;

    @ApiModelProperty(value = "商品单价(元)")
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品图片")
    private String goodsPic;

    @ApiModelProperty(value = "商品评价")
    private String evaluate;

    @ApiModelProperty(value = "快递单号")
    private String courierNumber;

    @ApiModelProperty(value = "是否删除,0(未删除)1(已删除)")
    private Integer ifDelete;

    @ApiModelProperty(value = "重量(克)")
    private Double weight;

    @ApiModelProperty(value = "用户是否删除,0(未删除),1(已删除)")
    private Integer userDelete;

    @ApiModelProperty(value = "用来标记是否被提醒,默认为0(未提醒),1(已提醒)")
    private Integer ifRemind;

    @ApiModelProperty(value = "退款信息")
    private String refundMsg;

    @ApiModelProperty(value = "订单类型(1普通购买,2拼团,3.社区菜篮子订单,4抢购)")
    private Integer orderType;

    @ApiModelProperty(value = "发货日期")
    private Date sendDate;

    @ApiModelProperty(value = "延长收货,0不延长,1延长")
    private Integer ifDelayed;

    @ApiModelProperty(value = "地址表关联")
    private Integer addressId;

    @ApiModelProperty(value = "分润状态(1,已冻结 2,已到账 3 已退款)")
    private Integer dividedState;

    @ApiModelProperty(value = "退货原因")
    private String refundInformation;

    @ApiModelProperty(value = "退货留言")
    private String returnMessage;

    @ApiModelProperty(value = "退款时间")
    private Date refundTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
