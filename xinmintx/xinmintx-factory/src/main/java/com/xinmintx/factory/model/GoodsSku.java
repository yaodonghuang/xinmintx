package com.xinmintx.factory.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @author wcj
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsSku extends Model<GoodsSku> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品编码(例10001_1)(商品id_自定义)
     */
    private String skuId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品sku记录索引
     */
    private String specValueId;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 代理价
     */
    private BigDecimal agentPrice;

    /**
     * 划线价
     */
    private BigDecimal linePrice;

    /**
     * 库存
     */
    private Integer stockNum;

    /**
     * 照片id
     */
    private Integer photoId;

    /**
     * 销量
     */
    private Integer goodsSales;

    /**
     * 重量
     */
    private Double goodsWeight;

    private Date createTime;

    private Date updateTime;

    /**
     * 采购价
     */
    private BigDecimal purchasePrice;

    /**
     * e卡价格
     */
    private BigDecimal ePrice;

    /**
     * 金卡价格
     */
    private BigDecimal glodPrice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
