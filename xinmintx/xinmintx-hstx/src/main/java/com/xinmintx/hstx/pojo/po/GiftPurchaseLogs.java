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
 * 礼包购买日志表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GiftPurchaseLogs对象", description="礼包购买日志表")
public class GiftPurchaseLogs extends Model<GiftPurchaseLogs> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "礼包id")
    private Long giftId;

    @ApiModelProperty(value = "礼包购买价格")
    private BigDecimal price;

    @ApiModelProperty(value = "购买之后剩余新民币")
    private BigDecimal balance;

    @ApiModelProperty(value = "交易时间")
    private Date createTime;

    @ApiModelProperty(value = "标记付款还是退款,pay(付款) refund(退款)  writeOff(核销) income(进账)")
    private String type;

    @ApiModelProperty(value = "商户id")
    private Long merchantId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
