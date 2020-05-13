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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 厂家信息表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Factory对象", description="厂家信息表")
public class Factory extends Model<Factory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "factory_id", type = IdType.AUTO)
    private Long factoryId;

    @ApiModelProperty(value = "厂家类型")
    private String type;

    @ApiModelProperty(value = "厂家唯一code")
    private String factoryCode;

    @ApiModelProperty(value = "厂家名称")
    private String name;

    @ApiModelProperty(value = "厂家头像上传图片")
    private String avatar;

    @ApiModelProperty(value = "厂家地址")
    private String address;

    @ApiModelProperty(value = "厂家联系电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "姓名")
    private String personname;

    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;

    @ApiModelProperty(value = "地区名称")
    private String regionName;

    @ApiModelProperty(value = "地区代码")
    private String regionCode;

    @ApiModelProperty(value = "推荐人Id")
    @TableField("referrerId")
    private Integer referrerId;

    @ApiModelProperty(value = "图片id")
    private String reservedPhoto;

    @ApiModelProperty(value = "预留手机号")
    private String phoneId;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezingAmount;

    @ApiModelProperty(value = "可用金额")
    private BigDecimal cashAmount;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "位置code")
    private String locationCode;

    @ApiModelProperty(value = "工厂规模")
    private String factoryScale;

    @ApiModelProperty(value = "工厂产品")
    private String factoryProduct;

    @ApiModelProperty(value = "身份证照片正面")
    private String idcardFront;

    @ApiModelProperty(value = "身份证照片反面")
    private String idcardBack;

    @ApiModelProperty(value = "银行卡照片正面")
    private String bankCardFront;

    @ApiModelProperty(value = "银行卡照片反面")
    private String bankCardBack;

    @ApiModelProperty(value = "门头照片")
    private String doorHeadPhoto;

    @ApiModelProperty(value = "作业照片")
    private String workPhoto;

    @ApiModelProperty(value = "场内照片")
    private String factoryPhoto;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "其他照片")
    private String otherPhoto;

    @ApiModelProperty(value = "品牌")
    private String brand;


    @Override
    protected Serializable pkVal() {
        return this.factoryId;
    }

}
