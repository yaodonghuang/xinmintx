package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分润规则对象 role_share
 * 
 * @author xinmintx
 * @date 2019-11-22
 */
public class RoleShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人) */
    @Excel(name = "用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人)")
    private Long userRole;

    /** 提交人角色名称 */
    @Excel(name = "提交人角色名称")
    private String roleName;

    /** 分润类型(1,推荐获取分成;2,享受分成,3,上级获取分成) */
    @Excel(name = "分润类型(1,推荐获取分成;2,享受分成,3,上级获取分成)")
    private Long recommendType;

    /** 说明 */
    @Excel(name = "说明")
    private String description;

    /** 金额 */
    @Excel(name = "金额")
    private Long money;

    /** 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,银卡) */
    @Excel(name = "需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,银卡)")
    private Long recommendRole;

    /** 需要创建的角色名称 */
    @Excel(name = "需要创建的角色名称")
    private String recommendName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserRole(Long userRole) 
    {
        this.userRole = userRole;
    }

    public Long getUserRole() 
    {
        return userRole;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }
    public void setRecommendType(Long recommendType) 
    {
        this.recommendType = recommendType;
    }

    public Long getRecommendType() 
    {
        return recommendType;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setMoney(Long money) 
    {
        this.money = money;
    }

    public Long getMoney() 
    {
        return money;
    }
    public void setRecommendRole(Long recommendRole) 
    {
        this.recommendRole = recommendRole;
    }

    public Long getRecommendRole() 
    {
        return recommendRole;
    }
    public void setRecommendName(String recommendName) 
    {
        this.recommendName = recommendName;
    }

    public String getRecommendName() 
    {
        return recommendName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userRole", getUserRole())
            .append("roleName", getRoleName())
            .append("recommendType", getRecommendType())
            .append("description", getDescription())
            .append("money", getMoney())
            .append("recommendRole", getRecommendRole())
            .append("recommendName", getRecommendName())
            .toString();
    }
}
