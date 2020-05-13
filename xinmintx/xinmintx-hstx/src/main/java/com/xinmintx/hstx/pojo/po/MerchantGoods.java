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
 * 商户产品表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantGoods对象", description="商户产品表")
public class MerchantGoods extends Model<MerchantGoods> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    @ApiModelProperty(value = "菜名")
    private String name;

    @ApiModelProperty(value = "分类:1.新鲜蔬菜 2.海鲜水产 3.粮油调味 4.时令水果 5.肉蛋食材")
    private Integer type;

    @ApiModelProperty(value = "社区价格")
    private BigDecimal communityPrice;

    @ApiModelProperty(value = "线上价格")
    private BigDecimal onlinePrice;

    @ApiModelProperty(value = "描述菜品")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "上架状态:1.上架 2 下架")
    private Integer shelfStatus;

    @ApiModelProperty(value = "商品规格")
    private Long bigdecimal;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
