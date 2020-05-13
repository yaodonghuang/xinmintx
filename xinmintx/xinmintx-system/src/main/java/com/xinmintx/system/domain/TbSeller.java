package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 tb_seller
 * 
 * @author xinmintx
 * @date 2019-12-25
 */
public class TbSeller extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private String sellerId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String name;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String nickName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** EMAIL */
    @Excel(name = "EMAIL")
    private String email;

    /** 公司手机 */
    @Excel(name = "公司手机")
    private String mobile;

    /** 公司电话 */
    @Excel(name = "公司电话")
    private String telephone;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String linkmanName;

    /** 联系人QQ */
    @Excel(name = "联系人QQ")
    private String linkmanQq;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String linkmanMobile;

    /** 联系人EMAIL */
    @Excel(name = "联系人EMAIL")
    private String linkmanEmail;

    /** 营业执照号 */
    @Excel(name = "营业执照号")
    private String licenseNumber;

    /** 税务登记证号 */
    @Excel(name = "税务登记证号")
    private String taxNumber;

    /** 组织机构代码 */
    @Excel(name = "组织机构代码")
    private String orgNumber;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private Long address;

    /** 公司LOGO图 */
    @Excel(name = "公司LOGO图")
    private String logoPic;

    /** 简介 */
    @Excel(name = "简介")
    private String brief;

    /** 法定代表人 */
    @Excel(name = "法定代表人")
    private String legalPerson;

    /** 法定代表人身份证 */
    @Excel(name = "法定代表人身份证")
    private String legalPersonCardId;

    /** 开户行账号名称 */
    @Excel(name = "开户行账号名称")
    private String bankUser;

    /** 开户行 */
    @Excel(name = "开户行")
    private String bankName;

    public void setSellerId(String sellerId) 
    {
        this.sellerId = sellerId;
    }

    public String getSellerId() 
    {
        return sellerId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAddressDetail(String addressDetail) 
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail() 
    {
        return addressDetail;
    }
    public void setLinkmanName(String linkmanName) 
    {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanName() 
    {
        return linkmanName;
    }
    public void setLinkmanQq(String linkmanQq) 
    {
        this.linkmanQq = linkmanQq;
    }

    public String getLinkmanQq() 
    {
        return linkmanQq;
    }
    public void setLinkmanMobile(String linkmanMobile) 
    {
        this.linkmanMobile = linkmanMobile;
    }

    public String getLinkmanMobile() 
    {
        return linkmanMobile;
    }
    public void setLinkmanEmail(String linkmanEmail) 
    {
        this.linkmanEmail = linkmanEmail;
    }

    public String getLinkmanEmail() 
    {
        return linkmanEmail;
    }
    public void setLicenseNumber(String licenseNumber) 
    {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() 
    {
        return licenseNumber;
    }
    public void setTaxNumber(String taxNumber) 
    {
        this.taxNumber = taxNumber;
    }

    public String getTaxNumber() 
    {
        return taxNumber;
    }
    public void setOrgNumber(String orgNumber) 
    {
        this.orgNumber = orgNumber;
    }

    public String getOrgNumber() 
    {
        return orgNumber;
    }
    public void setAddress(Long address) 
    {
        this.address = address;
    }

    public Long getAddress() 
    {
        return address;
    }
    public void setLogoPic(String logoPic) 
    {
        this.logoPic = logoPic;
    }

    public String getLogoPic() 
    {
        return logoPic;
    }
    public void setBrief(String brief) 
    {
        this.brief = brief;
    }

    public String getBrief() 
    {
        return brief;
    }
    public void setLegalPerson(String legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() 
    {
        return legalPerson;
    }
    public void setLegalPersonCardId(String legalPersonCardId) 
    {
        this.legalPersonCardId = legalPersonCardId;
    }

    public String getLegalPersonCardId() 
    {
        return legalPersonCardId;
    }
    public void setBankUser(String bankUser) 
    {
        this.bankUser = bankUser;
    }

    public String getBankUser() 
    {
        return bankUser;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("sellerId", getSellerId())
            .append("name", getName())
            .append("nickName", getNickName())
            .append("password", getPassword())
            .append("email", getEmail())
            .append("mobile", getMobile())
            .append("telephone", getTelephone())
            .append("status", getStatus())
            .append("addressDetail", getAddressDetail())
            .append("linkmanName", getLinkmanName())
            .append("linkmanQq", getLinkmanQq())
            .append("linkmanMobile", getLinkmanMobile())
            .append("linkmanEmail", getLinkmanEmail())
            .append("licenseNumber", getLicenseNumber())
            .append("taxNumber", getTaxNumber())
            .append("orgNumber", getOrgNumber())
            .append("address", getAddress())
            .append("logoPic", getLogoPic())
            .append("brief", getBrief())
            .append("createTime", getCreateTime())
            .append("legalPerson", getLegalPerson())
            .append("legalPersonCardId", getLegalPersonCardId())
            .append("bankUser", getBankUser())
            .append("bankName", getBankName())
            .toString();
    }
}
