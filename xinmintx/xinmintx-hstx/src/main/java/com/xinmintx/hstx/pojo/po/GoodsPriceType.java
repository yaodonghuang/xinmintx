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

/**
 * <p>
 * 用户商品价格折扣
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsPriceType对象", description="用户商品价格折扣")
public class GoodsPriceType extends Model<GoodsPriceType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品价格类型(0,普通会员价  1,E卡会员价  2,金卡会员价  3,代理商价")
    private Integer priceType;

    @ApiModelProperty(value = "价格配置比例")
    private Double percent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
