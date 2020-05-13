package com.xinmintx.community.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:.Community
 * @author:chf
 * @Date:2020/2/10：15:38
 * @developerKits： win 10     jdk1.8
 */
public class Community {
    private Long id;
    private Integer type;//1.小区 2.村
    private String name;//社区名称
    private Long createId;//创始人会员id
    private Date createTime;//创建时间
    private String regionCode;//所属区域
    private String consignee;//提货人
    private String phoneNumber;//手机号
    private String consigneeAddress;//提货地址
    private BigDecimal consigneeMoney;
    private String notice;//社区公告
    private String icon;//社区图标
    private String memberName;//社区昵称
    private String proprieterName;//社长昵称
    private String cellphone;//社长手机号
    private Integer cloaking;//是否开启隐身, 0 不开启 1 开启
    private Integer deputyHelp;//是否开启内帮办, 0 不开启 1 开启
    /**
     * 水果供应商
     */
    private Integer fruitSupplierId;

    /**
     * 水产供应商
     */
    private Integer aquaticSupplierId;

    /**
     * 蔬菜供应商
     */
    private Integer vegetablesSupplierId;

    /**
     * 肉类供应商
     */
    private Integer meatSupplierId;

    /**
     * 粮油调味供应商
     */
    private Integer grainAndOilSupplierId;

    public BigDecimal getConsigneeMoney() {
        return consigneeMoney;
    }

    public void setConsigneeMoney(BigDecimal consigneeMoney) {
        this.consigneeMoney = consigneeMoney;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getProprieterName() {
        return proprieterName;
    }

    public void setProprieterName(String proprieterName) {
        this.proprieterName = proprieterName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNotice() {
        return notice;
    }
    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public Integer getFruitSupplierId() {
        return fruitSupplierId;
    }

    public void setFruitSupplierId(Integer fruitSupplierId) {
        this.fruitSupplierId = fruitSupplierId;
    }

    public Integer getAquaticSupplierId() {
        return aquaticSupplierId;
    }

    public void setAquaticSupplierId(Integer aquaticSupplierId) {
        this.aquaticSupplierId = aquaticSupplierId;
    }

    public Integer getVegetablesSupplierId() {
        return vegetablesSupplierId;
    }

    public void setVegetablesSupplierId(Integer vegetablesSupplierId) {
        this.vegetablesSupplierId = vegetablesSupplierId;
    }

    public Integer getMeatSupplierId() {
        return meatSupplierId;
    }

    public void setMeatSupplierId(Integer meatSupplierId) {
        this.meatSupplierId = meatSupplierId;
    }

    public Integer getGrainAndOilSupplierId() {
        return grainAndOilSupplierId;
    }

    public void setGrainAndOilSupplierId(Integer grainAndOilSupplierId) {
        this.grainAndOilSupplierId = grainAndOilSupplierId;
    }

    public Integer getCloaking() {
        return cloaking;
    }

    public void setCloaking(Integer cloaking) {
        this.cloaking = cloaking;
    }

    public Integer getDeputyHelp() {
        return deputyHelp;
    }

    public void setDeputyHelp(Integer deputyHelp) {
        this.deputyHelp = deputyHelp;
    }
}
