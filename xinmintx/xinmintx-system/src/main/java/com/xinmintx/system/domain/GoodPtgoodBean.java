package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;

import java.util.Date;

/**
 * @ClassName:.GoodPtgoodBean
 * @author:chf
 * @Date:2019/12/17：18:02
 * @developerKits： win 10     jdk1.8
 */
public class GoodPtgoodBean {
    /** 拼团商品自增id */
    private Long ptgoodsId;

    /**商品Id*/
    private Long goodsId;

    /** 拼团商品名称 */
    @Excel(name = "拼团商品名称")
    private String ptgoodsName;

    /** 商品价格(默认价格) */
    @Excel(name = "商品价格(默认价格)")
    private Double price;

    /** 拼团价格 */
    @Excel(name = "拼团价格")
    private Double[] ptPrice;

    /** 拼团人数(2-10) */
    @Excel(name = "拼团人数(2-10)")
    private Long[] ptSize;

    /** 拼团有效期 */
    @Excel(name = "拼团有效期")
    private String ptValidhours;

    /** 拼团开始时间 */
    @Excel(name = "拼团开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 拼团结束时间 */
    @Excel(name = "拼团结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 商品库存数量 */
    @Excel(name = "商品库存数量")
    private Long ptgoodsNumber;

    /** 商品剪短描述 */
    @Excel(name = "商品剪短描述")
    private String description;

    /** 商品详细描述 */
    @Excel(name = "商品详细描述")
    private String content;

    /** 商品微缩图 */
    @Excel(name = "商品微缩图")
    private String ptgoodsThumb;

    /** 商品详情轮播图 */
    @Excel(name = "商品详情轮播图")
    private String ptgoodsImgs;

    /** 商品添加时间 */
    @Excel(name = "商品添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uptime;

    /** 销售，1，是；0，否 */
    @Excel(name = "销售，1，是；0，否")
    private String isSale;

    /** 拼团次数 */
    @Excel(name = "拼团次数")
    private Long groupTimes;

    /**拼团类型*/
    private Long groupType;

    /** $column.columnComment */
    @Excel(name = "拼团次数")
    private String common;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String nameActivity;

    public Long getPtgoodsId() {
        return ptgoodsId;
    }

    public void setPtgoodsId(Long ptgoodsId) {
        this.ptgoodsId = ptgoodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getPtgoodsName() {
        return ptgoodsName;
    }

    public void setPtgoodsName(String ptgoodsName) {
        this.ptgoodsName = ptgoodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double[] getPtPrice() {
        return ptPrice;
    }

    public void setPtPrice(Double[] ptPrice) {
        this.ptPrice = ptPrice;
    }

    public Long[] getPtSize() {
        return ptSize;
    }

    public void setPtSize(Long[] ptSize) {
        this.ptSize = ptSize;
    }

    public String getPtValidhours() {
        return ptValidhours;
    }

    public void setPtValidhours(String ptValidhours) {
        this.ptValidhours = ptValidhours;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPtgoodsNumber() {
        return ptgoodsNumber;
    }

    public void setPtgoodsNumber(Long ptgoodsNumber) {
        this.ptgoodsNumber = ptgoodsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPtgoodsThumb() {
        return ptgoodsThumb;
    }

    public void setPtgoodsThumb(String ptgoodsThumb) {
        this.ptgoodsThumb = ptgoodsThumb;
    }

    public String getPtgoodsImgs() {
        return ptgoodsImgs;
    }

    public void setPtgoodsImgs(String ptgoodsImgs) {
        this.ptgoodsImgs = ptgoodsImgs;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public Long getGroupTimes() {
        return groupTimes;
    }

    public void setGroupTimes(Long groupTimes) {
        this.groupTimes = groupTimes;
    }

    public Long getGroupType() {
        return groupType;
    }

    public void setGroupType(Long groupType) {
        this.groupType = groupType;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }
}
