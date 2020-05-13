package com.xinmintx.hstx.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xinmintx.hstx.pojo.po.Goods;
import com.xinmintx.hstx.pojo.po.GoodsPublic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
@Data
public class GoodsVo extends Goods {

    private Integer percent;

    private Integer sales;

    /**
     * 会员名称
     */
    private String memberType;
    /**
     * 会员价格
     */
    private BigDecimal memberPrice;

    /**
     * 虚拟用户头像列表
     */
    private List<String> virtualMembers;

    /**
     * 当前时间戳
     */
    private Long nowTime;

    /**
     * 抢购结束时间戳
     */
    private Long endTime;

    /**
     * 会员优惠标语
     */
    private String discounts;

    /**
     * 新民币获取提示
     */
    private String newCurrency;

    /**
     * 限购数量
     */
    private Integer restriction;
    /**
     * 商品公共部分
     */
    private List<GoodsPublic> goodsPublics;

    private List<String> turnsPhotoList;

    private List<Map<String,String>> parameterList;
}
