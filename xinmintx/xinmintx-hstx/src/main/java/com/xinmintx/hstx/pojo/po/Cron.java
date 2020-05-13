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
@ApiModel(value="Cron对象", description="")
public class Cron extends Model<Cron> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "定时器配置")
    private String cron;

    @ApiModelProperty(value = "设置具体触发时间")
    private Date time;

    @ApiModelProperty(value = "定时器类型")
    private String type;

    @ApiModelProperty(value = "当前启用定时器状态，0.未开启，1.任务结束成功 2.任务结束失败 3.已开启表达式 4.已开启触发时间")
    private String nowState;

    @ApiModelProperty(value = "定时器执行的类名")
    private String className;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
