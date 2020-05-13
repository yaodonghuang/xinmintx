package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户信息对象 user
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public class User extends BaseEntity
{
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

    /** 用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工) */
    @Excel(name = "用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工)")
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

    /** 是否审核（0,否;1,是） */
    @Excel(name = "是否审核", readConverterExp = "0=,否;1,是")
    private Integer isReview;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createUser;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 修改人姓名 */
    @Excel(name = "修改人姓名")
    private String updateUser;

    /** 最后登陆时间 */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLogin;

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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
            .append("isReview", getIsReview())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("lastLogin", getLastLogin())
            .toString();
    }
}
