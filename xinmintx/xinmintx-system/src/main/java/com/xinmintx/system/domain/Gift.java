package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 礼包对象 gift
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
public class Gift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 礼包表主键id */
    private Long id;

    /** 礼包名称 */
    @Excel(name = "礼包名称")
    private String giftName;

    /** 用于区分,1:平台礼包 2:商户礼包 */
    @Excel(name = "用于区分,1:平台礼包 2:商户礼包")
    private String giftType;

    /** 截止日期 */
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 0:未作废  1:已作废 */
    @Excel(name = "0:未作废  1:已作废")
    private Integer ifDelete;

    /** 礼包状态 */
    @Excel(name = "礼包状态")
    private String giftState;

    /** 来源id,平台id或者商户id,根据类型区分 */
    @Excel(name = "来源id,平台id或者商户id,根据类型区分")
    private Long sourceId;

    /** 好评率 */
    @Excel(name = "好评率")
    private Double favorableRate;

    /** 满减价格,(50-15) */
    @Excel(name = "满减价格,(50-15)")
    private String fullReducePrice;

    /** 代金价格 */
    @Excel(name = "代金价格")
    private Double cashCoupon;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** skuId */
    @Excel(name = "skuId")
    private Long skuId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer quantity;

    private String remark;

    // 照片
    private String turnsPhoto;

    private String beginTimeStamp;// 截止日期的开始时间戳

    private String endTimeStamp;// 截止日期的结束时间戳

    private Integer giftCount;// 用户可领取礼包数量

    private String goodsListName;// 商品名称

    private String giftGroup;// 礼包种类

    private BigDecimal price;// 购买礼包价格

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGiftGroup() {
        return giftGroup;
    }

    public void setGiftGroup(String giftGroup) {
        this.giftGroup = giftGroup;
    }

    public String getGoodsListName() {
        return goodsListName;
    }

    public void setGoodsListName(String goodsListName) {
        this.goodsListName = goodsListName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }

    public String getBeginTimeStamp() {
        return beginTimeStamp;
    }

    public void setBeginTimeStamp(String beginTimeStamp) {
        this.beginTimeStamp = beginTimeStamp;
    }

    public String getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(String endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public String getTurnsPhoto() {
        return turnsPhoto;
    }

    public void setTurnsPhoto(String turnsPhoto) {
        this.turnsPhoto = turnsPhoto;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGiftName(String giftName) 
    {
        this.giftName = giftName;
    }

    public String getGiftName() 
    {
        return giftName;
    }
    public void setGiftType(String giftType) 
    {
        this.giftType = giftType;
    }

    public String getGiftType() 
    {
        return giftType;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setIfDelete(Integer ifDelete) 
    {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() 
    {
        return ifDelete;
    }
    public void setGiftState(String giftState) 
    {
        this.giftState = giftState;
    }

    public String getGiftState() 
    {
        return giftState;
    }
    public void setSourceId(Long sourceId) 
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId() 
    {
        return sourceId;
    }
    public void setFavorableRate(Double favorableRate) 
    {
        this.favorableRate = favorableRate;
    }

    public Double getFavorableRate() 
    {
        return favorableRate;
    }
    public void setFullReducePrice(String fullReducePrice) 
    {
        this.fullReducePrice = fullReducePrice;
    }

    public String getFullReducePrice() 
    {
        return fullReducePrice;
    }
    public void setCashCoupon(Double cashCoupon) 
    {
        this.cashCoupon = cashCoupon;
    }

    public Double getCashCoupon() 
    {
        return cashCoupon;
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
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }

    public Integer getQuantity() 
    {
        return quantity;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("giftName", getGiftName())
            .append("giftType", getGiftType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("endDate", getEndDate())
            .append("ifDelete", getIfDelete())
            .append("giftState", getGiftState())
            .append("sourceId", getSourceId())
            .append("favorableRate", getFavorableRate())
            .append("fullReducePrice", getFullReducePrice())
            .append("cashCoupon", getCashCoupon())
            .append("goodsId", getGoodsId())
            .append("skuId", getSkuId())
            .append("remark", getRemark())
            .append("quantity", getQuantity())
            .append("turnsPhoto", getTurnsPhoto())
            .append("remark", getRemark())
            .toString();
    }
}
