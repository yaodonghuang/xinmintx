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
 * 购物车详情
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShoppingCartDetail对象", description="购物车详情")
public class ShoppingCartDetail extends Model<ShoppingCartDetail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "member表id(用户id)")
    private Integer memberId;

    @ApiModelProperty(value = "商品")
    private Integer goodsId;

    @ApiModelProperty(value = "数量")
    private Integer total;

    @ApiModelProperty(value = "是否显示(0,不显示,1显示)(购买以后状态为0)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "规格id")
    private Integer skuId;

    @ApiModelProperty(value = "1为普通商品,2为拼团,3.社区菜篮子,4为抢购")
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
