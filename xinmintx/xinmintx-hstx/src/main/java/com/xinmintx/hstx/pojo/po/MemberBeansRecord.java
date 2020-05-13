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
 * 新民豆变动记录
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberBeansRecord对象", description="新民豆变动记录")
public class MemberBeansRecord extends Model<MemberBeansRecord> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer memberId;

    @ApiModelProperty(value = "新民豆变动情况(100;-100)")
    private BigDecimal beansChange;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
