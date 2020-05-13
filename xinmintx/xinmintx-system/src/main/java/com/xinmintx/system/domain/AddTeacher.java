package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName:.AddTeacher
 * @author:chf
 * @Date:2020/1/17：17:15
 * @developerKits： win 10     jdk1.8
 */
public class AddTeacher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellphone;

    /** 性别(0,未知;1,男;2,女) */
    @Excel(name = "性别(0,未知;1,男;2,女)")
    private Integer gender;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 推荐人(用户表id) */
    @Excel(name = "推荐人(用户表id)")
    private Long recommender;

    /** 用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工；7，高级合伙人 ; 10.学员) */
    @Excel(name = "用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工；7，高级合伙人 ; 10.学员)")
    private Integer userRole;

    /** 账号状态(0:不可用,1:可用,2:未付款) */
    @Excel(name = "账号状态(0:不可用,1:可用,2:未付款)")
    private Integer status;

    /** 代理推荐的商户数量 */
    @Excel(name = "代理推荐的商户数量")
    private Long amount;

    /** 分公司名称 */
    @Excel(name = "分公司名称")
    private String companyName;

    /** 分公司地址 */
    @Excel(name = "分公司地址")
    private String companyAddress;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String regionName;

    /** 区域代码 */
    @Excel(name = "区域代码")
    private String regionCode;

    /** 微信openid */
    @Excel(name = "微信openid")
    private String openid;

    /** 微信头像 */
    @Excel(name = "微信头像")
    private String avatar;

    /** 微信refresh_token(用于刷新access_token,有效期30天) */
    @Excel(name = "微信refresh_token(用于刷新access_token,有效期30天)")
    private String refreshToken;

    /** 是否审核（0,否;1,是） */
    @Excel(name = "是否审核", readConverterExp = "0=,否;1,是")
    private Integer isReview;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createUser;

    /** 修改人姓名 */
    @Excel(name = "修改人姓名")
    private String updateUser;

    /** 最后登陆时间 */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLogin;

    /** 分公司所属的股东或者合伙人id(代理所属的合伙人id) */
    @Excel(name = "分公司所属的股东或者合伙人id(代理所属的合伙人id)")
    private Long partnerId;

    /** 所属股东id */
    @Excel(name = "所属股东id")
    private Long shareholderId;

    /** 倒计时截止时间 */
    @Excel(name = "倒计时截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String commonOne;

    /** 所属合伙人id */
    @Excel(name = "所属合伙人id")
    private String commonTwo;

    /** $column.columnComment */
    @Excel(name = "所属合伙人id")
    private Long operatorid;

    /** 开户状态:0未开户，1开户 */
    @Excel(name = "开户状态:0未开户，1开户")
    private Long accountStart;

    /** 开户Id */
    @Excel(name = "开户Id")
    private String accountId;

    /** 绑卡id */
    @Excel(name = "绑卡id")
    private String tiedCardId;

    /** $column.columnComment */
    @Excel(name = "绑卡id")
    private String token;

    /** 1为讲师 0 不是讲师  角色是否为讲师  代理 代理以上角色 才可以成为讲师 */
    @Excel(name = "1为讲师 0 不是讲师  角色是否为讲师  代理 代理以上角色 才可以成为讲师")
    private Integer lecturerStatus;

    /** 讲师等级1初级 2中级 3高级 */
    @Excel(name = "讲师等级1初级 2中级 3高级")
    private Integer lecturerGrade;

    /** 1是学员 0不是学员 */
    @Excel(name = "1是学员 0不是学员")
    private Integer student;

    /** 讲师id */
    @Excel(name = "讲师id")
    private Long teacherId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getCellphone()
    {
        return cellphone;
    }
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Integer getGender()
    {
        return gender;
    }
    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getIdcard()
    {
        return idcard;
    }
    public void setRecommender(Long recommender)
    {
        this.recommender = recommender;
    }

    public Long getRecommender()
    {
        return recommender;
    }
    public void setUserRole(Integer userRole)
    {
        this.userRole = userRole;
    }

    public Integer getUserRole()
    {
        return userRole;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }
    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public String getRegionName()
    {
        return regionName;
    }
    public void setRegionCode(String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode()
    {
        return regionCode;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }
    public void setIsReview(Integer isReview)
    {
        this.isReview = isReview;
    }

    public Integer getIsReview()
    {
        return isReview;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }
    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }

    public Long getPartnerId()
    {
        return partnerId;
    }
    public void setShareholderId(Long shareholderId)
    {
        this.shareholderId = shareholderId;
    }

    public Long getShareholderId()
    {
        return shareholderId;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setCommonOne(String commonOne)
    {
        this.commonOne = commonOne;
    }

    public String getCommonOne()
    {
        return commonOne;
    }
    public void setCommonTwo(String commonTwo)
    {
        this.commonTwo = commonTwo;
    }

    public String getCommonTwo()
    {
        return commonTwo;
    }
    public void setOperatorid(Long operatorid)
    {
        this.operatorid = operatorid;
    }

    public Long getOperatorid()
    {
        return operatorid;
    }
    public void setAccountStart(Long accountStart)
    {
        this.accountStart = accountStart;
    }

    public Long getAccountStart()
    {
        return accountStart;
    }
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountId()
    {
        return accountId;
    }
    public void setTiedCardId(String tiedCardId)
    {
        this.tiedCardId = tiedCardId;
    }

    public String getTiedCardId()
    {
        return tiedCardId;
    }
    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
    public void setLecturerStatus(Integer lecturerStatus)
    {
        this.lecturerStatus = lecturerStatus;
    }

    public Integer getLecturerStatus()
    {
        return lecturerStatus;
    }
    public void setLecturerGrade(Integer lecturerGrade)
    {
        this.lecturerGrade = lecturerGrade;
    }

    public Integer getLecturerGrade()
    {
        return lecturerGrade;
    }
    public void setStudent(Integer student)
    {
        this.student = student;
    }

    public Integer getStudent()
    {
        return student;
    }
    public void setTeacherId(Long teacherId)
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId()
    {
        return teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("name", getName())
                .append("cellphone", getCellphone())
                .append("gender", getGender())
                .append("idcard", getIdcard())
                .append("recommender", getRecommender())
                .append("userRole", getUserRole())
                .append("status", getStatus())
                .append("amount", getAmount())
                .append("companyName", getCompanyName())
                .append("companyAddress", getCompanyAddress())
                .append("regionName", getRegionName())
                .append("regionCode", getRegionCode())
                .append("openid", getOpenid())
                .append("avatar", getAvatar())
                .append("refreshToken", getRefreshToken())
                .append("isReview", getIsReview())
                .append("createUser", getCreateUser())
                .append("createTime", getCreateTime())
                .append("updateUser", getUpdateUser())
                .append("updateTime", getUpdateTime())
                .append("lastLogin", getLastLogin())
                .append("partnerId", getPartnerId())
                .append("shareholderId", getShareholderId())
                .append("endTime", getEndTime())
                .append("commonOne", getCommonOne())
                .append("commonTwo", getCommonTwo())
                .append("operatorid", getOperatorid())
                .append("accountStart", getAccountStart())
                .append("accountId", getAccountId())
                .append("tiedCardId", getTiedCardId())
                .append("token", getToken())
                .append("lecturerStatus", getLecturerStatus())
                .append("lecturerGrade", getLecturerGrade())
                .append("student", getStudent())
                .append("teacherId", getTeacherId())
                .toString();
    }
}
