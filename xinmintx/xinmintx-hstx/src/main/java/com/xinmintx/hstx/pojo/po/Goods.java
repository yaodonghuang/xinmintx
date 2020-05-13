package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象", description="商品表")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "1厂家,2商家")
    private Integer source;

    @ApiModelProperty(value = "关联id(厂家/商家的id)")
    private Integer relateId;

    @ApiModelProperty(value = "商品分类id")
    private Integer typeId;

    @ApiModelProperty(value = "今日精选")
    private Integer choiceness;

    @ApiModelProperty(value = "热销")
    private Integer hotSale;

    @ApiModelProperty(value = "预购")
    private Integer preorder;

    @ApiModelProperty(value = "商品列表显示名称")
    private String goodsListName;

    @ApiModelProperty(value = "商品详细名称")
    private String goodsName;

    @ApiModelProperty(value = "描述(富文本html)")
    private String content;

    @ApiModelProperty(value = "轮播图(商品图片表id数组)(1,2,3,4,5)")
    private String turnsPhoto;

    @ApiModelProperty(value = "1单规格,2多规格")
    private Integer speType;

    @ApiModelProperty(value = "售价")
    private BigDecimal price;

    @ApiModelProperty(value = "代理价格")
    private BigDecimal agencyPrice;

    @ApiModelProperty(value = "市场价格")
    private BigDecimal bazaarPrice;

    @ApiModelProperty(value = "采购价格")
    private BigDecimal procurementPrice;

    @ApiModelProperty(value = "划线价格")
    private BigDecimal linePrice;

    @ApiModelProperty(value = "库存")
    private Integer stockNum;

    @ApiModelProperty(value = "初始销量")
    private Integer salesInitial;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "实际销量")
    private Integer salesActual;

    @ApiModelProperty(value = "状态(0下架,1上架)")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "删除标志(0未删除,1已删除)")
    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "礼包（1是、0否）")
    private Integer giftBag;

    @ApiModelProperty(value = "二人团购")
    private BigDecimal twoPrice;

    @ApiModelProperty(value = "三人团购")
    private BigDecimal threePrice;

    @ApiModelProperty(value = "四人团购")
    private BigDecimal fourPrice;

    @ApiModelProperty(value = "五人团购")
    private BigDecimal fivePrice;

    @ApiModelProperty(value = "六人团购")
    private BigDecimal sixPrice;

    @ApiModelProperty(value = "七人团购")
    private BigDecimal sevenPrice;

    @ApiModelProperty(value = "八人团购")
    private BigDecimal eightPrice;

    @ApiModelProperty(value = "九人团购")
    private BigDecimal ninePrice;

    @ApiModelProperty(value = "十人团购")
    private BigDecimal tenPrice;

    @ApiModelProperty(value = "商品参数")
    private String parameter;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
