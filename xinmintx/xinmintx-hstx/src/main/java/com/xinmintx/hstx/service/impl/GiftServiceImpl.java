package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.GiftMapper;
import com.xinmintx.hstx.mapper.MemberGiftMapper;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/21 0021
 * @time: 下午 12:37
 * @Description:
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private MemberGiftMapper memberGiftMapper;

    @Override
    public ResultCode<MemberGift> getGift(Member member, Integer giftId) {
        ResultCode<MemberGift> resultCode = new ResultCode<>();
        Gift gift = giftMapper.selectById(giftId);
        if (gift == null) {
            return null;
        }
        if (gift.getPrice() == null) {
            gift.setPrice(BigDecimal.ZERO);
        }
        if (member.getMemberType() == null) {
            //普通会员,已过期会员不能领取
            resultCode.setMsg("会员类型不正确");
            return resultCode;
        }
        if (member.getMemberType() == 0) {
            //普通会员,已过期会员不能领取
            resultCode.setMsg("普通会员无法领取礼包");
            return resultCode;
        }
        if (member.getCardStatus() == null || member.getCardStatus() == 0){
            resultCode.setMsg("会员已过期");
            return resultCode;
        }
        if (member.getMemberType() == 1 || member.getMemberType() == 2) {
            ResultCode<Boolean> dealSilverCard = dealSilverCard(gift, member);
            Boolean bool = dealSilverCard.getData();
            if (!bool){
                resultCode.setMsg(dealSilverCard.getMsg());
                return resultCode;
            }
        }
        MemberGift memberGift = ifExistsGiftThisMember(member, giftId);
        if (memberGift != null) {
            //已经领取过礼包了
            resultCode.setData(memberGift);
            return resultCode;
        }
        //未领取过,领取礼包
        //修改礼包数量
        if (gift.getQuantity() < 1) {
            //礼包数量不够
            resultCode.setMsg("礼包数量不够");
            return resultCode;
        }
        gift.setQuantity(gift.getQuantity() - 1);
        gift.updateById();
        // 领取礼包之前增加礼包唯一码
        String uuid = UUID.randomUUID().toString().replace("-", "");
        memberGift = new MemberGift();
        memberGift.setMemberId(member.getId().longValue());
        memberGift.setGiftId(giftId.longValue());
        memberGift.setPrice(gift.getPrice());
        memberGift.setUuid(uuid);
        memberGift.insert();
        resultCode.setData(memberGift);
        return resultCode;
    }

    /**
     * 查询该用户是否领取过礼包
     *
     * @param member
     * @param giftId
     * @return
     */
    private MemberGift ifExistsGiftThisMember(Member member, Integer giftId) {
        return new LambdaQueryChainWrapper<>(memberGiftMapper)
                .eq(MemberGift::getMemberId, member.getId())
                .eq(MemberGift::getGiftId, giftId)
                .one();
    }

    /**
     * 判断用户能否领取礼包
     * @param gift
     * @param member
     * @return
     */
    private ResultCode<Boolean> dealSilverCard(Gift gift, Member member) {
        ResultCode<Boolean> resultCode = new ResultCode<>();
        resultCode.setData(false);
        // 查询用户下所有礼包类型,计算平台和商户礼包数量
        List<MemberGift> memberGifts = new LambdaQueryChainWrapper<>(memberGiftMapper)
                .eq(MemberGift::getMemberId, member.getId())
                .eq(MemberGift::getIfDelete, 0)
                .list();
        Set<Long> memberGiftIds = new HashSet<>();
        for (MemberGift memberGift : memberGifts) {
            memberGiftIds.add(memberGift.getGiftId());
        }
        List<Gift> giftList = null;
        if (memberGiftIds .size() > 0){
            giftList = new LambdaQueryChainWrapper<>(giftMapper)
                    .in(Gift::getId, memberGiftIds)
                    .list();
        }
        Integer pfQty = 0;
        Integer birthGftQty = 0;
        if (giftList != null && giftList.size() > 0) {
            for (Gift memberGift : giftList) {
                if ("platform".equalsIgnoreCase(memberGift.getGiftType())) {
                    // 计算出已经领取的平台礼包数量
                    pfQty++;
                }
                if ("birthGiftPackage".equalsIgnoreCase(memberGift.getGiftGroup())) {
                    // 计算出生日礼包可领取数量
                    birthGftQty++;
                }
            }
        }
        if ("platform".equalsIgnoreCase(gift.getGiftType())) {
            if (member.getMemberType() == 1) {
                resultCode.setMsg("E卡会员不能领取平台礼包!");
                return resultCode;
            }
            // 计算领取之后的总数量,判断是否超过了设置的数量
            pfQty = pfQty + 1;
            if (pfQty.compareTo(member.getPlatformCount()) > 0) {
                resultCode.setMsg("超过可以领取的平台礼包数量!已有" + (pfQty - 1) + "个");
                return resultCode;
            }
        }
        if ("birthGiftPackage".equalsIgnoreCase(gift.getGiftGroup())) {
            // 计算领取之后的总数量,判断是否超过了设置的数量
            birthGftQty = birthGftQty + 1;
            if (birthGftQty.compareTo(member.getBirthGiftCount()) > 0) {
                resultCode.setMsg("超过可以领取的生日礼包数量!已有" + (birthGftQty - 1) + "个");
                return resultCode;
            }
        }
        if ("merchant".equalsIgnoreCase(gift.getGiftType()) && !"birthGiftPackage".equalsIgnoreCase(gift.getGiftGroup())) {
            BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
            // 新民币大于礼包价格
            if (newCurrency.compareTo(gift.getPrice()) >= 0) {
                // 减掉新民币
                member.setNewCurrency(newCurrency.subtract(gift.getPrice()));
                // 更新新民币
                member.updateById();
                // 保存日志
                insertLogs(member, gift, "pay", member.getNewCurrency());
                resultCode.setData(true);
                return resultCode;
            } else {
                resultCode.setMsg("新民币余额不足,无法领取商户礼包");
                return resultCode;
            }
        } else if ("birthGiftPackage".equalsIgnoreCase(gift.getGiftGroup())) {
            // 保存日志
            gift.setPrice(BigDecimal.ZERO);
            insertLogs(member, gift, "pay", member.getNewCurrency());
            resultCode.setData(true);
            return resultCode;
        }
        resultCode.setData(true);
        return resultCode;
    }

    /**
     * 保存日志方法
     *
     * @param member
     * @param gift
     * @param type
     * @param balance
     */
    private static void insertLogs(Member member, Gift gift, String type,BigDecimal balance) {
        MemberCurrencyRecord record = new MemberCurrencyRecord();
        record.setMemberId(member.getId());
        record.setDescription("兑换礼包");
        GiftPurchaseLogs giftPurchaseLogs = new GiftPurchaseLogs();
        giftPurchaseLogs.setMemberId(Long.valueOf(member.getId()));
        if (gift != null) {
            giftPurchaseLogs.setGiftId(gift.getId());
            giftPurchaseLogs.setPrice(gift.getPrice());
            record.setCurrencyChange(gift.getPrice());
        } else {
            giftPurchaseLogs.setGiftId(null);
            giftPurchaseLogs.setPrice(BigDecimal.ZERO);
            record.setCurrencyChange(BigDecimal.ZERO);
        }
        giftPurchaseLogs.setBalance(balance);
        giftPurchaseLogs.setType(type);
        giftPurchaseLogs.insert();
        record.insert();
    }

}
