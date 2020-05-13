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
 * 推荐人关系表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberReferrer对象", description="推荐人关系表")
public class MemberReferrer extends Model<MemberReferrer> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐人id")
    private Integer referrerId;

    @ApiModelProperty(value = "member表id")
    private Integer memberId;

    @ApiModelProperty(value = "是否确定关系(0否,1是)")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
