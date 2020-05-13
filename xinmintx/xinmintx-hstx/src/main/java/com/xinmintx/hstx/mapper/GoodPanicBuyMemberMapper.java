package com.xinmintx.hstx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinmintx.hstx.pojo.po.GoodPanicBuyMember;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 抢购商品用户购买表 Mapper 接口
 * </p>
 *
 * @author wcj
 * @since 2020-02-20
 */
public interface GoodPanicBuyMemberMapper extends BaseMapper<GoodPanicBuyMember> {

    @Select("select ifnull(sum(quantity),0) from good_panic_buy_member where panic_buy_id=#{panicId} and member_id=#{memberId}")
    Integer getMemberBuyTotal(@Param("panicId") Integer panicId, @Param("memberId") Integer memberId);

}
