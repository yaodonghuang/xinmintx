package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 银卡信息
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Silver extends Model<Silver> {

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
     * 手机号
     */
    private String cellphone;

    /**
     * 性别(0,未知;1,男;2,女)
     */
    private Integer gender;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 推荐人(用户表id)
     */
    private Integer recommender;

    /**
     * 是否审核（0,否;1,是）
     */
    private Integer isReview;

    /**
     * 创建人姓名
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人姓名
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
