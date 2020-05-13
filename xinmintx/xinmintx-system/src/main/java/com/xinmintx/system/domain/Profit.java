package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 profit
 * 
 * @author xinmintx
 * @date 2019-11-15
 */
public class Profit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 本人提成 */
    @Excel(name = "本人提成")
    private Double selfProfit;

    /** 上级提成 */
    @Excel(name = "上级提成")
    private Double superProfit;

    /** 需要交纳费用 */
    @Excel(name = "需要交纳费用")
    private Double cost;

    /** 用户角色(1,合伙人;2,代理;3,黄金商户;4,普通商户;5,银卡)  */
    @Excel(name = "用户角色(1,合伙人;2,代理;3,黄金商户;4,普通商户;5,银卡) ")
    private String role;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSelfProfit(Double selfProfit) 
    {
        this.selfProfit = selfProfit;
    }

    public Double getSelfProfit() 
    {
        return selfProfit;
    }
    public void setSuperProfit(Double superProfit) 
    {
        this.superProfit = superProfit;
    }

    public Double getSuperProfit() 
    {
        return superProfit;
    }
    public void setCost(Double cost) 
    {
        this.cost = cost;
    }

    public Double getCost() 
    {
        return cost;
    }
    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("selfProfit", getSelfProfit())
            .append("superProfit", getSuperProfit())
            .append("cost", getCost())
            .append("role", getRole())
            .append("description", getDescription())
            .toString();
    }
}
