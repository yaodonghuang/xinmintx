package com.xinmintx.agent.model;

public class Profit {
    private Integer id;

    private Double selfProfit;

    private Double superProfit;

    private Double cost;

    private String role;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSelfProfit() {
        return selfProfit;
    }

    public void setSelfProfit(Double selfProfit) {
        this.selfProfit = selfProfit;
    }

    public Double getSuperProfit() {
        return superProfit;
    }

    public void setSuperProfit(Double superProfit) {
        this.superProfit = superProfit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}