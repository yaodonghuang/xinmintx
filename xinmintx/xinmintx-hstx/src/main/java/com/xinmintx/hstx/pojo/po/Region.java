package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Region对象", description="")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域主键")
    private Integer id;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "区域上级标识")
    private Integer pid;

    @ApiModelProperty(value = "地名简称")
    private String sname;

    @ApiModelProperty(value = "区域等级")
    private Integer level;

    @ApiModelProperty(value = "区域编码")
    private String citycode;

    @ApiModelProperty(value = "邮政编码")
    private String yzcode;

    @ApiModelProperty(value = "组合名称")
    private String mername;

    @TableField("Lng")
    private Float Lng;

    @TableField("Lat")
    private Float Lat;

    private String pinyin;

    @ApiModelProperty(value = "该地区是否开设分公司0 没有 ；1 有")
    @TableField("RegionalState")
    private Integer RegionalState;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
