package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/15 0015
 * @time: 上午 9:50
 * @Description:
 */
@Data
public class MemberVo extends Member {
    /**
     * 验证码
     */
    private String code;
    /**
     * 手机号
     */
    private String phone;
    /**
     * data资产值
     */
    private Integer dataCount;
    /**
     * 用户下游账号数
     */
    private Integer memberCount;
    /**
     * 金卡值
     */
    private Integer goldCount;
    /**
     * 地址
     */
    private String address;
    /**
     * 会员卡信息
     */
    private MemberCardInfo cardInfo;

    /**
     * 收益
     */
    private BigDecimal earnings;

    /**
     * 推荐的E卡人数
     */
    private Integer eCardCount;

    /**
     * 推荐的E卡排名(超过99为99+)
     */
    private String ranking;

    /**
     * 推荐的时间
     */
    private Date referrerTime;

    /**
     * 累计推荐E卡数量
     */
    private Integer totalECardCount;

    /**
     * 累计奖金
     */
    private BigDecimal totalBonus;
}
