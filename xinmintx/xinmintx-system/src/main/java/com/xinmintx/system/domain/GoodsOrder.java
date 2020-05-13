package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 商品订单对象 goods_order
 * 
 * @author xinmintx
 * @date 2020-01-03
 */
public class GoodsOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单主键id */
    private Long id;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNum;

    /** 订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败) */
    @Excel(name = "订单状态(1待付款,2待发货,3待收货,4已取消,5已收货,6物流异常,7待分享,8:退款中,9已退款,10退款失败)")
    private Integer orderState;

    /** 是否付款,0:待付款,1:已付款,2:退款中,3已退款,4退款失败 */
    @Excel(name = "是否付款,0:待付款,1:已付款,2:退款中,3已退款,4退款失败")
    private Integer ifPay;

    /** 收件地址 */
    @Excel(name = "收件地址")
    private String receiveAddress;

    /** 买家姓名 */
    @Excel(name = "买家姓名")
    private String receiveName;

    /** 买家电话 */
    @Excel(name = "买家电话")
    private String receivePhone;

    /** 买家留言 */
    @Excel(name = "买家留言")
    private String receiveMessage;

    /** 总金额(元) */
    @Excel(name = "总金额(元)")
    private Double totalAmount;

    /** 支付订单id */
    @Excel(name = "支付订单id")
    private Long uOrderId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** skuId */
    @Excel(name = "skuId")
    private Long skuId;

    /** 厂家id */
    @Excel(name = "厂家id")
    private Long factoryId;

    /** 商品单价(元) */
    @Excel(name = "商品单价(元)")
    private Double price;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Long quantity;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsPic;

    /** 商品评价 */
    @Excel(name = "商品评价")
    private String evaluate;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String courierNumber;

    /** 是否删除,0(未删除)1(已删除) */
    @Excel(name = "是否删除,0(未删除)1(已删除)")
    private Integer ifDelete;

    /** 重量(克) */
    @Excel(name = "重量(克)")
    private Long weight;

    /** 用户是否删除,0(未删除),1(已删除) */
    @Excel(name = "用户是否删除,0(未删除),1(已删除)")
    private Long userDelete;

    /** 用来标记是否被提醒,默认为0(未提醒),1(已提醒) */
    @Excel(name = "用来标记是否被提醒,默认为0(未提醒),1(已提醒)")
    private Long ifRemind;

    /** 退款信息 */
    @Excel(name = "退款信息")
    private String refundMsg;

    /** 订单类型(1普通购买,2拼团) */
    @Excel(name = "订单类型(1普通购买,2拼团)")
    private Integer orderType;

    /** 发货日期 */
    @Excel(name = "发货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendDate;

    /** 延长收货,0不延长,1延长 */
    @Excel(name = "延长收货,0不延长,1延长")
    private Integer ifDelayed;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setOrderNum(String orderNum) 
    {
        this.orderNum = orderNum;
    }

    public String getOrderNum() 
    {
        return orderNum;
    }
    public void setOrderState(Integer orderState) 
    {
        this.orderState = orderState;
    }

    public Integer getOrderState() 
    {
        return orderState;
    }
    public void setIfPay(Integer ifPay) 
    {
        this.ifPay = ifPay;
    }

    public Integer getIfPay() 
    {
        return ifPay;
    }
    public void setReceiveAddress(String receiveAddress) 
    {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveAddress() 
    {
        return receiveAddress;
    }
    public void setReceiveName(String receiveName) 
    {
        this.receiveName = receiveName;
    }

    public String getReceiveName() 
    {
        return receiveName;
    }
    public void setReceivePhone(String receivePhone) 
    {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() 
    {
        return receivePhone;
    }
    public void setReceiveMessage(String receiveMessage) 
    {
        this.receiveMessage = receiveMessage;
    }

    public String getReceiveMessage() 
    {
        return receiveMessage;
    }
    public void setTotalAmount(Double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() 
    {
        return totalAmount;
    }
    public void setUOrderId(Long uOrderId) 
    {
        this.uOrderId = uOrderId;
    }

    public Long getUOrderId() 
    {
        return uOrderId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setFactoryId(Long factoryId) 
    {
        this.factoryId = factoryId;
    }

    public Long getFactoryId() 
    {
        return factoryId;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsPic(String goodsPic) 
    {
        this.goodsPic = goodsPic;
    }

    public String getGoodsPic() 
    {
        return goodsPic;
    }
    public void setEvaluate(String evaluate) 
    {
        this.evaluate = evaluate;
    }

    public String getEvaluate() 
    {
        return evaluate;
    }
    public void setCourierNumber(String courierNumber) 
    {
        this.courierNumber = courierNumber;
    }

    public String getCourierNumber() 
    {
        return courierNumber;
    }
    public void setIfDelete(Integer ifDelete) 
    {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() 
    {
        return ifDelete;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setUserDelete(Long userDelete) 
    {
        this.userDelete = userDelete;
    }

    public Long getUserDelete() 
    {
        return userDelete;
    }
    public void setIfRemind(Long ifRemind) 
    {
        this.ifRemind = ifRemind;
    }

    public Long getIfRemind() 
    {
        return ifRemind;
    }
    public void setRefundMsg(String refundMsg) 
    {
        this.refundMsg = refundMsg;
    }

    public String getRefundMsg() 
    {
        return refundMsg;
    }
    public void setOrderType(Integer orderType) 
    {
        this.orderType = orderType;
    }

    public Integer getOrderType() 
    {
        return orderType;
    }
    public void setSendDate(Date sendDate) 
    {
        this.sendDate = sendDate;
    }

    public Date getSendDate() 
    {
        return sendDate;
    }
    public void setIfDelayed(Integer ifDelayed) 
    {
        this.ifDelayed = ifDelayed;
    }

    public Integer getIfDelayed() 
    {
        return ifDelayed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("orderNum", getOrderNum())
            .append("orderState", getOrderState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("ifPay", getIfPay())
            .append("receiveAddress", getReceiveAddress())
            .append("receiveName", getReceiveName())
            .append("receivePhone", getReceivePhone())
            .append("receiveMessage", getReceiveMessage())
            .append("totalAmount", getTotalAmount())
            .append("uOrderId", getUOrderId())
            .append("goodsId", getGoodsId())
            .append("skuId", getSkuId())
            .append("factoryId", getFactoryId())
            .append("price", getPrice())
            .append("quantity", getQuantity())
            .append("goodsName", getGoodsName())
            .append("goodsPic", getGoodsPic())
            .append("evaluate", getEvaluate())
            .append("courierNumber", getCourierNumber())
            .append("ifDelete", getIfDelete())
            .append("weight", getWeight())
            .append("userDelete", getUserDelete())
            .append("ifRemind", getIfRemind())
            .append("refundMsg", getRefundMsg())
            .append("orderType", getOrderType())
            .append("sendDate", getSendDate())
            .append("ifDelayed", getIfDelayed())
            .toString();
    }
}
