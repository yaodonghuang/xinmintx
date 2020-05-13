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
 * 限时抢购
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodPanicBuy对象", description="限时抢购")
public class GoodPanicBuy extends Model<GoodPanicBuy> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "售价")
    private BigDecimal price;

    @ApiModelProperty(value = "划线价格")
    private BigDecimal linePrice;

    @ApiModelProperty(value = "普通会员优惠金额")
    private BigDecimal generalPrice;

    @ApiModelProperty(value = "E卡会员优惠金额")
    private BigDecimal ePrice;

    @ApiModelProperty(value = "新民金卡优惠金额")
    private BigDecimal goldPrice;

    @ApiModelProperty(value = "抢购开始时间")
    private Date startTime;

    @ApiModelProperty(value = "抢购结束时间")
    private Date endTime;

    @ApiModelProperty(value = "商品抢购库存数量")
    private Integer stockNum;

    @ApiModelProperty(value = "展示图片")
    private String activityImg;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "启用(1是,0否)")
    private Integer isSale;

    @ApiModelProperty(value = "状态(0下架,1上架)")
    private Integer status;

    @ApiModelProperty(value = "初始销量(虚拟销量)")
    private Integer salesInitial;

    @ApiModelProperty(value = "实际销量")
    private Integer salesActual;

    @ApiModelProperty(value = "虚拟抢购用户id ',' 隔开 例: 1,2,3,4")
    private String virtualMember;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "限购(-1为不限)")
    private Integer restriction;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
