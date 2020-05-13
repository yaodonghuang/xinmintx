package com.xinmintx.hstx.controller;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.RedEnvelopeActivities;
import com.xinmintx.hstx.pojo.po.RedEnvelopeRecord;
import com.xinmintx.hstx.pojo.po.RedEnvelopeType;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.RedPacketService;
import com.xinmintx.hstx.util.FieldUtils;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/redPacket")
public class RedPacketController extends BaseController {

    @Autowired
    private IMemberService IMemberService;

    @Autowired
    private RedPacketService redPacketService;

    /**
     * 领取红包
     * @param activityId 活动Id
     * @param redPacketId 红包Id
     * @return
     */
    @PostMapping("/receiveRedEnvelope/{activityId}/{redPacketId}")
    public ResultCode receiveRedEnvelope(@PathVariable("activityId") Integer activityId, @PathVariable("redPacketId") Integer redPacketId){
        Member member = IMemberService.findMemberByToken(token);
        return redPacketService.receiveRedEnvelope(activityId, redPacketId, member);
    }

    /**
     * 红包类型
     * @param activityId 活动id
     * @return
     */
    @PostMapping("/redEnvelopeType/{activityId}")
    public ResultCode redEnvelopeType(@PathVariable("activityId") Integer activityId){
        Member member = IMemberService.findMemberByToken(token);
        return redPacketService.queryRedType(activityId,member);
    }

    /**
     * 获取当前红包活动时间信息
     * @return RedEnvelopeActivities
     */
    @PostMapping("/activitiesOpen")
    public ResultCode activitiesOpen(@Ignore ResultCode resultCode){
        RedEnvelopeActivities redEnvelopeActivities = redPacketService.activitiesOpen();
        resultCode.setData(redEnvelopeActivities);
        return resultCode;
    }

    /**
     * 本期活动用户已领取的红包类型
     * @return
     */
    //@PostMapping("/receivedRedEnvelopeEype/{activityId}")
    public ResultCode receivedRedEnvelopeEype(@Ignore ResultCode resultCode,@PathVariable("activityId") Integer activityId){
        Member member = IMemberService.findMemberByToken(token);
        List<RedEnvelopeRecord> redEnvelopeRecords = redPacketService.receivedRedEnvelopeEype(activityId, member);
        resultCode.setData(redEnvelopeRecords);
        return resultCode;
    }


    /**
     * 本期活动推荐人数
     * @param resultCode
     * @param activityId
     * @return
     */
    @PostMapping("/recommendedNumberOfThisActivity/{activityId}")
    public ResultCode recommendedNumberOfThisActivity(@Ignore ResultCode resultCode,@PathVariable("activityId")Integer activityId){
        Member member = IMemberService.findMemberByToken(token);
        MemberVo memberByECard = redPacketService.getMemberByECard(member, activityId);
        Integer eCardCount = memberByECard.getECardCount();
        if(eCardCount == null){
            eCardCount = 0;
         }
        resultCode.setData(eCardCount);
        return resultCode;
    }

    /***
     * 本期活动总金额
     * @param resultCode
     * @param activityId
     * @return
     */
    @PostMapping("/receiveTotalAmountRedEnvelope/{activityId}")
    public ResultCode ReceiveTotalAmountRedEnvelope(@Ignore ResultCode resultCode,@PathVariable("activityId")Integer activityId){
        Member member = IMemberService.findMemberByToken(token);
        List<RedEnvelopeRecord> redEnvelopeRecords = redPacketService.receivedRedEnvelopeEype(activityId, member);
        BigDecimal money = new BigDecimal(0);
        for(RedEnvelopeRecord list:redEnvelopeRecords){
            RedEnvelopeType redEnvelopeType = redPacketService.selectByRedTypeId(list.getRedTypeId());
            money = money.add(redEnvelopeType.getBonusAmount());
        }
        resultCode.setData(money);
        return resultCode;
    }

    /**
     * 获取总金额和总累计推荐人数
     * @return
     */
    @PostMapping("/getSum")
    public ResultCode getSum(@Ignore ResultCode resultCode){
        Member member = IMemberService.findMemberByToken(token);
        MemberVo one = FieldUtils.fieldTrans(member, MemberVo.class);
        resultCode.setData(IMemberService.getSum(one));
        return  resultCode;
    }
}
