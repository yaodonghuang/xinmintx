package com.xinmintx.community.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class GoodsOrder {
    private Integer id;

    private Integer memberId;

    private String orderNum;

    private Integer orderState;

    private Date createTime;

    private Date updateTime;

    private Integer ifPay;

    private String receiveAddress;

    private String receiveName;

    private String receivePhone;

    private String receiveMessage;

    private BigDecimal totalAmount;

    private Integer uOrderId;

    private Integer goodsId;

    private Integer skuId;

    private Integer factoryId;

    private Long merchantId;

    private Long communityId;

    private BigDecimal price;

    private Integer quantity;

    private String goodsName;

    private String goodsPic;

    private String evaluate;

    private String courierNumber;

    private Byte ifDelete;

    private Double weight;

    private Integer userDelete;

    private Integer ifRemind;

    private String refundMsg;

    private Integer orderType;

    private Date sendDate;

    private Integer ifDelayed;

    private Integer addressId;

    private Integer dividedState;

    private String refundInformation;

    private String returnMessage;

    private Date refundTime;

    private Integer deputyHelp;

    private List<GoodsOrderDetail>  detailList;

    public Integer getDeputyHelp() {
        return deputyHelp;
    }

    public void setDeputyHelp(Integer deputyHelp) {
        this.deputyHelp = deputyHelp;
    }

    public List<GoodsOrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<GoodsOrderDetail> detailList) {
        this.detailList = detailList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIfPay() {
        return ifPay;
    }

    public void setIfPay(Integer ifPay) {
        this.ifPay = ifPay;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage == null ? null : receiveMessage.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getuOrderId() {
        return uOrderId;
    }

    public void setuOrderId(Integer uOrderId) {
        this.uOrderId = uOrderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber == null ? null : courierNumber.trim();
    }

    public Byte getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Byte ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Integer userDelete) {
        this.userDelete = userDelete;
    }

    public Integer getIfRemind() {
        return ifRemind;
    }

    public void setIfRemind(Integer ifRemind) {
        this.ifRemind = ifRemind;
    }

    public String getRefundMsg() {
        return refundMsg;
    }

    public void setRefundMsg(String refundMsg) {
        this.refundMsg = refundMsg == null ? null : refundMsg.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getIfDelayed() {
        return ifDelayed;
    }

    public void setIfDelayed(Integer ifDelayed) {
        this.ifDelayed = ifDelayed;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getDividedState() {
        return dividedState;
    }

    public void setDividedState(Integer dividedState) {
        this.dividedState = dividedState;
    }

    public String getRefundInformation() {
        return refundInformation;
    }

    public void setRefundInformation(String refundInformation) {
        this.refundInformation = refundInformation == null ? null : refundInformation.trim();
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage == null ? null : returnMessage.trim();
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}
