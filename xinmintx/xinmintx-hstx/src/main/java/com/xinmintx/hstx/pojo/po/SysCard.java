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
 * 后台打卡表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysCard对象", description="后台打卡表")
public class SysCard extends Model<SysCard> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "录音")
    private String recordUrl;

    @ApiModelProperty(value = "美文")
    private String characters;

    @ApiModelProperty(value = "图片")
    private String pictureUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "1.早  2.晚")
    private Integer timeFrame;

    @ApiModelProperty(value = "备注")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
