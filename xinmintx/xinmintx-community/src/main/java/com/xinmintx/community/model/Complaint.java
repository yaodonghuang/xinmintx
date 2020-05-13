package com.xinmintx.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class Complaint {

    private Integer id;

    private Integer communityId;

    private Integer memberId;

    private Integer merchantId;

    private Integer type;

    private String content;

    private Date createTime;

}
