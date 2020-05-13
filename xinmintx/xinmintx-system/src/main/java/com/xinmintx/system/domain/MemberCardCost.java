package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员卡费用对象 member_card_cost
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
public class MemberCardCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 费用 */
    @Excel(name = "费用")
    private Double cost;

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
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCost(Double cost) 
    {
        this.cost = cost;
    }

    public Double getCost() 
    {
        return cost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("description", getDescription())
            .append("cost", getCost())
            .toString();
    }
}
