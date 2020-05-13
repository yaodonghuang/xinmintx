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
 * 代理商设置员工推商户获取的提成
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StaffProfit对象", description="代理商设置员工推商户获取的提成")
public class StaffProfit extends Model<StaffProfit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "user表id(员工)")
    private Integer userId;

    @ApiModelProperty(value = "员工推荐商户获取的提成")
    private Double profit;

    @ApiModelProperty(value = "商户类型(1,基本商户;2黄金商户)")
    private Integer merchantType;

    @ApiModelProperty(value = "员工获取商户营业额百分比")
    private Double percent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
