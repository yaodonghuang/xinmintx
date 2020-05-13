package com.xinmintx.agent.modelDTO;
import lombok.Data;

@Data
public class CommodityAndImage {
    private Integer id;
    private String activityTitle;
    private String commodityName;
    private Integer salesVolume;
   // 市场价
    private Double price;
    //销售价
    private Double salesPrice;

    private Double purchasePrice;
    private String ossUrl;
    private int type;
    //代理价
    private Double agencyPrice;
}
