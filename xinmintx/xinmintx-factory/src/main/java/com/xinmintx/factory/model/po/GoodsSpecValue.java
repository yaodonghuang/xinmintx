package com.xinmintx.factory.model.po;

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
 * 商品规格值表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsSpecValue对象", description="商品规格值表")
public class GoodsSpecValue extends Model<GoodsSpecValue> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品规格组表id")
    private Integer specId;

    @ApiModelProperty(value = "商品规格值")
    private String value;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    private Integer code;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
