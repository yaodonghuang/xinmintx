package com.xinmintx.community.model;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: com.xinmintx.merchant.model.MerchantGoods
 * @Author:Pikachu
 * @Date: 2020/2/13 11:13
 * @Version: v1.0
 */

public class MerchantGoods {
    private Long id;
    /**
     * 商户id
     */
    private Long merchantId;
    /**
     * 菜名
     */
    private String name;
    /**
     * 分类:1.新鲜蔬菜 2.海鲜水产 3.粮油调味 4.时令水果
     */
    private Integer type;
    /**
     * 社区价格
     */
    private Double communityPrice;
    /**
     * 线上价格
     */
    private Double onlinePrice;
    /**
     * 描述菜品
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 上架状态:1.上架 2 下架
     */
    private Integer shelfStatus;
    /**
     * 创建订单时的商品数量
     */
    private Integer quantity;
    /**
     *  创建订单时的地址id
     */
    private Integer addressId;
    /**
     *  创建订单时的备注
     */
    private String remark;
    /**
     *
     */
    private Integer memberId;
    /**
     * 社区id
     */
    private Long communityId;
    /**
     * 创建订单时的图片
     */
    private String pic;
    /**
     * 图片集合
     */
    /**
     * 商品规格
     */
    private Long bigdecimal;

    public Long getBigdecimal() {
        return bigdecimal;
    }

    public void setBigdecimal(Long bigdecimal) {
        this.bigdecimal = bigdecimal;
    }

    private List<MerchantGoodsPic> picList;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getCommunityPrice() {
        return communityPrice;
    }

    public void setCommunityPrice(Double communityPrice) {
        this.communityPrice = communityPrice;
    }

    public Double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(Double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public List<MerchantGoodsPic> getPicList() {
        return picList;
    }

    public void setPicList(List<MerchantGoodsPic> picList) {
        this.picList = picList;
    }
}
