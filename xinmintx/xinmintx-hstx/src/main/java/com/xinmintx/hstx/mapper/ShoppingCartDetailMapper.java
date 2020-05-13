package com.xinmintx.hstx.mapper;

import com.xinmintx.hstx.pojo.po.ShoppingCartDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 购物车详情 Mapper 接口
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
public interface ShoppingCartDetailMapper extends BaseMapper<ShoppingCartDetail> {

    /**
     * 查询用户在购物车添加的抢购商品的数量
     * @param memberId
     * @param panicId
     * @return
     */
    @Select("select ifnull(sum(total),0) from shopping_cart_detail where member_id=#{memberId} and goods_id=#{panicId} and status=1 and type=4")
    Integer getMemberPanicTotalByPanicId(@Param("memberId") Integer memberId, @Param("panicId") Integer panicId);
}
