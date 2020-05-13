package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员卡对象 member
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
public class Member extends BaseEntity
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

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idcard;

    /** 推荐人(用户表id) */
    @Excel(name = "推荐人(用户表id)")
    private Long recommender;

    /** 会员卡类型(0,普通卡;1,E卡;2,新民金卡) */
    @Excel(name = "会员卡类型(0,普通卡;1,E卡;2,新民金卡)")
    private Long memberType;

    /** 是否审核（0,否;1,是） */
    @Excel(name = "是否审核", readConverterExp = "0=,否;1,是")
    private Integer isReview;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createUser;

    /** 修改人姓名 */
    @Excel(name = "修改人姓名")
    private String updateUser;

    /** 微信openid */
    @Excel(name = "微信openid")
    private String openid;

    /** token */
    @Excel(name = "token")
    private String token;

    /** 微信头像 */
    @Excel(name = "微信头像")
    private String avatarUrl;

    /** 平台礼包可领次数 */
    @Excel(name = "平台礼包可领次数")
    private Integer platformCount;

    /** 商铺礼包可领次数 */
    @Excel(name = "商铺礼包可领次数")
    private Integer merchantCount;

    /** 分公司礼包可领取次数 */
    @Excel(name = "分公司礼包可领取次数")
    private Integer branchOfficeCount;

    /** 生日礼包可领取次数 */
    @Excel(name = "生日礼包可领取次数")
    private Integer birthGiftCount;

    /** 转换积分 */
    @Excel(name = "转换积分")
    private Integer integral;

    /** 新民币 */
    @Excel(name = "新民币")
    private BigDecimal newCurrency;

    /** 新民豆 */
    @Excel(name = "新民豆")
    private BigDecimal newBeans;

    /** 代理iD */
    @Excel(name = "代理iD")
    private Integer userId;

    /** 冻结的新民豆 */
    @Excel(name = "冻结的新民豆")
    private BigDecimal freezeBeans;

    /** 冻结的新民币 */
    @Excel(name = "冻结的新民币")
    private BigDecimal freezeCurrency;

    /** 礼包状态 0无礼包 1待领取 */
    @Excel(name = "礼包状态 0无礼包 1待领取")
    private Integer giftStart;

    /** 矩阵数code */
    @Excel(name = "矩阵数code")
    private String treeCode;

    /** 地区code */
    @Excel(name = "地区code")
    private String regionCode;

    /** 会员生日 */
    @Excel(name = "会员生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 会员卡信息表id */
    @Excel(name = "会员卡信息表id")
    private Long cardId;

    /** 会员卡状态(0过期,1可用) */
    @Excel(name = "会员卡状态(0过期,1可用)")
    private Integer cardStatus;

    /** 会员卡有效期至 */
    @Excel(name = "会员卡有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cardIndate;

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
    public void setMemberType(Long memberType) 
    {
        this.memberType = memberType;
    }

    public Long getMemberType() 
    {
        return memberType;
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
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }
    public void setPlatformCount(Integer platformCount) 
    {
        this.platformCount = platformCount;
    }

    public Integer getPlatformCount() 
    {
        return platformCount;
    }
    public void setMerchantCount(Integer merchantCount) 
    {
        this.merchantCount = merchantCount;
    }

    public Integer getMerchantCount() 
    {
        return merchantCount;
    }
    public void setBranchOfficeCount(Integer branchOfficeCount) 
    {
        this.branchOfficeCount = branchOfficeCount;
    }

    public Integer getBranchOfficeCount() 
    {
        return branchOfficeCount;
    }
    public void setBirthGiftCount(Integer birthGiftCount) 
    {
        this.birthGiftCount = birthGiftCount;
    }

    public Integer getBirthGiftCount() 
    {
        return birthGiftCount;
    }
    public void setIntegral(Integer integral) 
    {
        this.integral = integral;
    }

    public Integer getIntegral() 
    {
        return integral;
    }
    public void setNewCurrency(BigDecimal newCurrency)
    {
        this.newCurrency = newCurrency;
    }

    public BigDecimal getNewCurrency()
    {
        return newCurrency;
    }
    public void setNewBeans(BigDecimal newBeans)
    {
        this.newBeans = newBeans;
    }

    public BigDecimal getNewBeans()
    {
        return newBeans;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setFreezeBeans(BigDecimal freezeBeans)
    {
        this.freezeBeans = freezeBeans;
    }

    public BigDecimal getFreezeBeans()
    {
        return freezeBeans;
    }
    public void setFreezeCurrency(BigDecimal freezeCurrency)
    {
        this.freezeCurrency = freezeCurrency;
    }

    public BigDecimal getFreezeCurrency()
    {
        return freezeCurrency;
    }
    public void setGiftStart(Integer giftStart) 
    {
        this.giftStart = giftStart;
    }

    public Integer getGiftStart() 
    {
        return giftStart;
    }
    public void setTreeCode(String treeCode) 
    {
        this.treeCode = treeCode;
    }

    public String getTreeCode() 
    {
        return treeCode;
    }
    public void setRegionCode(String regionCode) 
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode() 
    {
        return regionCode;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setCardId(Long cardId) 
    {
        this.cardId = cardId;
    }

    public Long getCardId() 
    {
        return cardId;
    }
    public void setCardStatus(Integer cardStatus) 
    {
        this.cardStatus = cardStatus;
    }

    public Integer getCardStatus() 
    {
        return cardStatus;
    }
    public void setCardIndate(Date cardIndate) 
    {
        this.cardIndate = cardIndate;
    }

    public Date getCardIndate() 
    {
        return cardIndate;
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
            .append("memberType", getMemberType())
            .append("isReview", getIsReview())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .append("openid", getOpenid())
            .append("token", getToken())
            .append("avatarUrl", getAvatarUrl())
            .append("platformCount", getPlatformCount())
            .append("merchantCount", getMerchantCount())
            .append("branchOfficeCount", getBranchOfficeCount())
            .append("birthGiftCount", getBirthGiftCount())
            .append("integral", getIntegral())
            .append("newCurrency", getNewCurrency())
            .append("newBeans", getNewBeans())
            .append("userId", getUserId())
            .append("freezeBeans", getFreezeBeans())
            .append("freezeCurrency", getFreezeCurrency())
            .append("giftStart", getGiftStart())
            .append("treeCode", getTreeCode())
            .append("regionCode", getRegionCode())
            .append("birthday", getBirthday())
            .append("cardId", getCardId())
            .append("cardStatus", getCardStatus())
            .append("cardIndate", getCardIndate())
            .toString();
    }
}
