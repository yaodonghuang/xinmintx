package com.xinmintx.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class CommunityMerchant implements Comparable<CommunityMerchant>{

    private Integer id;

    private Integer communityId;

    private Integer merchantId;

    private Integer type;

    private Date createTime;

    private String typeName;

    private String ifSign;

    @Override
    public int compareTo(CommunityMerchant o) {
        return this.type.compareTo(o.getType());
    }
}
