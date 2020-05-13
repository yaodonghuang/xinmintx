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

/**
 * <p>
 * 提现规格
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DepositSpecification对象", description="提现规格")
public class DepositSpecification extends Model<DepositSpecification> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "起提金额")
    private BigDecimal depositSum;

    @ApiModelProperty(value = "手续费")
    private Double serviceCharge;

    @ApiModelProperty(value = "预留一")
    private String reservedOne;

    @ApiModelProperty(value = "预留二")
    private String reservedTwo;

    @ApiModelProperty(value = "预留三")
    private String reservedThree;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
