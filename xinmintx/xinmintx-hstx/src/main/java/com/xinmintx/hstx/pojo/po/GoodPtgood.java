package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodPtgood对象", description="")
public class GoodPtgood extends Model<GoodPtgood> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "拼团自增id")
    @TableId(value = "ptgoods_id", type = IdType.AUTO)
    private Integer ptgoodsId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "拼团商品名称")
    private String ptgoodsName;

    @ApiModelProperty(value = "商品价格(默认价格)")
    private BigDecimal price;

    @ApiModelProperty(value = "拼团价格(优惠多少钱)")
    private BigDecimal ptPrice;

    @ApiModelProperty(value = "拼团人数(1-10)")
    private Integer ptSize;

    @ApiModelProperty(value = "拼团有效期(小时)")
    private Double ptValidhours;

    @ApiModelProperty(value = "拼团开始时间")
    private Date startTime;

    @ApiModelProperty(value = "拼团结束时间")
    private Date endTime;

    @ApiModelProperty(value = "商品拼团库存数量")
    private Integer ptgoodsNumber;

    @ApiModelProperty(value = "商品剪短描述")
    private String description;

    @ApiModelProperty(value = "商品详细描述")
    private String content;

    @ApiModelProperty(value = "商品微缩图")
    private String ptgoodsThumb;

    @ApiModelProperty(value = "商品详情轮播图")
    private String ptgoodsImgs;

    @ApiModelProperty(value = "商品添加时间")
    private Date addtime;

    @ApiModelProperty(value = "修改时间")
    private Date uptime;

    @ApiModelProperty(value = "启用，1，是；0，否")
    private Integer isSale;

    @ApiModelProperty(value = "拼团次数")
    private Integer ptTimes;

    @ApiModelProperty(value = "拼团销量")
    private Integer ptSales;

    @ApiModelProperty(value = "拼团类型（1代理商，2.惠商）")
    private Integer groupType;

    private String common;

    @ApiModelProperty(value = "活动名称")
    private String nameActivity;


    @Override
    protected Serializable pkVal() {
        return this.ptgoodsId;
    }

}
