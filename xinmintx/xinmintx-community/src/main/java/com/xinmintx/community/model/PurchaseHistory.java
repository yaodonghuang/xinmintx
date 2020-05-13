package com.xinmintx.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: com.xinmintx.merchant.model.PurchaseHistory
 * @Author:Pikachu
 * @Date: 2020/3/13 13:57
 * @Version: v1.0
 */
@Data
public class PurchaseHistory {
    private String avatarUrl;
    private String name;
    private Integer quantity;
    private Date createTime;
}
