package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

/**
 * @author wangkang
 */
@Data
public class MemberAddressVo {
    private Integer id;
    private String token;
    private String name;
    private String cellphone;
    private String regionCode;
    private String region;
    private String address;
    private Boolean checked;
}
