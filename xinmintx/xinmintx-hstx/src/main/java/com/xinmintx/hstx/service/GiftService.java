package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberGift;
import com.xinmintx.hstx.pojo.vo.ResultCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/21 0021
 * @time: 下午 12:37
 * @Description:
 */
public interface GiftService {
    /**
     * 获取礼包
     * @param member
     * @param giftId
     * @return
     */
    ResultCode<MemberGift> getGift(Member member, Integer giftId);
}
