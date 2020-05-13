package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 首页推荐位置商品对象 goods_featured_first
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
public class GoodsFeaturedFirst extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 首页推荐位置 */
    @Excel(name = "首页推荐位置")
    private Long place;

    /** 状态(0不启用,1启用) */
    @Excel(name = "状态(0不启用,1启用)")
    private Long status;

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
    public void setPlace(Long place) 
    {
        this.place = place;
    }

    public Long getPlace() 
    {
        return place;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("place", getPlace())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
