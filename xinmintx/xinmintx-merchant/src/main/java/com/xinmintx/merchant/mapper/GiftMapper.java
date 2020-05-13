package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.model.Gift;
import com.xinmintx.merchant.model.GiftPurchaseLogs;
import com.xinmintx.merchant.model.Member;
import com.xinmintx.merchant.model.MemberGiftResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 礼包Mapper
 */
public interface GiftMapper {

    Gift getGiftById(@Param("id") Long giftId);

    int insertGiftLog(GiftPurchaseLogs gpl);

    int deleteGiftByUuid(@Param("memberId") Integer memberId, @Param("uuid") String uuid);

    Member getMemberById(@Param("memberId") Integer memberId);

    Gift selectGiftByUuid(@Param("uuid") String uuid);

    Integer ifExistsMerchant(@Param("merchantId") Integer merchantId);

    List<Gift> getWaitWriteOffList(@Param("merchantId") Integer merchantId);

    List<MemberGiftResult> getWaitWriteOffDetailList(@Param("giftId") Long giftId);

    List<Gift> getAlreadyWriteOff(@Param("merchantId") Integer merchantId);

    Integer getFinishWriteOffNum(@Param("merchantId") Integer merchantId, @Param("giftId") Long giftId);

    Integer getWaitWriteOffQty(@Param("giftId") Long giftId);

    int updateBirthdayCount(@Param("memberId")Integer memberId);
}
