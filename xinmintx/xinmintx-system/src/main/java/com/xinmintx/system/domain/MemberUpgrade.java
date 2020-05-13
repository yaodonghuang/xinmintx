package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员卡升级金额或积分对象 member_upgrade
 * 
 * @author xinmintx
 * @date 2019-11-28
 */
public class MemberUpgrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 金额 */
    @Excel(name = "金额")
    private Double money;

    /** 积分 */
    @Excel(name = "积分")
    private Long integral;

    /** 用户角色(1,合伙人;2,代理;3,黄金商户;4,普通商户;5,银卡)  */
    @Excel(name = "用户角色(1,合伙人;2,代理;3,黄金商户;4,普通商户;5,银卡) ")
    private String role;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMoney(Double money) 
    {
        this.money = money;
    }

    public Double getMoney() 
    {
        return money;
    }
    public void setIntegral(Long integral) 
    {
        this.integral = integral;
    }

    public Long getIntegral() 
    {
        return integral;
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
            .append("money", getMoney())
            .append("integral", getIntegral())
            .append("role", getRole())
            .append("description", getDescription())
            .toString();
    }
}
