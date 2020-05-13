package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 各个角色账号任务考核到期时间对象 account_expiration
 * 
 * @author xinmintx
 * @date 2019-11-28
 */
public class AccountExpiration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人) */
    @Excel(name = "角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人)")
    private Long userRole;

    /** 角色名 */
    @Excel(name = "角色名")
    private String roleName;

    /** 倒计时天数 */
    @Excel(name = "倒计时天数")
    private Long daysNo;

    /** 具体到期时间 */
    @Excel(name = "具体到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dayTime;

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
    public void setDaysNo(Long daysNo) 
    {
        this.daysNo = daysNo;
    }

    public Long getDaysNo() 
    {
        return daysNo;
    }
    public void setDayTime(Date dayTime) 
    {
        this.dayTime = dayTime;
    }

    public Date getDayTime() 
    {
        return dayTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userRole", getUserRole())
            .append("roleName", getRoleName())
            .append("daysNo", getDaysNo())
            .append("dayTime", getDayTime())
            .toString();
    }
}
