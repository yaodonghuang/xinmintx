package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 商品详情公共部分配置对象 goods_public
 * 
 * @author xinmintx
 * @date 2020-02-25
 */
public class GoodsPublic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 图片 */
    @Excel(name = "图片")
    private String photoUrl;

    /** 显示位置(1.商品详情之上,2商品详情之下) */
    @Excel(name = "显示位置(1.商品详情之上,2商品详情之下)")
    private Integer place;

    /** 作用商品商品(1,普通商品,2拼团商品,3抢购商品) */
    @Excel(name = "作用商品商品(1,普通商品,2拼团商品,3抢购商品)")
    private Integer type;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否启用(0否,1是) */
    @Excel(name = "是否启用(0否,1是)")
    private Integer status;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }
    public void setPlace(Integer place) 
    {
        this.place = place;
    }

    public Integer getPlace() 
    {
        return place;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("photoUrl", getPhotoUrl())
            .append("place", getPlace())
            .append("type", getType())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
