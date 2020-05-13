package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 广告对象 advertising
 * 
 * @author xinmintx
 * @date 2020-01-02
 */
public class Advertising extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 广告类型(1首页,2热销榜单,3今日精选,4分类) */
    @Excel(name = "广告类型(1首页,2热销榜单,3今日精选,4分类)")
    private Long adType;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNum;

    /** 广告链接类型(1外部链接,2普通商品,3拼团商品,4热销榜单,5今日精选,6分类) */
    @Excel(name = "广告链接类型(1外部链接,2普通商品,3拼团商品,4热销榜单,5今日精选,6分类)")
    private Long linkType;

    /** 外部广告链接 */
    @Excel(name = "外部广告链接")
    private String linkUrl;

    /** 关联id(商品/分类的id) */
    @Excel(name = "关联id(商品/分类的id)")
    private Long relateId;

    /** 广告位(当前页显示在第几个位置) */
    @Excel(name = "广告位(当前页显示在第几个位置)")
    private Long adPlace;

    /** 是否有效(0为无效,1为有效) */
    @Excel(name = "是否有效(0为无效,1为有效)")
    private Integer status;

    /** 广告图片 */
    @Excel(name = "广告图片")
    private String goodsPhotoUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAdType(Long adType) 
    {
        this.adType = adType;
    }

    public Long getAdType() 
    {
        return adType;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setLinkType(Long linkType) 
    {
        this.linkType = linkType;
    }

    public Long getLinkType() 
    {
        return linkType;
    }
    public void setLinkUrl(String linkUrl) 
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() 
    {
        return linkUrl;
    }
    public void setRelateId(Long relateId) 
    {
        this.relateId = relateId;
    }

    public Long getRelateId() 
    {
        return relateId;
    }
    public void setAdPlace(Long adPlace) 
    {
        this.adPlace = adPlace;
    }

    public Long getAdPlace() 
    {
        return adPlace;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setGoodsPhotoUrl(String goodsPhotoUrl) 
    {
        this.goodsPhotoUrl = goodsPhotoUrl;
    }

    public String getGoodsPhotoUrl() 
    {
        return goodsPhotoUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("adType", getAdType())
            .append("orderNum", getOrderNum())
            .append("linkType", getLinkType())
            .append("linkUrl", getLinkUrl())
            .append("relateId", getRelateId())
            .append("adPlace", getAdPlace())
            .append("status", getStatus())
            .append("goodsPhotoUrl", getGoodsPhotoUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
