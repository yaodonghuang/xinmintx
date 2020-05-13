package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 消费分润对象 member_benefit
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
public class MemberBenefit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类型(1,合伙人(商品提供商) 2,分公司 3,消费推荐人 4,矩阵 5,通证池 6,分公司推荐人) */
    @Excel(name = "类型(1,合伙人(商品提供商) 2,分公司 3,消费推荐人 4,矩阵 5,通证池 6,分公司推荐人)")
    private Long type;

    /** 比例 */
    @Excel(name = "比例")
    private Double percent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
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
            .append("type", getType())
            .append("percent", getPercent())
            .toString();
    }
}
