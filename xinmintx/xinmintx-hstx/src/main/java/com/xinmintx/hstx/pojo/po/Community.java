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
 * 社区表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Community对象", description="社区表")
public class Community extends Model<Community> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "1.小区 2.村")
    private Integer type;

    @ApiModelProperty(value = "社区名称")
    private String name;

    @ApiModelProperty(value = "创始人会员id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "所属区域")
    private String regionCode;

    @ApiModelProperty(value = "提货人")
    private String consignee;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "提货地址")
    private String consigneeAddress;

    @ApiModelProperty(value = "社区公告")
    private String notice;

    @ApiModelProperty(value = "社区图标")
    private String icon;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
