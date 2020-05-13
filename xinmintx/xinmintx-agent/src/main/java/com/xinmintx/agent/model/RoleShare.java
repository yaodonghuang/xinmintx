package com.xinmintx.agent.model;

public class RoleShare {
    private Integer id;

    private Integer userRole;

    private String roleName;

    private Integer recommendType;

    private Double money;

    private Integer recommendRole;

    private String recommendName;

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

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getRecommendRole() {
        return recommendRole;
    }

    public void setRecommendRole(Integer recommendRole) {
        this.recommendRole = recommendRole;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName == null ? null : recommendName.trim();
    }
}