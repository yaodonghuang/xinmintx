package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import com.xinmintx.hstx.pojo.po.MemberCardOrder;
import com.xinmintx.hstx.pojo.po.MemberCardOrderDetail;
import com.xinmintx.hstx.pojo.po.MemberOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/8 0008
 * @time: 下午 21:23
 * @Description:
 */
@Data
public class MemberCardOrderVo extends MemberCardOrder {
    /**
     * 推荐人id
     */
    private Integer referrerId;
    /**
     * 订单详情
     */
    private List<MemberCardOrderDetail> details;
    /**
     * 会员卡信息
     */
    private MemberCardInfo cardInfo;
}
