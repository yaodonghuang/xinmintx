package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会员卡信息对象 member_card_info
 * 
 * @author xinmintx
 * @date 2020-02-09
 */
public class MemberCardInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;

    /** 会员卡号(前8位为身份证前8位,9-12为用户生日,13-16为自增/主键) */
    @Excel(name = "会员卡号(前8位为身份证前8位,9-12为用户生日,13-16为自增/主键)")
    private String cardNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pyCode;

    /** 性别(1男,2女) */
    @Excel(name = "性别(1男,2女)")
    private Integer gender;

    /** 身份证 */
    @Excel(name = "身份证")
    private String idcard;

    /** 生日 */
    @Excel(name = "生日")
    private String birthday;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellphone;

    /** 身份证地址 */
    @Excel(name = "身份证地址")
    private String address;

    /** 是否实体卡(0否,1是) */
    @Excel(name = "是否实体卡(0否,1是)")
    private Integer entityCard;

    /** 卡类型(1E卡,2新民金卡) */
    @Excel(name = "卡类型(1E卡,2新民金卡)")
    private Integer cardType;

    /** 血型 */
    @Excel(name = "血型")
    private String blood;

    /** 星座 */
    @Excel(name = "星座")
    private String constellation;

    /** 身高cm */
    @Excel(name = "身高cm")
    private Double height;

    /** 体重kg */
    @Excel(name = "体重kg")
    private Double weight;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String carType;

    /** 车架号 */
    @Excel(name = "车架号")
    private String carSkeletonNumber;

    /** 车辆品牌 */
    @Excel(name = "车辆品牌")
    private String carBrand;

    /** 购车时间 */
    @Excel(name = "购车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date carBuyTime;

    /** 保险时间 */
    @Excel(name = "保险时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date carInsuranceTime;

    /** 行驶证照片 */
    @Excel(name = "行驶证照片")
    private String drivingLicensePhoto;

    /** 支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败) */
    @Excel(name = "支付状态(0:待付款,1:已付款,2:退款中,3已退款,4退款失败)")
    private Long payStatus;

    /** 卡状态(0不可用,1可用) */
    @Excel(name = "卡状态(0不可用,1可用)")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setCardNumber(String cardNumber) 
    {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() 
    {
        return cardNumber;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPyCode(String pyCode) 
    {
        this.pyCode = pyCode;
    }

    public String getPyCode() 
    {
        return pyCode;
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
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setCellphone(String cellphone) 
    {
        this.cellphone = cellphone;
    }

    public String getCellphone() 
    {
        return cellphone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setEntityCard(Integer entityCard) 
    {
        this.entityCard = entityCard;
    }

    public Integer getEntityCard() 
    {
        return entityCard;
    }
    public void setCardType(Integer cardType) 
    {
        this.cardType = cardType;
    }

    public Integer getCardType() 
    {
        return cardType;
    }
    public void setBlood(String blood) 
    {
        this.blood = blood;
    }

    public String getBlood() 
    {
        return blood;
    }
    public void setConstellation(String constellation) 
    {
        this.constellation = constellation;
    }

    public String getConstellation() 
    {
        return constellation;
    }
    public void setHeight(Double height) 
    {
        this.height = height;
    }

    public Double getHeight() 
    {
        return height;
    }
    public void setWeight(Double weight) 
    {
        this.weight = weight;
    }

    public Double getWeight() 
    {
        return weight;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setCarNumber(String carNumber) 
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber() 
    {
        return carNumber;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setCarSkeletonNumber(String carSkeletonNumber) 
    {
        this.carSkeletonNumber = carSkeletonNumber;
    }

    public String getCarSkeletonNumber() 
    {
        return carSkeletonNumber;
    }
    public void setCarBrand(String carBrand) 
    {
        this.carBrand = carBrand;
    }

    public String getCarBrand() 
    {
        return carBrand;
    }
    public void setCarBuyTime(Date carBuyTime) 
    {
        this.carBuyTime = carBuyTime;
    }

    public Date getCarBuyTime() 
    {
        return carBuyTime;
    }
    public void setCarInsuranceTime(Date carInsuranceTime) 
    {
        this.carInsuranceTime = carInsuranceTime;
    }

    public Date getCarInsuranceTime() 
    {
        return carInsuranceTime;
    }
    public void setDrivingLicensePhoto(String drivingLicensePhoto) 
    {
        this.drivingLicensePhoto = drivingLicensePhoto;
    }

    public String getDrivingLicensePhoto() 
    {
        return drivingLicensePhoto;
    }
    public void setPayStatus(Long payStatus) 
    {
        this.payStatus = payStatus;
    }

    public Long getPayStatus() 
    {
        return payStatus;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("cardNumber", getCardNumber())
            .append("name", getName())
            .append("pyCode", getPyCode())
            .append("gender", getGender())
            .append("idcard", getIdcard())
            .append("birthday", getBirthday())
            .append("cellphone", getCellphone())
            .append("address", getAddress())
            .append("entityCard", getEntityCard())
            .append("cardType", getCardType())
            .append("blood", getBlood())
            .append("constellation", getConstellation())
            .append("height", getHeight())
            .append("weight", getWeight())
            .append("education", getEducation())
            .append("carNumber", getCarNumber())
            .append("carType", getCarType())
            .append("carSkeletonNumber", getCarSkeletonNumber())
            .append("carBrand", getCarBrand())
            .append("carBuyTime", getCarBuyTime())
            .append("carInsuranceTime", getCarInsuranceTime())
            .append("drivingLicensePhoto", getDrivingLicensePhoto())
            .append("payStatus", getPayStatus())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
