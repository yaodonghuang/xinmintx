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
import java.util.Date;

/**
 * <p>
 * 商家信息表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Merchant对象", description="商家信息表")
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @ApiModelProperty(value = "推荐人(用户表id)")
    private Integer recommender;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "手机号")
    private String cellphone;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;

    @ApiModelProperty(value = "入驻类型(1,基本商户;2黄金商户;3社区商户)")
    private Integer merchantType;

    @ApiModelProperty(value = "桌数")
    private Integer merchantTable;

    @ApiModelProperty(value = "商户地址")
    private String address;

    @ApiModelProperty(value = "地区名称")
    private String regionName;

    @ApiModelProperty(value = "区域代码")
    private String regionCode;

    @ApiModelProperty(value = "商户所属的分公司id")
    private Integer merchantBranchOfficeId;

    @ApiModelProperty(value = "商户营业额")
    private BigDecimal turnover;

    @ApiModelProperty(value = "是否审核（0,否;1,是）")
    private Integer isReview;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人姓名")
    private String createUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改人姓名")
    private String updateUser;

    @ApiModelProperty(value = "商户分类 1.美食 2.水果 3.夜宵 4.果蔬商超")
    private Integer merchantCategory;

    @ApiModelProperty(value = "人均消费")
    private BigDecimal perCapita;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezingAmount;

    @ApiModelProperty(value = "可用金额")
    private BigDecimal cashAmount;

    private String shopName;

    private String shopAddress;

    @ApiModelProperty(value = "商户头像")
    private String avatar;

    @ApiModelProperty(value = "公告通知")
    private String announcement;

    @ApiModelProperty(value = "身份证照片正面")
    private String idcardFront;

    @ApiModelProperty(value = "身份证照片反面")
    private String idcardBack;

    @ApiModelProperty(value = "银行卡照片正面")
    private String bankCardFront;

    @ApiModelProperty(value = "银行卡照片反面")
    private String bankCardBack;

    @ApiModelProperty(value = "门头照片")
    private String doorHeadPhoto;

    @ApiModelProperty(value = "店内照片1")
    private String storePhotoOne;

    @ApiModelProperty(value = "店内照片2")
    private String storePhotoTwo;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "其他照片")
    private String otherPhoto;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "商铺规格")
    private String shopSpecification;

    @ApiModelProperty(value = "经营模式")
    private String businessModel;

    @ApiModelProperty(value = "提现起提金额")
    private BigDecimal amountRaised;

    @ApiModelProperty(value = "商户分类细分：1:水果;2：水产;3：蔬菜;4：肉类;5：粮油调味")
    private String merchantCategoryDetail;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
