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
import java.util.Date;

/**
 * <p>
 * 会员卡信息
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberCardInfo对象", description="会员卡信息")
public class MemberCardInfo extends Model<MemberCardInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员id")
    private Integer memberId;

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "会员卡号(前8位为身份证前8位,9-12为用户生日,13-16为自增/主键)")
    private String cardNumber;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "拼音")
    private String pyCode;

    @ApiModelProperty(value = "性别(1男,2女)")
    private Integer gender;

    @ApiModelProperty(value = "身份证")
    private String idcard;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "手机号")
    private String cellphone;

    @ApiModelProperty(value = "身份证地址")
    private String address;

    @ApiModelProperty(value = "是否实体卡(0否,1是)")
    private Integer entityCard;

    @ApiModelProperty(value = "卡类型(1E卡,2新民金卡)")
    private Integer cardType;

    @ApiModelProperty(value = "血型")
    private String blood;

    @ApiModelProperty(value = "星座")
    private String constellation;

    @ApiModelProperty(value = "身高cm")
    private Double height;

    @ApiModelProperty(value = "体重kg")
    private Double weight;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    @ApiModelProperty(value = "车辆型号")
    private String carType;

    @ApiModelProperty(value = "车架号")
    private String carSkeletonNumber;

    @ApiModelProperty(value = "车辆品牌")
    private String carBrand;

    @ApiModelProperty(value = "购车时间")
    private Date carBuyTime;

    @ApiModelProperty(value = "保险时间")
    private Date carInsuranceTime;

    @ApiModelProperty(value = "行驶证照片")
    private String drivingLicensePhoto;

    @ApiModelProperty(value = "支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败)")
    private Integer payStatus;

    @ApiModelProperty(value = "卡状态(0不可用,1可用)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
