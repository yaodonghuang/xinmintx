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
 * 各个角色账号任务考核到期时间
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccountExpiration对象", description="各个角色账号任务考核到期时间")
public class AccountExpiration extends Model<AccountExpiration> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,股东)")
    private Integer userRole;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "倒计时天数")
    private Integer daysNo;

    @ApiModelProperty(value = "具体到期时间")
    private Date dayTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
