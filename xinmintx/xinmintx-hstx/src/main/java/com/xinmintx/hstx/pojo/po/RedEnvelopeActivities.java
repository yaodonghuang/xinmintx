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
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RedEnvelopeActivities对象", description="活动表")
public class RedEnvelopeActivities extends Model<RedEnvelopeActivities> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "活动开始时间")
    private Date redStartTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date redEndTime;

    @ApiModelProperty(value = "活动状态（0.未启动  1.进行中   2.结束）")
    private Integer start;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
