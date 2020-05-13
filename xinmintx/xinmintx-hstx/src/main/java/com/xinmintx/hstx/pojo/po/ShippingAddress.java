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
 * 收货地址
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShippingAddress对象", description="收货地址")
public class ShippingAddress extends Model<ShippingAddress> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer memberId;

    @ApiModelProperty(value = "收货人姓名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String cellphone;

    @ApiModelProperty(value = "所在地区")
    private String region;

    @ApiModelProperty(value = "地区代码")
    private String regionCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "是否为默认地址(0不是,1是)")
    private Integer defaultAddress;

    @ApiModelProperty(value = "省id")
    private Integer provinceId;

    @ApiModelProperty(value = "市id")
    private Integer cityId;

    @ApiModelProperty(value = "区id")
    private Integer areaId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
