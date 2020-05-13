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
 * 用户信息表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户信息表")
public class User extends Model<User> {

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

    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @ApiModelProperty(value = "推荐人(用户表id)")
    private Integer recommender;

    @ApiModelProperty(value = "用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工；7，高级合伙人 ; 10.学员)")
    private Integer userRole;

    @ApiModelProperty(value = "账号状态(0:不可用,1:可用,2:未付款)")
    private Integer status;

    @ApiModelProperty(value = "代理推荐的商户数量")
    private Integer amount;

    @ApiModelProperty(value = "分公司名称")
    private String companyName;

    @ApiModelProperty(value = "分公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "地区名称")
    private String regionName;

    @ApiModelProperty(value = "区域代码")
    private String regionCode;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "微信头像")
    private String avatar;

    @ApiModelProperty(value = "微信refresh_token(用于刷新access_token,有效期30天)")
    private String refreshToken;

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

    @ApiModelProperty(value = "最后登陆时间")
    private Date lastLogin;

    @ApiModelProperty(value = "分公司所属的股东或者合伙人id(代理所属的合伙人id)")
    private Integer partnerId;

    @ApiModelProperty(value = "所属股东id")
    private Integer shareholderId;

    @ApiModelProperty(value = "倒计时截止时间")
    private Date endTime;

    @ApiModelProperty(value = "预留字段1")
    private String commonOne;

    @ApiModelProperty(value = "所属合伙人id")
    private String commonTwo;

    private Integer operatorid;

    @ApiModelProperty(value = "开户状态:0未开户，1开户")
    private Integer accountStart;

    @ApiModelProperty(value = "开户Id")
    private String accountId;

    @ApiModelProperty(value = "绑卡id")
    private String tiedCardId;

    private String token;

    @ApiModelProperty(value = "1为讲师 0 不是讲师  角色是否为讲师  代理 代理以上角色 才可以成为讲师")
    private Integer lecturerStatus;

    @ApiModelProperty(value = "讲师等级1初级 2中级 3高级")
    private Integer lecturerGrade;

    @ApiModelProperty(value = "1是学员 0不是学员")
    private Integer student;

    @ApiModelProperty(value = "讲师id")
    private Integer teacherId;

    @ApiModelProperty(value = "角色 是否 为推荐代理达到指定人数升级为合伙人 1 是； 0 否")
    private Integer startAgentPartner;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
