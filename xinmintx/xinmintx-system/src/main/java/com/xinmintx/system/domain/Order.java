package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单对象 order
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 订单交易号 */
    @Excel(name = "订单交易号")
    private String outTradeNo;

    /** 第三方交易标示(微信，支付宝下单返回) */
    @Excel(name = "第三方交易标示(微信，支付宝下单返回)")
    private String prepayId;

    /** 订单描述(商品名称) */
    @Excel(name = "订单描述(商品名称)")
    private String goodsDesc;

    /** 支付金额(单位分) */
    @Excel(name = "支付金额(单位分)")
    private Long totalFee;

    /** 支付方式(1,微信;2支付宝,3,银行卡) */
    @Excel(name = "支付方式(1,微信;2支付宝,3,银行卡)")
    private String payType;

    /** 支付状态(0,未支付;1,已支付;2,已退款) */
    @Excel(name = "支付状态(0,未支付;1,已支付;2,已退款)")
    private String payStatus;

    /** 支付交易号(微信支付宝支付完成后交易流水号) */
    @Excel(name = "支付交易号(微信支付宝支付完成后交易流水号)")
    private Long transactionId;

    /** 支付时间 */
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setOutTradeNo(String outTradeNo) 
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() 
    {
        return outTradeNo;
    }
    public void setPrepayId(String prepayId) 
    {
        this.prepayId = prepayId;
    }

    public String getPrepayId() 
    {
        return prepayId;
    }
    public void setGoodsDesc(String goodsDesc) 
    {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDesc() 
    {
        return goodsDesc;
    }
    public void setTotalFee(Long totalFee) 
    {
        this.totalFee = totalFee;
    }

    public Long getTotalFee() 
    {
        return totalFee;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setPayStatus(String payStatus) 
    {
        this.payStatus = payStatus;
    }

    public String getPayStatus() 
    {
        return payStatus;
    }
    public void setTransactionId(Long transactionId) 
    {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() 
    {
        return transactionId;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("outTradeNo", getOutTradeNo())
            .append("prepayId", getPrepayId())
            .append("goodsDesc", getGoodsDesc())
            .append("totalFee", getTotalFee())
            .append("payType", getPayType())
            .append("payStatus", getPayStatus())
            .append("transactionId", getTransactionId())
            .append("createTime", getCreateTime())
            .append("payTime", getPayTime())
            .toString();
    }
}
