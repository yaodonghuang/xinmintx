package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品审核对象 procurement_commodities
 * 
 * @author xinmintx
 * @date 2019-12-03
 */
public class ProcurementCommodities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** user  ID (这里是user id) */
    @Excel(name = "user  ID (这里是user id)")
    private Long merchantId;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String sampleName;

    /** 样品类型 */
    @Excel(name = "样品类型")
    private String sampleType;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String trackingNumber;

    /** 供货商 */
    @Excel(name = "供货商")
    private String supplier;

    /** 电话号 */
    @Excel(name = "电话号")
    private String cellphone;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 市场价格 */
    @Excel(name = "市场价格")
    private Double marketValue;

    /** 采购价格 */
    @Excel(name = "采购价格")
    private Double procurementPrice;

    /** 图片id */
    @Excel(name = "图片id")
    private Long pictureId;

    /** $column.columnComment */
    @Excel(name = "图片id")
    private String commonone;

    /** $column.columnComment */
    @Excel(name = "图片id")
    private String commontwo;

    /** 是否审核（0，未审核，1审核） */
    @Excel(name = "是否审核", readConverterExp = "0=，未审核，1审核")
    private Integer isReview;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }
    public void setSampleName(String sampleName) 
    {
        this.sampleName = sampleName;
    }

    public String getSampleName() 
    {
        return sampleName;
    }
    public void setSampleType(String sampleType) 
    {
        this.sampleType = sampleType;
    }

    public String getSampleType() 
    {
        return sampleType;
    }
    public void setTrackingNumber(String trackingNumber) 
    {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() 
    {
        return trackingNumber;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setCellphone(String cellphone) 
    {
        this.cellphone = cellphone;
    }

    public String getCellphone() 
    {
        return cellphone;
    }
    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }
    public void setMarketValue(Double marketValue) 
    {
        this.marketValue = marketValue;
    }

    public Double getMarketValue() 
    {
        return marketValue;
    }
    public void setProcurementPrice(Double procurementPrice) 
    {
        this.procurementPrice = procurementPrice;
    }

    public Double getProcurementPrice() 
    {
        return procurementPrice;
    }
    public void setPictureId(Long pictureId) 
    {
        this.pictureId = pictureId;
    }

    public Long getPictureId() 
    {
        return pictureId;
    }
    public void setCommonone(String commonone) 
    {
        this.commonone = commonone;
    }

    public String getCommonone() 
    {
        return commonone;
    }
    public void setCommontwo(String commontwo) 
    {
        this.commontwo = commontwo;
    }

    public String getCommontwo() 
    {
        return commontwo;
    }
    public void setIsReview(Integer isReview) 
    {
        this.isReview = isReview;
    }

    public Integer getIsReview() 
    {
        return isReview;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("sampleName", getSampleName())
            .append("sampleType", getSampleType())
            .append("trackingNumber", getTrackingNumber())
            .append("supplier", getSupplier())
            .append("cellphone", getCellphone())
            .append("detailedAddress", getDetailedAddress())
            .append("marketValue", getMarketValue())
            .append("procurementPrice", getProcurementPrice())
            .append("pictureId", getPictureId())
            .append("commonone", getCommonone())
            .append("commontwo", getCommontwo())
            .append("isReview", getIsReview())
            .toString();
    }
}
