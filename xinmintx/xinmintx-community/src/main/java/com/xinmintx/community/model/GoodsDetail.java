package com.xinmintx.community.model;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: com.xinmintx.merchant.model.GoodsDetail
 * @Author:Pikachu
 * @Date: 2020/3/13 11:30
 * @Version: v1.0
 */
@Data
public class GoodsDetail {
    private Long id;
    private String icon;
    private String name;
    private String communityName;
    private Double communityPrice;
    private Double onlinePrice;
    private String description;
    private Long bigdecimal;
    private String consigneeAddress;
    private List<MerchantGoodsPic> picList;
    private List<PurchaseHistory> list;

}
