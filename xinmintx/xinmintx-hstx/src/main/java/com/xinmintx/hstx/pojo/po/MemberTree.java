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
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MemberTree对象", description="")
public class MemberTree extends Model<MemberTree> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员树id")
    @TableId(value = "tree_id", type = IdType.AUTO)
    private Long treeId;

    @ApiModelProperty(value = "会员树code")
    private String treeCode;

    @ApiModelProperty(value = "会员表主键id")
    private Integer memberId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "上层树id")
    private Long parentTreeId;


    @Override
    protected Serializable pkVal() {
        return this.treeId;
    }

}
