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
 * 商品sku属性表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsSku对象", description="商品sku属性表")
public class GoodsSku extends Model<GoodsSku> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编码(例10001_1)(商品id_自定义)")
    private String skuId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品sku记录索引")
    private String specValueId;

    @ApiModelProperty(value = "售价")
    private BigDecimal price;

    @ApiModelProperty(value = "代理价")
    private BigDecimal agentPrice;

    @ApiModelProperty(value = "划线价")
    private BigDecimal linePrice;

    @ApiModelProperty(value = "库存")
    private Integer stockNum;

    @ApiModelProperty(value = "照片id")
    private Integer photoId;

    @ApiModelProperty(value = "销量")
    private Integer goodsSales;

    @ApiModelProperty(value = "重量")
    private Double goodsWeight;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "采购价")
    private BigDecimal purchasePrice;

    @ApiModelProperty(value = "e卡价格")
    private BigDecimal ePrice;

    @ApiModelProperty(value = "金卡价格")
    private BigDecimal glodPrice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
