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
@ApiModel(value="Profit对象", description="")
public class Profit extends Model<Profit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "本人提成")
    private Double selfProfit;

    @ApiModelProperty(value = "上级提成")
    private Double superProfit;

    @ApiModelProperty(value = "需要交纳费用")
    private Double cost;

    @ApiModelProperty(value = "用户角色(1,合伙人;3,代理;4,黄金商户;5,普通商户;6,银卡;7,社区商户) ")
    private String role;

    @ApiModelProperty(value = "描述")
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
