package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import com.xinmintx.hstx.pojo.vo.MemberCardOrderVo;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMemberService {

    @Deprecated
    Map upgradeSilverByUserid(Member member, Integer memberId);

    Member findMemberByToken(String token);

    MemberVo getMemberByToken(String token);

    void updateMember(Member member);

    Member selectMemberByOpenid(String openid);

    ResultCode getNewCurrencyByToken(String token);

    double getDataBonusRecord(Integer memberId);

    Member insertOrSelect(Member member, Integer referrerId);

    ResultCode distribution(Member member, String code);

    ResultCode loginOut(Member member);

    ResultCode saveMemberCardInfo(MemberCardInfo memberCardInfo, Member member);

    MemberCardOrderVo memberCardRenew(Member member);

    ResultCode<MemberCardOrderVo> makeMemberCard(MemberCardInfo memberCardInfo, Member member);

    ResultCode<MemberCardOrderVo> reissueMemberCard(Member member);

    ResultCode<MemberCardOrderVo> getMemberCardOrder(Integer id);

    ResultCode createMemberCardOrder(MemberCardOrderVo memberCardOrderVo, Member member);

    ResultCode getMemberAmountLog(Member member);

    ResultCode getMemberCurrencyRecord(Member member);

    ResultCode getMemberCurrencyRecordInOrOut(Member member,String date,Integer flag);

    ResultCode updatePayPassword (Member member,String code,String payPassword,String confirmPayPassword);

    MemberVo getMemberByECard(Member member, Date startTime, Date endTime);

    List<MemberVo> getMembersByECard(Date startTime, Date endTime);

    MemberVo getSum(MemberVo one);
}
