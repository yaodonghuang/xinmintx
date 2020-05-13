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
 * 礼包表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Gift对象", description="礼包表")
public class Gift extends Model<Gift> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "礼包表主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "礼包名称")
    private String giftName;

    @ApiModelProperty(value = "用于区分,1:平台礼包(platform) 2:商户礼包(merchant) 3:分公司礼包(branchOffice)")
    private String giftType;

    @ApiModelProperty(value = "1.代金券(cashCoupon) 2.礼包(giftPackage) 3.生日礼包（birthGiftPackage）")
    private String giftGroup;

    @ApiModelProperty(value = "礼包购买价格")
    private BigDecimal price;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "截止日期")
    private Date endDate;

    @ApiModelProperty(value = "0:未作废  1:已作废")
    private Integer ifDelete;

    @ApiModelProperty(value = "礼包状态,0 启用 1 禁用")
    private String giftState;

    @ApiModelProperty(value = "来源id,平台id或者商户id,根据类型区分")
    private Long sourceId;

    @ApiModelProperty(value = "好评率")
    private BigDecimal favorableRate;

    @ApiModelProperty(value = "满减价格,(50-15)")
    private String fullReducePrice;

    @ApiModelProperty(value = "代金价格")
    private BigDecimal cashCoupon;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "剩余礼包数量")
    private Integer quantity;

    @ApiModelProperty(value = "人均消费")
    private BigDecimal perCapita;

    @ApiModelProperty(value = "礼包图片")
    private String giftPic;

    @ApiModelProperty(value = "总数量")
    private Integer totalQty;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
