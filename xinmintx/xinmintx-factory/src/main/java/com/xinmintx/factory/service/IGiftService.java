package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.GiftMapper;
import com.xinmintx.factory.model.Gift;
import com.xinmintx.factory.model.Member;

import java.math.BigDecimal;
import java.util.List;

/**
 * 礼包service
 */
public interface IGiftService {
    // 根据礼包类型获取礼包列表
    List<Gift> getPlatformList(String giftType, Long sourceId, String giftGroup, String token);

    // 根据token获取MemberId
    Integer getMemberIdByToken(String token);

    // 根据会员id和礼包id获取绑定会员礼包
    int getGift(Integer memberId, Long giftId, BigDecimal price, String uuid);

    // 查询这个用户下是否已经领取过该礼包
    Integer ifExistsGiftThisMember(Integer memberId, Long giftId);

    // 变更礼包数量
    int changeGiftQty(Long giftId);

    // 获取我的礼包
    List<Gift> getMyGiftList(Integer memberId);

    Member getMemberByToken(String token);

    // 获取礼包
    ResultCode getGift(String token, Long giftId);

    // 删除礼包
    ResultCode deleteGift(String token, Long giftId);

    // 礼包退款
    ResultCode refundGift(String token, Long giftId);

    // 礼包日志
    ResultCode giftLogs(String token, String type);

    // 查询是否领取过平台礼包
    ResultCode ifGetPlatFormGift(String token, String type);

    // 商户核销接口
    ResultCode writeOff(Integer memberId, String uuid, String token);

    // 商户待核销列表
    ResultCode waitWriteOff(String token);

    // 商户待核销详情列表
    ResultCode waitWriteOffDetail(Long giftId);

    // 商户已核销列表
    ResultCode alreadyWriteOff(String token);

    // 新民比日志
    ResultCode saveLog(Integer memberId, Long giftId, BigDecimal price, String type,
                       BigDecimal balance, Integer merchantId);
}
