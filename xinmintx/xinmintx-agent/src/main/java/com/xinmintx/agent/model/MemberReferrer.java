package com.xinmintx.agent.model;

public class MemberReferrer {
    private Integer id;

    private Integer referrerId;

    private Integer memberId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}