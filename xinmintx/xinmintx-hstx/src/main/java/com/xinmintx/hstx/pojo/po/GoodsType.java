package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 商品分类表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsType对象", description="商品分类表")
public class GoodsType extends Model<GoodsType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上级目录id(一级目录时为0,二级目录时填写一级目录id)")
    private Integer parentId;

    @ApiModelProperty(value = "类别名称")
    private String typeName;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "目录等级(1一级目录,2二级目录)")
    private Integer level;

    @ApiModelProperty(value = "三级目录图片")
    @TableField("typePhoto")
    private String typePhoto;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
