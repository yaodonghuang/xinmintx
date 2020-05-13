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
@ApiModel(value="GoodPtcodeInfo对象", description="")
public class GoodPtcodeInfo extends Model<GoodPtcodeInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "拼团商品规格id")
    private Integer skuId;

    @ApiModelProperty(value = "拼团id")
    private Integer pid;

    @ApiModelProperty(value = "拼团编号")
    private String ptcode;

    @ApiModelProperty(value = "商品id")
    private Integer ptgoodId;

    @ApiModelProperty(value = "单价")
    private BigDecimal perPrice;

    @ApiModelProperty(value = "收货地址id")
    private Integer addressId;

    @ApiModelProperty(value = "收货人")
    private String addressName;

    @ApiModelProperty(value = "加入时间")
    private Date addtimeDatetime;

    @ApiModelProperty(value = "‘结束时间‘")
    private Date endtimeDatetime;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "是否团长(0否,1是)")
    private Integer isHeader;

    @ApiModelProperty(value = "是否缺货(0否,1是)")
    private Integer lack;

    @ApiModelProperty(value = "是否入团成功(0否,1是)")
    private Integer isJoin;

    @ApiModelProperty(value = "订单id")
    private Integer goodsOrderId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
