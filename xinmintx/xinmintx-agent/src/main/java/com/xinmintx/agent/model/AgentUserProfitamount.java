package com.xinmintx.agent.model;

public class AgentUserProfitamount {
    private Integer id;

    private String cardType;

    private Integer userId;

    private String agentProfitAmount;

    private String cardName;

    private Integer starts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAgentProfitAmount() {
        return agentProfitAmount;
    }

    public void setAgentProfitAmount(String agentProfitAmount) {
        this.agentProfitAmount = agentProfitAmount == null ? null : agentProfitAmount.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }
}