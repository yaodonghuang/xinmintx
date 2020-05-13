package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 good_ptgood
 * 
 * @author xinmintx
 * @date 2019-12-18
 */
public class GoodPtgood extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 拼团自增id */
    private Long ptgoodsId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 拼团商品名称 */
    @Excel(name = "拼团商品名称")
    private String ptgoodsName;

    /** 商品价格(默认价格) */
    @Excel(name = "商品价格(默认价格)")
    private Double price;

    /** 拼团价格(优惠多少钱) */
    @Excel(name = "拼团价格(优惠多少钱)")
    private Double ptPrice;

    /** 拼团人数(2-10) */
    @Excel(name = "拼团人数(2-10)")
    private Long ptSize;

    /** 拼团有效期(小时) */
    @Excel(name = "拼团有效期(小时)")
    private Double ptValidhours;

    /** 拼团开始时间 */
    @Excel(name = "拼团开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 拼团结束时间 */
    @Excel(name = "拼团结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 商品拼团库存数量 */
    @Excel(name = "商品拼团库存数量")
    private Long ptgoodsNumber;

    /** 商品剪短描述 */
    @Excel(name = "商品剪短描述")
    private String description;

    /** 商品详细描述 */
    @Excel(name = "商品详细描述")
    private String content;

    /** 商品微缩图 */
    @Excel(name = "商品微缩图")
    private String ptgoodsThumb;

    /** 商品详情轮播图 */
    @Excel(name = "商品详情轮播图")
    private String ptgoodsImgs;

    /** 商品添加时间 */
    @Excel(name = "商品添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uptime;

    /** 启用，1，是；0，否 */
    @Excel(name = "启用，1，是；0，否")
    private Long isSale;

    /** 拼团次数 */
    @Excel(name = "拼团次数")
    private Long groupTimes;

    /** 拼团类型（1代理商，2.惠商） */
    @Excel(name = "拼团类型", readConverterExp = "1=代理商，2.惠商")
    private Long groupType;

    /** $column.columnComment */
    @Excel(name = "拼团类型", readConverterExp = "$column.readConverterExp()")
    private String common;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String nameActivity;

    public void setPtgoodsId(Long ptgoodsId) 
    {
        this.ptgoodsId = ptgoodsId;
    }

    public Long getPtgoodsId() 
    {
        return ptgoodsId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setPtgoodsName(String ptgoodsName) 
    {
        this.ptgoodsName = ptgoodsName;
    }

    public String getPtgoodsName() 
    {
        return ptgoodsName;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setPtPrice(Double ptPrice) 
    {
        this.ptPrice = ptPrice;
    }

    public Double getPtPrice() 
    {
        return ptPrice;
    }
    public void setPtSize(Long ptSize) 
    {
        this.ptSize = ptSize;
    }

    public Long getPtSize()
    {
        return ptSize;
    }
    public void setPtValidhours(Double ptValidhours)
    {
        this.ptValidhours = ptValidhours;
    }

    public Double getPtValidhours()
    {
        return ptValidhours;
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
    public void setPtgoodsNumber(Long ptgoodsNumber) 
    {
        this.ptgoodsNumber = ptgoodsNumber;
    }

    public Long getPtgoodsNumber() 
    {
        return ptgoodsNumber;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPtgoodsThumb(String ptgoodsThumb) 
    {
        this.ptgoodsThumb = ptgoodsThumb;
    }

    public String getPtgoodsThumb() 
    {
        return ptgoodsThumb;
    }
    public void setPtgoodsImgs(String ptgoodsImgs) 
    {
        this.ptgoodsImgs = ptgoodsImgs;
    }

    public String getPtgoodsImgs() 
    {
        return ptgoodsImgs;
    }
    public void setAddtime(Date addtime) 
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }
    public void setUptime(Date uptime) 
    {
        this.uptime = uptime;
    }

    public Date getUptime() 
    {
        return uptime;
    }
    public void setIsSale(Long isSale) 
    {
        this.isSale = isSale;
    }

    public Long getIsSale() 
    {
        return isSale;
    }
    public void setGroupTimes(Long ptTimes)
    {
        this.groupTimes = ptTimes;
    }

    public Long getGroupTimes()
    {
        return groupTimes;
    }
    public void setGroupType(Long groupType) 
    {
        this.groupType = groupType;
    }

    public Long getGroupType() 
    {
        return groupType;
    }
    public void setCommon(String common) 
    {
        this.common = common;
    }

    public String getCommon() 
    {
        return common;
    }
    public void setNameActivity(String nameActivity) 
    {
        this.nameActivity = nameActivity;
    }

    public String getNameActivity() 
    {
        return nameActivity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("ptgoodsId", getPtgoodsId())
            .append("goodsId", getGoodsId())
            .append("ptgoodsName", getPtgoodsName())
            .append("price", getPrice())
            .append("ptPrice", getPtPrice())
            .append("ptSize", getPtSize())
            .append("ptValidhours", getPtValidhours())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("ptgoodsNumber", getPtgoodsNumber())
            .append("description", getDescription())
            .append("content", getContent())
            .append("ptgoodsThumb", getPtgoodsThumb())
            .append("ptgoodsImgs", getPtgoodsImgs())
            .append("addtime", getAddtime())
            .append("uptime", getUptime())
            .append("isSale", getIsSale())
            .append("groupTimes", getGroupTimes())
            .append("groupType", getGroupType())
            .append("common", getCommon())
            .append("nameActivity", getNameActivity())
            .toString();
    }
}
