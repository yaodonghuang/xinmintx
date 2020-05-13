package com.xinmintx.community.model;

import java.util.Date;

/** 社区投票实体类
 * @author hyd
 */
public class CommunityVote {
    private Long id;

    private Long communityId;

    private Integer reason;

    private String otherReason;

    private Long originatorId;

    private Date createTime;

    private Integer agreeNum;

    private Integer refuseNum;

    private Byte ifComplete;

    public Byte getIfComplete() {
        return ifComplete;
    }

    public void setIfComplete(Byte ifComplete) {
        this.ifComplete = ifComplete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason == null ? null : otherReason.trim();
    }

    public Long getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(Long originatorId) {
        this.originatorId = originatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Integer getRefuseNum() {
        return refuseNum;
    }

    public void setRefuseNum(Integer refuseNum) {
        this.refuseNum = refuseNum;
    }
}
