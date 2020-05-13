package com.xinmintx.hstx.service.impl;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.exception.BaseException;
import com.xinmintx.hstx.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.hstx.mapper.RedEnvelopeActivitiesMapper;
import com.xinmintx.hstx.mapper.RedEnvelopeRecordMapper;
import com.xinmintx.hstx.mapper.RedEnvelopeTypeMapper;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.RedEnvelopeTypeVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.RedPacketService;
import com.xinmintx.hstx.util.DateUtils;
import com.xinmintx.hstx.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    private RedEnvelopeTypeMapper redEnvelopeTypeMapper;

    @Autowired
    private com.xinmintx.hstx.service.IMemberService IMemberService;
    @Autowired
    private RedEnvelopeRecordMapper redEnvelopeRecordMapper;

    @Autowired
    private RedEnvelopeActivitiesMapper redEnvelopeActivitiesMapper;


    /**
     * 领取红包
     * @param activityId
     * @param redPacketId
     * @param member
     * @return
     */
    @Override
    @Transactional
    public ResultCode receiveRedEnvelope(Integer activityId, Integer redPacketId, Member member) {
        ResultCode resultCode = new ResultCode();
        RedEnvelopeActivities redEnvelopeActivities = redEnvelopeActivitiesMapper.selectById(activityId);
        //活动是否在进行当中
        if(redEnvelopeActivities == null || redEnvelopeActivities.getStart() != 1 ){
            resultCode.setCode(500);
            resultCode.setMsg("活动已结束");
            return resultCode;
        }
        //用户是否可以领取此类红包
        MemberVo memberByECard = IMemberService.getMemberByECard(member, redEnvelopeActivities.getRedStartTime(), redEnvelopeActivities.getRedEndTime());
        RedEnvelopeType redEnvelopeType1 = redEnvelopeTypeMapper.selectById(redPacketId);
        if(memberByECard.getECardCount() < redEnvelopeType1.getNumPeople()){
            resultCode.setCode(500);
            resultCode.setMsg("不能领取此红包");
            return resultCode;
        }
        //验证用户是否在本次活动领取过相同的红包
        RedEnvelopeRecord one = new LambdaQueryChainWrapper<>(redEnvelopeRecordMapper)
                .eq(RedEnvelopeRecord::getRedActivitiesId, activityId)
                .eq(RedEnvelopeRecord::getMemberId, member.getId())
                .eq(RedEnvelopeRecord::getRedTypeId, redPacketId).one();
        if(one == null){
            RedEnvelopeRecord redEnvelopeRecord = new RedEnvelopeRecord();
            redEnvelopeRecord.setRedActivitiesId(activityId);
            redEnvelopeRecord.setRedTypeId(redPacketId);
            redEnvelopeRecord.setMemberId(member.getId());
            //记录用户领取红包
            boolean insert = redEnvelopeRecord.insert();
            //同步余额
            if(insert){
                RedEnvelopeType redEnvelopeType = redEnvelopeTypeMapper.selectById(redPacketId);
                BigDecimal bonusAmount = redEnvelopeType.getBonusAmount();
                BigDecimal add = member.getCashAmount().add(bonusAmount);
                member.setCashAmount(add);
                //余额增加
                member.updateById();
                //记录余额变动情况
                MemberAmountLog memberAmountLog = new MemberAmountLog();
                memberAmountLog.setMemberId(member.getId().longValue());
                memberAmountLog.setCreateTime(new Date());
                memberAmountLog.setType("2");
                memberAmountLog.setPrice(bonusAmount);
                memberAmountLog.setBalance(add);
                memberAmountLog.setRemark("红包活动");
                memberAmountLog.setSource(9);
                memberAmountLog.insert();
                return resultCode;
            }
            resultCode.setCode(500);
            resultCode.setMsg("领取失败");
            return resultCode;
        }
        resultCode.setMsg("本次活动当前红包已领取");
        return resultCode;
    }



    /**
     * 活动类型
     * 获取红包活动时间信息 (仅查询当前状态进行中的，活动结束日期还未过期的一条红包活动时间信息
     * @return RedEnvelopeActivities
     */
    @Override
    public RedEnvelopeActivities activitiesOpen() {
        RedEnvelopeActivities redEnvelopeActivities = new LambdaQueryChainWrapper<>(redEnvelopeActivitiesMapper)
                .eq(RedEnvelopeActivities::getStart, 1)
                .ge(RedEnvelopeActivities::getRedEndTime, DateUtils.getNowDate().getTime()/1000)
                .one();
        if ( null == redEnvelopeActivities){
            throw new BaseException(ErrorCodeConStants.OBJECT_ALREADY_NONENTITY_1);
        }
        return redEnvelopeActivities;
    }

    /**
     * 查询红包类型
     * @return
     */
    @Override
    public ResultCode queryRedType(Integer activityId, Member member) {
        ResultCode resultCode = new ResultCode();
        RedEnvelopeType redEnvelopeType = new RedEnvelopeType();
        List<RedEnvelopeType> redEnvelopeTypes = redEnvelopeType.selectAll();
        List<RedEnvelopeTypeVo> redEnvelopeTypeVos = ListUtils.listTrans(redEnvelopeTypes, RedEnvelopeTypeVo.class);
        List<RedEnvelopeRecord> redEnvelopeRecords = receivedRedEnvelopeEype(activityId, member);
        //红包是否领取过
        for (RedEnvelopeRecord list : redEnvelopeRecords){
            for (RedEnvelopeTypeVo listType : redEnvelopeTypeVos){
                if(list.getRedTypeId()==listType.getId()){
                    listType.setRedPackStrat(1);
                }
            }
        }
        resultCode.setData(redEnvelopeTypeVos);
        return resultCode;
    }

    /**
     * 本期活动推荐人数
     * @param member
     * @param activityId
     * @return
     */
    @Override
    public MemberVo getMemberByECard(Member member, Integer activityId) {
        RedEnvelopeActivities redEnvelopeActivities = redEnvelopeActivitiesMapper.selectById(activityId);
        MemberVo memberByECard = IMemberService.getMemberByECard(member, redEnvelopeActivities.getRedStartTime(), redEnvelopeActivities.getRedEndTime());
        return memberByECard;
    }

    @Override
    public RedEnvelopeType selectByRedTypeId(Integer redTypeId) {
        return redEnvelopeTypeMapper.selectById(redTypeId);
    }

    /**
     * 本期活动用户已领取的红包类型
     * @param activityId
     * @param member
     * @return
     */
    @Override
    public List<RedEnvelopeRecord> receivedRedEnvelopeEype(Integer activityId, Member member) {
        List<RedEnvelopeRecord> list = new LambdaQueryChainWrapper<>(redEnvelopeRecordMapper)
                .eq(RedEnvelopeRecord::getRedActivitiesId, activityId)
                .eq(RedEnvelopeRecord::getMemberId, member.getId())
                .list();
        return list;
    }
}
