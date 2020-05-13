package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 限时抢购对象 good_panic_buy
 * 
 * @author xinmintx
 * @date 2020-02-20
 */
public class GoodPanicBuy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 售价 */
    @Excel(name = "售价")
    private Double price;

    /** 划线价格 */
    @Excel(name = "划线价格")
    private Double linePrice;

    /** 普通会员优惠金额 */
    @Excel(name = "普通会员优惠金额")
    private Double generalPrice;

    /** E卡会员优惠金额 */
    @Excel(name = "E卡会员优惠金额")
    private Double ePrice;

    /** 新民金卡优惠金额 */
    @Excel(name = "新民金卡优惠金额")
    private Double goldPrice;

    /** 抢购开始时间 */
    @Excel(name = "抢购开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 抢购结束时间 */
    @Excel(name = "抢购结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 商品抢购库存数量 */
    @Excel(name = "商品抢购库存数量")
    private Long stockNum;

    /** 展示图片 */
    @Excel(name = "展示图片")
    private String activityImg;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String activityTitle;

    /** 启用(1是,0否) */
    @Excel(name = "启用(1是,0否)")
    private Integer isSale;

    /** 状态(0下架,1上架) */
    @Excel(name = "状态(0下架,1上架)")
    private Integer status;

    /** 初始销量(虚拟销量) */
    @Excel(name = "初始销量(虚拟销量)")
    private Long salesInitial;

    /** 实际销量 */
    @Excel(name = "实际销量")
    private Long salesActual;

    /** 虚拟抢购用户id ',' 隔开 例: 1,2,3,4 */
    @Excel(name = "虚拟抢购用户id ',' 隔开 例: 1,2,3,4")
    private String virtualMember;

    /** 限购 */
    @Excel(name = "限购(-1为不限)")
    private Long restriction;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setLinePrice(Double linePrice) 
    {
        this.linePrice = linePrice;
    }

    public Double getLinePrice() 
    {
        return linePrice;
    }
    public void setGeneralPrice(Double generalPrice) 
    {
        this.generalPrice = generalPrice;
    }

    public Double getGeneralPrice() 
    {
        return generalPrice;
    }
    public void setEPrice(Double ePrice) 
    {
        this.ePrice = ePrice;
    }

    public Double getEPrice() 
    {
        return ePrice;
    }
    public void setGoldPrice(Double goldPrice) 
    {
        this.goldPrice = goldPrice;
    }

    public Double getGoldPrice() 
    {
        return goldPrice;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStockNum(Long stockNum) 
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum() 
    {
        return stockNum;
    }
    public void setActivityImg(String activityImg) 
    {
        this.activityImg = activityImg;
    }

    public String getActivityImg() 
    {
        return activityImg;
    }
    public void setActivityTitle(String activityTitle) 
    {
        this.activityTitle = activityTitle;
    }

    public String getActivityTitle() 
    {
        return activityTitle;
    }
    public void setIsSale(Integer isSale) 
    {
        this.isSale = isSale;
    }

    public Integer getIsSale() 
    {
        return isSale;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSalesInitial(Long salesInitial) 
    {
        this.salesInitial = salesInitial;
    }

    public Long getSalesInitial() 
    {
        return salesInitial;
    }
    public void setSalesActual(Long salesActual) 
    {
        this.salesActual = salesActual;
    }

    public Long getSalesActual() 
    {
        return salesActual;
    }
    public void setVirtualMember(String virtualMember) 
    {
        this.virtualMember = virtualMember;
    }

    public String getVirtualMember() 
    {
        return virtualMember;
    }
    public void setRestriction(Long restriction) 
    {
        this.restriction = restriction;
    }

    public Long getRestriction() 
    {
        return restriction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("price", getPrice())
            .append("linePrice", getLinePrice())
            .append("generalPrice", getGeneralPrice())
            .append("ePrice", getEPrice())
            .append("goldPrice", getGoldPrice())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("stockNum", getStockNum())
            .append("activityImg", getActivityImg())
            .append("activityTitle", getActivityTitle())
            .append("isSale", getIsSale())
            .append("status", getStatus())
            .append("salesInitial", getSalesInitial())
            .append("salesActual", getSalesActual())
            .append("virtualMember", getVirtualMember())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("restriction", getRestriction())
            .toString();
    }
}
