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
 * 
 * </p>
 *
 * @author zhf
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FactoryName对象", description="")
public class FactoryName extends Model<FactoryName> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工厂名称")
    private String factoryName;

    @ApiModelProperty(value = "工厂id")
    private Integer factoryId;

    @ApiModelProperty(value = "工厂头像")
    private String factoryPortrait;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
