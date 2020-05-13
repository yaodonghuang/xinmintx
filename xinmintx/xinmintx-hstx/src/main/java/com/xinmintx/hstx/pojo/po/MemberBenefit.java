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
 * 消费分润
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberBenefit对象", description="消费分润")
public class MemberBenefit extends Model<MemberBenefit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型(1,合伙人(商品提供商) 2,分公司 3,消费推荐人 4,矩阵 5,通证池 6,分公司推荐人 7消费获得的新民币 8利润分润比例)")
    private Integer type;

    @ApiModelProperty(value = "比例")
    private Double percent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
