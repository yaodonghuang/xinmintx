package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 厂家信息对象 factory
 *
 * @author xinmintx
 * @date 2019-11-29
 */
public class Factory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long factoryId;

    /**
     * 厂家类型
     */
    @Excel(name = "厂家类型")
    private String type;

    /**
     * 厂家名称
     */
    @Excel(name = "厂家名称")
    private String name;

    /**
     * 厂家地址
     */
    @Excel(name = "厂家地址")
    private String address;

    /**
     * 厂家联系电话
     */
    @Excel(name = "厂家联系电话")
    private String phone;

    /**
     * 厂家头像路径
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     *  厂家唯一码
     */
    private String factoryCode;

    private String bankCard;// 银行卡号

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("factoryId", getFactoryId())
                .append("type", getType())
                .append("name", getName())
                .append("address", getAddress())
                .append("phone", getPhone())
                .append("password", getPassword())
                .append("salt", getSalt())
                .append("createTime", getCreateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
