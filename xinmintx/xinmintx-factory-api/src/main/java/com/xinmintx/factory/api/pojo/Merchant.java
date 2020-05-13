package com.xinmintx.factory.api.pojo;

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
 * @author wcj
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 推荐人(用户表id)
     */
    private Integer recommender;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 手机号
     */
    private String cellphone;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 入驻类型(1,基本商户;2黄金商户)
     */
    private Integer merchantType;

    /**
     * 桌数
     */
    private Integer merchantTable;

    /**
     * 商户地址
     */
    private String address;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 区域代码
     */
    private String regionCode;

    /**
     * 商户所属的分公司id
     */
    private Integer merchantBranchOfficeId;

    /**
     * 商户营业额
     */
    private BigDecimal turnover;

    /**
     * 是否审核（0,否;1,是）
     */
    private Integer isReview;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人姓名
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人姓名
     */
    private String updateUser;

    /**
     * 商户分类 1.美食 2.水果 3.夜宵
     */
    private Integer merchantCategory;

    /**
     * 人均消费
     */
    private BigDecimal perCapita;

    /**
     * token
     */
    private String token;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
