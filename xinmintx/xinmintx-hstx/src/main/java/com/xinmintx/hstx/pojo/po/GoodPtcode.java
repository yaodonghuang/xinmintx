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
@ApiModel(value="GoodPtcode对象", description="")
public class GoodPtcode extends Model<GoodPtcode> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "‘团长id‘")
    private Integer uid;

    @ApiModelProperty(value = "‘拼团商品id‘")
    private Integer ptgoodsId;

    @ApiModelProperty(value = "‘拼团编号‘")
    private String ptcode;

    @ApiModelProperty(value = "‘拼团人数‘")
    private Integer ptnumber;

    @ApiModelProperty(value = "‘创建日期‘")
    private Date addtimeDatetime;

    @ApiModelProperty(value = "‘结束时间‘")
    private Date endtimeDatetime;

    @ApiModelProperty(value = "数量")
    private Integer common;

    @ApiModelProperty(value = "状态(0不可拼,1可拼,2拼团成功,3拼团失败)")
    private Integer status;

    @ApiModelProperty(value = "拼团类型(1惠商,2代理商)")
    private Integer ptType;

    @ApiModelProperty(value = "代理商拼团id")
    private Integer ptUserId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
