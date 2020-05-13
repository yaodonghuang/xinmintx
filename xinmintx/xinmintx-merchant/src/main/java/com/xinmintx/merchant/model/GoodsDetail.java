package com.xinmintx.merchant.model;

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
    private String name;
    private Double communityPrice;
    private String description;
    private Long bigdecimal;
    private String consigneeAddress;
    private List<MerchantGoodsPic> picList;
    private List<PurchaseHistory> list;

}
