package com.xinmintx.hstx.scheduled;

import com.xinmintx.hstx.mapper.GiftMapper;
import com.xinmintx.hstx.mapper.MemberGiftMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberGift;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员礼包自动领取定时器
 */
@Slf4j
@Component
@Transactional
public class MemberGiftScheduled {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private MemberGiftMapper memberGiftMapper;

    //每天晚上11点59分59秒执行一次
//    @Scheduled(cron = "59 59 23 ? * *")
//    @Scheduled(fixedRate = 2000)
    public void memberGetGift() {
        // 查询所有普通用户,新民币
        Map<String, Object> map = new HashMap<>();
        map.put("member_type", 1);
        List<Member> memberList = memberMapper.selectByMap(map);
        if (memberList != null && memberList.size() > 0) {
            for (Member member : memberList) {
                autoGetGift(member);
            }
        }
    }

    // 自动领取礼包逻辑
    private void autoGetGift(Member member) {
        Integer integral = member.getIntegral() == null ? 194 : member.getIntegral();
        BigDecimal dcmIntegral = new BigDecimal(String.valueOf(integral));
        BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
        if (newCurrency.compareTo(dcmIntegral) >= 0) {// 新民币大于设置的积分
            BigDecimal last = newCurrency.divide(dcmIntegral, 0, BigDecimal.ROUND_HALF_UP);// 余数
            member.setNewCurrency(newCurrency.subtract(last.multiply(dcmIntegral)));// 减掉新民币
            // 更新会员信息,随机选取红包领取
            List<Integer> idList = giftMapper.getGiftIds(member.getId());
            if (idList != null && idList.size() > 0) {
                int size = 0;
                if (idList.size() <= last.intValue()) {
                    size = idList.size();
                } else {
                    size = last.intValue();
                }
                for (int i = 0; i < size; i++) {
                    MemberGift memberGift = new MemberGift();
                    Long aLong = Long.valueOf(member.getId());
                    memberGift.setMemberId(aLong);
                    Integer integer = idList.get(i);
                    Long aLong1 = Long.valueOf(integer);
                    memberGift.setGiftId(aLong1);
                    memberGiftMapper.insert(memberGift);
                }
            }
            memberMapper.updateById(member);
        }
    }
}
