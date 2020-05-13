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

/**
 * <p>
 * 红包活动记录
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RedEnvelopeRecord对象", description="红包活动记录")
public class RedEnvelopeRecord extends Model<RedEnvelopeRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "活动id")
    private Integer redActivitiesId;

    @ApiModelProperty(value = "红包类型id")
    private Integer redTypeId;

    @ApiModelProperty(value = "member表 id")
    private Integer memberId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
