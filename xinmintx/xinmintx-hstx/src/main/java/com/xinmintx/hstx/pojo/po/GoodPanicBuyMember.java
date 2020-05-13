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
 * 抢购商品用户购买表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodPanicBuyMember对象", description="抢购商品用户购买表")
public class GoodPanicBuyMember extends Model<GoodPanicBuyMember> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "抢购商品表id")
    private Integer panicBuyId;

    @ApiModelProperty(value = "用户id")
    private Integer memberId;

    @ApiModelProperty(value = "skuid")
    private Integer skuid;

    @ApiModelProperty(value = "购买的数量")
    private Integer quantity;

    @ApiModelProperty(value = "创建支付")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
