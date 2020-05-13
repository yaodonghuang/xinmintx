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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员卡
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Member对象", description="会员卡")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String cellphone;

    @ApiModelProperty(value = "性别(0,未知;1,男;2,女)")
    private Integer gender;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "推荐人(用户表id)")
    private Integer recommender;

    @ApiModelProperty(value = "会员卡类型(0,普通卡;1,E卡;2,新民金卡)")
    private Integer memberType;

    @ApiModelProperty(value = "是否审核（0,否;1,是）")
    private Integer isReview;

    @ApiModelProperty(value = "创建人姓名")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人姓名")
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "微信头像")
    private String avatarUrl;

    @ApiModelProperty(value = "平台礼包可领次数")
    private Integer platformCount;

    @ApiModelProperty(value = "商铺礼包可领次数")
    private Integer merchantCount;

    @ApiModelProperty(value = "分公司礼包可领取次数")
    private Integer branchOfficeCount;

    @ApiModelProperty(value = "生日礼包可领取次数")
    private Integer birthGiftCount;

    @ApiModelProperty(value = "转换积分")
    private Integer integral;

    @ApiModelProperty(value = "新民币")
    private BigDecimal newCurrency;

    @ApiModelProperty(value = "新民豆")
    private BigDecimal newBeans;

    @ApiModelProperty(value = "代理iD")
    private Integer userId;

    @ApiModelProperty(value = "冻结的新民豆")
    private BigDecimal freezeBeans;

    @ApiModelProperty(value = "冻结的新民币")
    private BigDecimal freezeCurrency;

    @ApiModelProperty(value = "礼包状态 0无礼包 1待领取")
    private Integer giftStart;

    @ApiModelProperty(value = "矩阵数code")
    private String treeCode;

    @ApiModelProperty(value = "地区code")
    private String regionCode;

    @ApiModelProperty(value = "会员生日")
    private Date birthday;

    @ApiModelProperty(value = "会员卡信息表id")
    private Integer cardId;

    @ApiModelProperty(value = "会员卡状态(0过期,1可用)")
    private Integer cardStatus;

    @ApiModelProperty(value = "会员卡有效期至")
    private Date cardIndate;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezingAmount;

    @ApiModelProperty(value = "可用金额")
    private BigDecimal cashAmount;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
