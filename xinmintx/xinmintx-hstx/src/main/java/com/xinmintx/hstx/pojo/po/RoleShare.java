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
 * 各个角色推荐角色分润规则
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleShare对象", description="各个角色推荐角色分润规则")
public class RoleShare extends Model<RoleShare> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人;8,用户/银卡;9,矩阵;10,初级讲师;11,中级讲师;12,高级讲师)")
    private Integer userRole;

    @ApiModelProperty(value = "提交人角色名称")
    private String roleName;

    @ApiModelProperty(value = "分润类型(1,推荐获取分成;2,享受分成,3,上级获取分成;4,享受团队分成)")
    private Integer recommendType;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,银卡)")
    private Integer recommendRole;

    @ApiModelProperty(value = "需要创建的角色名称")
    private String recommendName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
