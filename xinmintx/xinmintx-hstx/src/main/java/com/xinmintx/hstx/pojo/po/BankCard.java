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
 * 
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BankCard对象", description="")
public class BankCard extends Model<BankCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;

    @ApiModelProperty(value = "状态 ")
    private Integer start;

    @ApiModelProperty(value = "银行预留手机号")
    private String cellphone;

    @ApiModelProperty(value = "用户表id")
    private Integer userId;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行卡类型")
    private String bankType;

    @ApiModelProperty(value = "银行卡绑定姓名")
    private String cardholderName;

    @ApiModelProperty(value = "绑定银行卡身份证")
    private String commonOne;

    @ApiModelProperty(value = "第三方userId(开户ID)")
    private String commonTwo;

    @ApiModelProperty(value = "惠商id")
    private Integer memberId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
