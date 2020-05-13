package com.xinmintx.merchant.model.mbplusModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

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
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer orderId;


    private String name;


    private String idcard;

    private Integer recommender;


    private String merchantName;


    private String cellphone;


    private String bankCard;


    private Integer merchantType;


    private Integer merchantTable;


    private String address;


    private String regionName;


    private String regionCode;


    private Integer merchantBranchOfficeId;


    private BigDecimal turnover;

    private Integer isReview;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer merchantCategory;

    private BigDecimal perCapita;

    private String token;

    private BigDecimal freezingAmount;

    private BigDecimal cashAmount;

    private String shopName;

    private String shopAddress;

    private String avatar;

    private String announcement;

    private String idcardFront;

    private String idcardBack;

    private String bankCardFront;

    private String bankCardBack;

    private String doorHeadPhoto;

    private String storePhotoOne;

    private String storePhotoTwo;

    private String businessLicense;

    private String otherPhoto;

    private String latitude;

    private String longitude;

    private String shopSpecification;

    private String businessModel;

    private BigDecimal amountRaised;

    private String merchantCategoryDetail;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
