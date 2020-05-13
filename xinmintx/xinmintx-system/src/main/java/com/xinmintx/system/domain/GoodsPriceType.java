package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户商品价格分类对象 goods_price_type
 * 
 * @author xinmintx
 * @date 2020-02-13
 */
public class GoodsPriceType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商品价格类型(1,普通会员价  2,E卡会员价  3,金卡会员价  4,代理商价 */
    @Excel(name = "商品价格类型(1,普通会员价  2,E卡会员价  3,金卡会员价  4,代理商价")
    private Integer priceType;

    /** 价格配置比例 */
    @Excel(name = "价格配置比例")
    private Double percent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPriceType(Integer priceType) 
    {
        this.priceType = priceType;
    }

    public Integer getPriceType() 
    {
        return priceType;
    }
    public void setPercent(Double percent) 
    {
        this.percent = percent;
    }

    public Double getPercent() 
    {
        return percent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("priceType", getPriceType())
            .append("percent", getPercent())
            .toString();
    }
}
