package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商家信息对象 merchant
 * 
 * @author xinmintx
 * @date 2020-03-04
 */
public class Merchant extends BaseEntity
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

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 推荐人(用户表id) */
    @Excel(name = "推荐人(用户表id)")
    private Long recommender;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merchantName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellphone;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankCard;

    /** 入驻类型(1,基本商户;2黄金商户;3社区商户) */
    @Excel(name = "入驻类型(1,基本商户;2黄金商户;3社区商户)")
    private Integer merchantType;

    /** 桌数 */
    @Excel(name = "桌数")
    private Long merchantTable;

    /** 商户地址 */
    @Excel(name = "商户地址")
    private String address;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String regionName;

    /** 区域代码 */
    @Excel(name = "区域代码")
    private String regionCode;

    /** 商户所属的分公司id */
    @Excel(name = "商户所属的分公司id")
    private Long merchantBranchOfficeId;

    /** 商户营业额 */
    @Excel(name = "商户营业额")
    private Double turnover;

    /** 是否审核（0,否;1,是） */
    @Excel(name = "是否审核", readConverterExp = "0=,否;1,是")
    private Integer isReview;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createUser;

    /** 修改人姓名 */
    @Excel(name = "修改人姓名")
    private String updateUser;

    /** 商户分类 1.美食 2.水果 3.夜宵 4.果蔬商超 */
    @Excel(name = "商户分类 1.美食 2.水果 3.夜宵 4.果蔬商超")
    private Integer merchantCategory;

    /** 人均消费 */
    @Excel(name = "人均消费")
    private Double perCapita;

    /** token */
    @Excel(name = "token")
    private String token;

    /** 冻结金额 */
    @Excel(name = "冻结金额")
    private Double freezingAmount;

    /** 可用金额 */
    @Excel(name = "可用金额")
    private Double cashAmount;

    /** $column.columnComment */
    @Excel(name = "可用金额")
    private String shopName;

    /** $column.columnComment */
    @Excel(name = "可用金额")
    private String shopAddress;

    /** 商户头像 */
    @Excel(name = "商户头像")
    private String avatar;

    /** 公告通知 */
    @Excel(name = "公告通知")
    private String announcement;

    /** 身份证照片正面 */
    @Excel(name = "身份证照片正面")
    private String idcardFront;

    /** 身份证照片反面 */
    @Excel(name = "身份证照片反面")
    private String idcardBack;

    /** 银行卡照片正面 */
    @Excel(name = "银行卡照片正面")
    private String bankCardFront;

    /** 银行卡照片反面 */
    @Excel(name = "银行卡照片反面")
    private String bankCardBack;

    /** 门头照片 */
    @Excel(name = "门头照片")
    private String doorHeadPhoto;

    /** 店内照片1 */
    @Excel(name = "店内照片1")
    private String storePhotoOne;

    /** 店内照片2 */
    @Excel(name = "店内照片2")
    private String storePhotoTwo;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String businessLicense;

    /** 其他照片 */
    @Excel(name = "其他照片")
    private String otherPhoto;

    /** 维度 */
    @Excel(name = "维度")
    private Long latitude;

    /** 经度 */
    @Excel(name = "经度")
    private Long longitude;

    /** 商铺规格 */
    @Excel(name = "商铺规格")
    private String shopSpecification;

    /** 经营模式 */
    @Excel(name = "经营模式")
    private String businessModel;

    /** 提现起提金额 */
    @Excel(name = "提现起提金额")
    private Double amountRaised;

    /** 商户手续费 */
    @Excel(name = "商户手续费")
    private BigDecimal serviceCharge;

    private String merchantCategoryDetail;

    private String[] threeType;

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

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
    public void setMerchantName(String merchantName)
    {
        this.merchantName = merchantName;
    }

    public String getMerchantName()
    {
        return merchantName;
    }
    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getCellphone()
    {
        return cellphone;
    }
    public void setBankCard(String bankCard)
    {
        this.bankCard = bankCard;
    }

    public String getBankCard()
    {
        return bankCard;
    }
    public void setMerchantType(Integer merchantType)
    {
        this.merchantType = merchantType;
    }

    public Integer getMerchantType()
    {
        return merchantType;
    }
    public void setMerchantTable(Long merchantTable)
    {
        this.merchantTable = merchantTable;
    }

    public Long getMerchantTable()
    {
        return merchantTable;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
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
    public void setMerchantBranchOfficeId(Long merchantBranchOfficeId)
    {
        this.merchantBranchOfficeId = merchantBranchOfficeId;
    }

    public Long getMerchantBranchOfficeId()
    {
        return merchantBranchOfficeId;
    }
    public void setTurnover(Double turnover)
    {
        this.turnover = turnover;
    }

    public Double getTurnover()
    {
        return turnover;
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
    public void setMerchantCategory(Integer merchantCategory)
    {
        this.merchantCategory = merchantCategory;
    }

    public Integer getMerchantCategory()
    {
        return merchantCategory;
    }
    public void setPerCapita(Double perCapita)
    {
        this.perCapita = perCapita;
    }

    public Double getPerCapita()
    {
        return perCapita;
    }
    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
    public void setFreezingAmount(Double freezingAmount)
    {
        this.freezingAmount = freezingAmount;
    }

    public Double getFreezingAmount()
    {
        return freezingAmount;
    }
    public void setCashAmount(Double cashAmount)
    {
        this.cashAmount = cashAmount;
    }

    public Double getCashAmount()
    {
        return cashAmount;
    }
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getShopName()
    {
        return shopName;
    }
    public void setShopAddress(String shopAddress)
    {
        this.shopAddress = shopAddress;
    }

    public String getShopAddress()
    {
        return shopAddress;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setAnnouncement(String announcement)
    {
        this.announcement = announcement;
    }

    public String getAnnouncement()
    {
        return announcement;
    }
    public void setIdcardFront(String idcardFront)
    {
        this.idcardFront = idcardFront;
    }

    public String getIdcardFront()
    {
        return idcardFront;
    }
    public void setIdcardBack(String idcardBack)
    {
        this.idcardBack = idcardBack;
    }

    public String getIdcardBack()
    {
        return idcardBack;
    }
    public void setBankCardFront(String bankCardFront)
    {
        this.bankCardFront = bankCardFront;
    }

    public String getBankCardFront()
    {
        return bankCardFront;
    }
    public void setBankCardBack(String bankCardBack)
    {
        this.bankCardBack = bankCardBack;
    }

    public String getBankCardBack()
    {
        return bankCardBack;
    }
    public void setDoorHeadPhoto(String doorHeadPhoto)
    {
        this.doorHeadPhoto = doorHeadPhoto;
    }

    public String getDoorHeadPhoto()
    {
        return doorHeadPhoto;
    }
    public void setStorePhotoOne(String storePhotoOne)
    {
        this.storePhotoOne = storePhotoOne;
    }

    public String getStorePhotoOne()
    {
        return storePhotoOne;
    }
    public void setStorePhotoTwo(String storePhotoTwo)
    {
        this.storePhotoTwo = storePhotoTwo;
    }

    public String getStorePhotoTwo()
    {
        return storePhotoTwo;
    }
    public void setBusinessLicense(String businessLicense)
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense()
    {
        return businessLicense;
    }
    public void setOtherPhoto(String otherPhoto)
    {
        this.otherPhoto = otherPhoto;
    }

    public String getOtherPhoto()
    {
        return otherPhoto;
    }
    public void setLatitude(Long latitude)
    {
        this.latitude = latitude;
    }

    public Long getLatitude()
    {
        return latitude;
    }
    public void setLongitude(Long longitude)
    {
        this.longitude = longitude;
    }

    public Long getLongitude()
    {
        return longitude;
    }
    public void setShopSpecification(String shopSpecification)
    {
        this.shopSpecification = shopSpecification;
    }

    public String getShopSpecification()
    {
        return shopSpecification;
    }
    public void setBusinessModel(String businessModel)
    {
        this.businessModel = businessModel;
    }

    public String getBusinessModel()
    {
        return businessModel;
    }
    public void setAmountRaised(Double amountRaised)
    {
        this.amountRaised = amountRaised;
    }

    public Double getAmountRaised()
    {
        return amountRaised;
    }

    public String getMerchantCategoryDetail() {
        return merchantCategoryDetail;
    }

    public void setMerchantCategoryDetail(String merchantCategoryDetail) {
        this.merchantCategoryDetail = merchantCategoryDetail;
    }

    public String[] getThreeType() {
        return threeType;
    }

    public void setThreeType(String[] threeType) {
        this.threeType = threeType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("name", getName())
                .append("idcard", getIdcard())
                .append("recommender", getRecommender())
                .append("merchantName", getMerchantName())
                .append("cellphone", getCellphone())
                .append("bankCard", getBankCard())
                .append("merchantType", getMerchantType())
                .append("merchantTable", getMerchantTable())
                .append("address", getAddress())
                .append("regionName", getRegionName())
                .append("regionCode", getRegionCode())
                .append("merchantBranchOfficeId", getMerchantBranchOfficeId())
                .append("turnover", getTurnover())
                .append("isReview", getIsReview())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .append("merchantCategory", getMerchantCategory())
                .append("perCapita", getPerCapita())
                .append("token", getToken())
                .append("freezingAmount", getFreezingAmount())
                .append("cashAmount", getCashAmount())
                .append("shopName", getShopName())
                .append("shopAddress", getShopAddress())
                .append("avatar", getAvatar())
                .append("announcement", getAnnouncement())
                .append("idcardFront", getIdcardFront())
                .append("idcardBack", getIdcardBack())
                .append("bankCardFront", getBankCardFront())
                .append("bankCardBack", getBankCardBack())
                .append("doorHeadPhoto", getDoorHeadPhoto())
                .append("storePhotoOne", getStorePhotoOne())
                .append("storePhotoTwo", getStorePhotoTwo())
                .append("businessLicense", getBusinessLicense())
                .append("otherPhoto", getOtherPhoto())
                .append("latitude", getLatitude())
                .append("longitude", getLongitude())
                .append("shopSpecification", getShopSpecification())
                .append("businessModel", getBusinessModel())
                .append("amountRaised", getAmountRaised())
                .append("merchantCategoryDetail", getMerchantCategoryDetail())
                .append("threeType", getThreeType())
                .toString();
    }
}
