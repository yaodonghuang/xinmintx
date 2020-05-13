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
 * 打卡签到记录评论
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CheckInComment对象", description="打卡签到记录评论")
public class CheckInComment extends Model<CheckInComment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "打卡记录表主键(对应打卡记录)")
    private Integer checkInId;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "会员id")
    private Integer commentName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "评论人头像url")
    private String pictureUrl;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
