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
 * 商品审核表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProcurementCommodities对象", description="商品审核表")
public class ProcurementCommodities extends Model<ProcurementCommodities> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "user  ID (这里是user id)")
    private Integer merchantId;

    @ApiModelProperty(value = "样品名称")
    private String sampleName;

    @ApiModelProperty(value = "样品类型")
    private String sampleType;

    @ApiModelProperty(value = "快递单号")
    private String trackingNumber;

    @ApiModelProperty(value = "供货商")
    private String supplier;

    @ApiModelProperty(value = "电话号")
    private String cellphone;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "市场价格")
    private Double marketValue;

    @ApiModelProperty(value = "采购价格")
    private Double procurementPrice;

    @ApiModelProperty(value = "图片id")
    private Integer pictureId;

    private String commonone;

    private String commontwo;

    @ApiModelProperty(value = "是否审核（0，未审核，1审核）")
    private Integer isReview;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
