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
 * 厂家提现日志关联表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FactoryCashInfo对象", description="厂家提现日志关联表")
public class FactoryCashInfo extends Model<FactoryCashInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提现关联表主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "厂家id")
    private Long factoryId;

    @ApiModelProperty(value = "请求流水")
    private String requestSn;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "member表id")
    private Integer memberId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
