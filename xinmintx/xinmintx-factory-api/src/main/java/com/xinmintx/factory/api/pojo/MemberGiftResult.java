package com.xinmintx.factory.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MemberGiftResult {
    private Integer memberId;// 会员id
    private String name;// 会员名称
    private String cellphone;// 会员电话
    private String giftGroup;// 代金券还是礼包
    private Date createTime;// 礼包创建时间
    private Date endDate;// 礼包截止日期
    private String fullReducePrice;// 礼包满多少优惠
    private BigDecimal cashCoupon;// 礼包优惠金额
    private String giftPic;// 礼包图片
    private String createDate;
    private String endTime;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFullReducePrice() {
        return fullReducePrice;
    }

    public void setFullReducePrice(String fullReducePrice) {
        this.fullReducePrice = fullReducePrice;
    }

    public BigDecimal getCashCoupon() {
        return cashCoupon;
    }

    public void setCashCoupon(BigDecimal cashCoupon) {
        this.cashCoupon = cashCoupon;
    }

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getGiftGroup() {
        return giftGroup;
    }

    public void setGiftGroup(String giftGroup) {
        this.giftGroup = giftGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
