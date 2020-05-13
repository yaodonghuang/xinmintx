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
 * 会员金额收支日志表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberAmountLog对象", description="会员金额收支日志表")
public class MemberAmountLog extends Model<MemberAmountLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "类型:1.冻结金额 2.可用金额")
    private String type;

    @ApiModelProperty(value = "收入或者支出金额")
    private BigDecimal price;

    @ApiModelProperty(value = "当前余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "来源:1.充值 2.消费分润来源(代理) 3.金卡分润 4.社区内帮办运费收入 5.商户拒单 6.消费7.提现 8.奖金池分配9.红包活动 10.E卡分润")
    private Integer source;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
