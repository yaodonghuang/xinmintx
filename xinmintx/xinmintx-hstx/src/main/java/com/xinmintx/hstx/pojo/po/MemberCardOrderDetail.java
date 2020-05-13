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
 * 会员卡订单详情
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberCardOrderDetail对象", description="会员卡订单详情")
public class MemberCardOrderDetail extends Model<MemberCardOrderDetail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员卡订单id")
    private Integer cardOrderId;

    @ApiModelProperty(value = "会员卡信息id")
    private Integer cardId;

    @ApiModelProperty(value = "订单类型(0制卡费,1E卡,2新民金卡)")
    private Integer orderType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
