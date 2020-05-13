package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会员卡订单对象 member_card_order
 * 
 * @author xinmintx
 * @date 2020-02-15
 */
public class MemberCardOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 订单表id */
    @Excel(name = "订单表id")
    private Long orderId;

    /** 收获地址id */
    @Excel(name = "收获地址id")
    private Long addressId;

    /** 用户留言 */
    @Excel(name = "用户留言")
    private String message;

    /** 订单类型(1,购买会员卡,2续费,3制卡,4补卡) */
    @Excel(name = "订单类型(1,购买会员卡,2续费,3制卡,4补卡)")
    private Long orderType;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double totalPrice;

    /** 支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败) */
    @Excel(name = "支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败)")
    private Long payStatus;

    /** 订单状态订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败) */
    @Excel(name = "订单状态订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败)")
    private Long orderStatus;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String courierNumber;

    /**创建时间*/
    @Excel(name="创建时间")
    private Date createTime;

    /**修改时间*/
    @Excel(name="修改时间")
    private Date updateTime;

    /** 会员卡号 */
    @Excel(name = "会员卡号")
    private String cardNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setOrderType(Long orderType) 
    {
        this.orderType = orderType;
    }

    public Long getOrderType() 
    {
        return orderType;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() 
    {
        return totalPrice;
    }
    public void setPayStatus(Long payStatus) 
    {
        this.payStatus = payStatus;
    }

    public Long getPayStatus() 
    {
        return payStatus;
    }
    public void setOrderStatus(Long orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus() 
    {
        return orderStatus;
    }
    public void setCourierNumber(String courierNumber) 
    {
        this.courierNumber = courierNumber;
    }

    public String getCourierNumber() 
    {
        return courierNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("addressId", getAddressId())
            .append("message", getMessage())
            .append("orderType", getOrderType())
            .append("description", getDescription())
            .append("totalPrice", getTotalPrice())
            .append("payStatus", getPayStatus())
            .append("orderStatus", getOrderStatus())
            .append("courierNumber", getCourierNumber())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("cardNumber",getCardNumber())
            .toString();
    }
}
