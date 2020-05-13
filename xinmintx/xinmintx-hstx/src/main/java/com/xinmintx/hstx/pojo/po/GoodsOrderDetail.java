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
 * 订单详情表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsOrderDetail对象", description="订单详情表")
public class GoodsOrderDetail extends Model<GoodsOrderDetail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单详情表主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员表id")
    private Integer memberId;

    @ApiModelProperty(value = "订单主表id")
    private Integer orderId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "sku id")
    private Integer skuId;

    @ApiModelProperty(value = "厂家id")
    private Integer factoryId;

    @ApiModelProperty(value = "商品单价(元)")
    private BigDecimal price;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品图片地址")
    private String goodsPic;

    @ApiModelProperty(value = "商品评价")
    private String evaluate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "快递单号,用于保存发货之后的快递单号")
    private String courierNumber;

    @ApiModelProperty(value = "订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败,11申请退货,12退货中,13退货成功,14退货失败)")
    private Integer orderState;

    @ApiModelProperty(value = "总金额(元)")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "是否缺货(0否,1是)")
    private Integer lack;

    @ApiModelProperty(value = "重量(克)")
    private Double weight;

    @ApiModelProperty(value = "用来标记是否被提醒,默认为0(未提醒),1(已提醒)")
    private Integer ifRemind;

    @ApiModelProperty(value = "退款信息")
    private String refundMsg;

    @ApiModelProperty(value = "退货单号")
    private String returnsSingleNumber;

    @ApiModelProperty(value = "是否付款,0:待付款,1:已付款,2:退款中,3已退款,4退款失败")
    private Integer ifPay;

    @ApiModelProperty(value = "退货原因")
    private String refundInformation;

    @ApiModelProperty(value = "退货留言")
    private String returnMessage;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
