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
 * 会员礼包关联表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberGift对象", description="会员礼包关联表")
public class MemberGift extends Model<MemberGift> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "礼包id")
    private Long giftId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "0 未删除  1 删除")
    private Integer ifDelete;

    @ApiModelProperty(value = "领取时的礼包价格")
    private BigDecimal price;

    @ApiModelProperty(value = "uuid,礼包唯一条码")
    private String uuid;

    @ApiModelProperty(value = "生日礼包预约时间(年月日)")
    private String appointTime;

    @ApiModelProperty(value = "生日礼包具体时间(时分)")
    private String time;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
