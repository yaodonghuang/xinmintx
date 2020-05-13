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
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantType对象", description="")
public class MerchantType extends Model<MerchantType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型id")
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    @ApiModelProperty(value = "类型")
    private String type;


    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

}
