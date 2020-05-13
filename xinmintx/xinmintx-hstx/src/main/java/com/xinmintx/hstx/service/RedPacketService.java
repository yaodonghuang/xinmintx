package com.xinmintx.hstx.service;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.RedEnvelopeActivities;
import com.xinmintx.hstx.pojo.po.RedEnvelopeRecord;
import com.xinmintx.hstx.pojo.po.RedEnvelopeType;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;

import java.util.List;


public interface RedPacketService {
    ResultCode receiveRedEnvelope(Integer activityId, Integer redPacketId, Member member);

    RedEnvelopeActivities activitiesOpen();

    List<RedEnvelopeRecord> receivedRedEnvelopeEype(Integer activityId, Member member);

    ResultCode queryRedType(Integer activityId, Member member);

    MemberVo getMemberByECard(Member member, Integer activityId);

    RedEnvelopeType selectByRedTypeId(Integer redTypeId);


}
