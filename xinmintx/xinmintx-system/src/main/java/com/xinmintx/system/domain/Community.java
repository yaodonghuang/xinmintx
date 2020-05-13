package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 社区对象 community
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
public class Community extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 1.小区 2.村 */
    @Excel(name = "1.小区 2.村")
    private Integer type;

    /** 社区名称 */
    @Excel(name = "社区名称")
    private String name;

    /** 创始人会员id */
    @Excel(name = "创始人会员id")
    private Long createId;

    /** 所属区域 */
    @Excel(name = "所属区域")
    private String regionCode;

    /** 提货人 */
    @Excel(name = "提货人")
    private String consignee;

    /** 提货人手机号 */
    @Excel(name = "提货人手机号")
    private String phoneNumber;

    /** 提货地址 */
    @Excel(name = "提货地址")
    private String consigneeAddress;

    /** 提货点费用 */
    @Excel(name = "提货点费用")
    private Double consigneeMoney;

    /** 社区公告 */
    @Excel(name = "社区公告")
    private String notice;

    /** 社区图标 */
    @Excel(name = "社区图标")
    private String icon;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setRegionCode(String regionCode) 
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode() 
    {
        return regionCode;
    }
    public void setConsignee(String consignee) 
    {
        this.consignee = consignee;
    }

    public String getConsignee() 
    {
        return consignee;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setConsigneeAddress(String consigneeAddress) 
    {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeAddress() 
    {
        return consigneeAddress;
    }
    public void setConsigneeMoney(Double consigneeMoney) 
    {
        this.consigneeMoney = consigneeMoney;
    }

    public Double getConsigneeMoney() 
    {
        return consigneeMoney;
    }
    public void setNotice(String notice) 
    {
        this.notice = notice;
    }

    public String getNotice() 
    {
        return notice;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("name", getName())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .append("regionCode", getRegionCode())
            .append("consignee", getConsignee())
            .append("phoneNumber", getPhoneNumber())
            .append("consigneeAddress", getConsigneeAddress())
            .append("consigneeMoney", getConsigneeMoney())
            .append("notice", getNotice())
            .append("icon", getIcon())
            .toString();
    }
}
