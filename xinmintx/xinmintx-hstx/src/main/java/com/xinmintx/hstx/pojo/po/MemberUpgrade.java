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
 * 会员卡升级金额或积分
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberUpgrade对象", description="会员卡升级金额或积分")
public class MemberUpgrade extends Model<MemberUpgrade> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "积分")
    private Integer integral;

    @ApiModelProperty(value = "会员卡类型(1,普通;2,银卡;,3,金卡;4,白金卡)")
    private String role;

    @ApiModelProperty(value = "描述")
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
