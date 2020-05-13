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
 * 积分获取方式
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IntegralAccess对象", description="积分获取方式")
public class IntegralAccess extends Model<IntegralAccess> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "积分获取方式代码(1,打卡,2,评论,3,点赞,4,消费)")
    private Integer accessType;

    @ApiModelProperty(value = "获取方式说明")
    private String description;

    @ApiModelProperty(value = "可获得的积分值")
    private Double integralValue;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
