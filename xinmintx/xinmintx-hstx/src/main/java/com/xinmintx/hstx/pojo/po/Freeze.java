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
 * 冻结信息表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Freeze对象", description="冻结信息表")
public class Freeze extends Model<Freeze> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单表id")
    private Integer orderId;

    @ApiModelProperty(value = "会员表id")
    private Integer memberId;

    @ApiModelProperty(value = "1为user 2新民币 3为通证池 4factory 5消费获得的新民币 6矩阵 7金卡升级冻结")
    private Integer type;

    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "跟新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态 1待提款 2已提款 3已退款")
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
