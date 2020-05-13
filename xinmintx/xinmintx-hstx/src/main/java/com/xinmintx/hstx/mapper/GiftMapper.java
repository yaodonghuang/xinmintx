package com.xinmintx.hstx.mapper;

import com.xinmintx.hstx.pojo.po.Gift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinmintx.hstx.pojo.po.GiftPurchaseLogs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 礼包表 Mapper 接口
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
public interface GiftMapper extends BaseMapper<Gift> {

    @Select("SELECT id FROM `gift` WHERE gift_type = 'merchant' AND gift_state = '0' AND id NOT IN (SELECT gift_id FROM `member_gift` WHERE member_id = #{id})")
    List<Integer> getGiftIds(@Param("id") Integer memberId);

    @Insert("insert into gift_purchase_logs (member_id, gift_id,\n" +
            "          price, balance,\n" +
            "          type, merchant_id)\n" +
            "        values (#{memberId,jdbcType=BIGINT}, #{giftId,jdbcType=BIGINT},\n" +
            "          #{price,jdbcType=DECIMAL}, #{balance,jdbcType=DECIMAL},\n" +
            "          #{type,jdbcType=VARCHAR}, #{merchantId,jdbcType=BIGINT})")
    int insertGiftLog(GiftPurchaseLogs gpl);

}
