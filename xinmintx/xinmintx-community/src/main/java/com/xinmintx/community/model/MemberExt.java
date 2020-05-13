package com.xinmintx.community.model;

/**
 * @ClassName: com.xinmintx.community.model.MemberExt
 * @Author:Pikachu
 * @Date: 2020/2/20 12:26
 * @Version: v1.0
 */

public class MemberExt {
    private Integer memberId;
    private String avatarUrl;
    private String memberName;
    private Integer rank;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
