package com.xinmintx.agent.model;

import java.util.Date;

public class AccountExpiration {
    private Integer id;

    private Integer userRole;

    private String roleName;

    private Integer daysNo;

    private Date dayTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getDaysNo() {
        return daysNo;
    }

    public void setDaysNo(Integer daysNo) {
        this.daysNo = daysNo;
    }

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }
}