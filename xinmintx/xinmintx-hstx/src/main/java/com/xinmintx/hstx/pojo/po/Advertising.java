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
 * 广告表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Advertising对象", description="广告表")
public class Advertising extends Model<Advertising> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "广告类型(1首页,2热销榜单,3今日精选,4分类)")
    private Integer adType;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "广告链接类型(1外部链接,2普通商品,3拼团商品,4热销榜单,5今日精选,6分类 7,限时抢购商品)")
    private Integer linkType;

    @ApiModelProperty(value = "外部广告链接")
    private String linkUrl;

    @ApiModelProperty(value = "关联id(商品/分类的id)")
    private Integer relateId;

    @ApiModelProperty(value = "广告位(当前页显示在第几个位置)")
    private Integer adPlace;

    @ApiModelProperty(value = "是否有效(0为无效,1为有效)")
    private Integer status;

    @ApiModelProperty(value = "广告图片")
    private String goodsPhotoUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
