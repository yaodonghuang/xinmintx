package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 礼包Mapper
 */
public interface GiftMapper {
    List<Gift> getPlatformList(@Param("giftType")String giftType, @Param("sourceId")Long sourceId, @Param("giftGroup")String giftGroup);

    Integer getMemberIdByToken(@Param("token")String token);

    Integer ifExistsGiftThisMember(@Param("memberId")Integer memberId, @Param("giftId")Long giftId);

    int getGift(@Param("memberId")Integer memberId, @Param("giftId")Long giftId, @Param("price") BigDecimal price, @Param("uuid") String uuid);

    int changeGiftQty(@Param("giftId")Long giftId);

    List<Gift> getMyGiftList(@Param("memberId")Integer memberId);

    String getTypeName(@Param("merchantId")Long merchantId);

    List<MemberGift> getAllGiftByMemberId(@Param("memberId")Integer memberId);

    Member getMemberByToken(@Param("token")String token);

    List<Gift> selectMemberAllGift(@Param("memberId")Integer memberId);

    Gift getGiftById(@Param("id")Long giftId);

    int updateNewCurrency(@Param("member")Member member);

    int deleteGiftOfMember(@Param("memberId")Integer memberId, @Param("giftId")Long giftId);

    int updatePlatformCount(@Param("memberId")Integer memberId);

    int deleteGift(@Param("memberId")Integer memberId, @Param("giftId")Long giftId);

    int changeGiftQtyAdd(@Param("giftId")Long giftId);

    int updateNewCurrencyForMember(@Param("memberId")Integer memberId, @Param("price")BigDecimal price);

    int insertGiftLog(GiftPurchaseLogs gpl);

    List<GiftPurchaseLogs> getGiftLogs(@Param("memberId")Integer memberId, @Param("type")String type);

    Integer ifGetPlatFormGift(@Param("memberId")Integer memberId, @Param("type")String type);

    int deleteGiftByUuid(@Param("memberId")Integer memberId, @Param("uuid")String uuid);

    Member getMemberById(@Param("memberId")Integer memberId);

    Gift selectGiftByUuid(@Param("uuid")String uuid);

    Integer ifExistsMerchant(@Param("merchantId")Integer merchantId);

    Integer getMerchantIdByToken(@Param("token")String token);

    List<Gift> getWaitWriteOffList(@Param("merchantId")Integer merchantId);

    List<MemberGiftResult> getWaitWriteOffDetailList(@Param("giftId")Long giftId);

    List<Gift> getAlreadyWriteOff(@Param("merchantId")Integer merchantId);

    Integer getFinishWriteOffNum(@Param("merchantId")Integer merchantId, @Param("giftId")Long giftId);

    Integer getWaitWriteOffQty(@Param("giftId")Long giftId);

    int updateMyGift(MemberGift mg);

    int insertMemberCurrencyLog(MemberCurrencyRecord mcr);
}
