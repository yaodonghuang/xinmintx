package com.xinmintx.business.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Goods {
    private Integer id;

    private Integer source;

    private Integer relateId;

    private Integer typeId;

    private Integer choiceness;

    private Integer hotSale;

    private Integer preorder;

    private String goodsListName;

    private String goodsName;

    private String content;

    private String turnsPhoto;

    private Integer speType;

    private BigDecimal price;

    private BigDecimal agencyPrice;

    private BigDecimal linePrice;

    private Integer stockNum;

    private Integer salesInitial;

    private String activityTitle;

    private Integer salesActual;

    private Integer status;

    private Integer sort;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Long onePrice;

    private Long twoPrice;

    private Long threePrice;

    private Long fourPrice;

    private Long fivePrice;

    private Long sixPrice;

    private Long sevenPrice;

    private Long eightPrice;

    private Long ninePrice;

    private Long tenPrice;

    private Integer percent;

    private Integer sales;

    private List<String> turnsPhotoList;
}