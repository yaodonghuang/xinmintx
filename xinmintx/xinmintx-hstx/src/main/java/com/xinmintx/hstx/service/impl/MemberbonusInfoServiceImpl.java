package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.MemberBonusInfoMapper;
import com.xinmintx.hstx.mapper.MemberBonusMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBonus;
import com.xinmintx.hstx.pojo.po.MemberBonusInfo;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.MemberbonusInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 奖池信息
 *
 * @author Administrator
 */
@Service
@Slf4j
public class MemberbonusInfoServiceImpl implements MemberbonusInfoService {
    @Resource
    private MemberBonusMapper memberBonusMapper;
    @Resource
    private IMemberService memberService;
    @Resource
    private MemberBonusInfoMapper memberBonusInfoMapper;

    /**
     * 获取奖池名单列表
     *
     * @return
     */
    @Override
    public ResultCode bonusList(Member member) {
        ResultCode rc = new ResultCode();
        QueryWrapper<MemberBonus> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("status", 1);
        queryWrapperOne.le("UNIX_TIMESTAMP(begin_date)", System.currentTimeMillis() / 1000);
        queryWrapperOne.ge("UNIX_TIMESTAMP(end_date)", System.currentTimeMillis() / 1000);
        MemberBonus memberBonus = memberBonusMapper.selectOne(queryWrapperOne);
        if (memberBonus == null) {
            log.info("================memberBonus empty==================");
            rc.setCode(500);
            rc.setMsg("查询不到奖金池信息");
            return rc;
        }
        log.info("MemberBonus event:" + memberBonus.toString());
        QueryWrapper<MemberBonusInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("id", 4);
        List<MemberBonusInfo> memberBonusInfoList = memberBonusInfoMapper.selectList(queryWrapper);
        List<MemberVo> memberList = memberService.getMembersByECard(memberBonus.getBeginDate(), memberBonus.getEndDate());
        List<MemberVo> newList = new ArrayList<>();
        if (memberList != null && memberList.size() > 0) {
            if (memberList.size() > 50) {
                newList = memberList.subList(0, 50);
            } else {
                newList.addAll(memberList);
            }
            newList.forEach(mb -> {
                dealRank(memberBonus, memberBonusInfoList, Integer.valueOf(mb.getRanking()), mb);
            });
            rc.setCode(200);
            Map<String, Object> map = new HashMap<>(2);
            MemberVo rankMember = memberService.getMemberByECard(member, memberBonus.getBeginDate(), memberBonus.getEndDate());
            map.put("ranking", rankMember.getRanking());
            map.put("totalAmount", memberBonus.getTotalAmount());
            map.put("newList", newList);
            rc.setData(map);
        }
        return rc;
    }

    /**
     * 获取结束时间
     *
     * @return
     */
    @Override
    public ResultCode getEndTime() {
        ResultCode rc = new ResultCode();
        QueryWrapper<MemberBonus> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("status", 1);
        queryWrapperOne.le("UNIX_TIMESTAMP(begin_date)", System.currentTimeMillis() / 1000);
        queryWrapperOne.ge("UNIX_TIMESTAMP(end_date)", System.currentTimeMillis() / 1000);
        MemberBonus memberBonus = memberBonusMapper.selectOne(queryWrapperOne);
        if (memberBonus == null) {
            log.info("================memberBonus empty==================");
            rc.setCode(500);
            rc.setMsg("查询不到奖金池信息");
            return rc;
        }
        log.info("MemberBonus event:" + memberBonus.toString());
        Map<String, String> timeMap = new HashMap<>(2);
        timeMap.put("now", String.valueOf(System.currentTimeMillis()));
        timeMap.put("endTime", String.valueOf(memberBonus.getEndDate().getTime()));
        rc.setData(timeMap);
        return rc;
    }

    /**
     * 查询上期信息
     *
     * @return
     */
    @Override
    public ResultCode previousList(Member member) {
        ResultCode rc = new ResultCode();
        QueryWrapper<MemberBonus> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("status", 1);
        queryWrapperOne.le("UNIX_TIMESTAMP(begin_date)", System.currentTimeMillis() / 1000);
        queryWrapperOne.ge("UNIX_TIMESTAMP(end_date)", System.currentTimeMillis() / 1000);
        MemberBonus memberBonus = memberBonusMapper.selectOne(queryWrapperOne);
        Date endDate = new Date();
        if (memberBonus != null) {
            endDate = memberBonus.getBeginDate();
        } else {
            endDate = new Date();
        }
        QueryWrapper<MemberBonus> previous = new QueryWrapper<>();
        previous.eq("status", 0);
        previous.le("UNIX_TIMESTAMP(end_date)", endDate.getTime() / 1000);
        previous.last("order by create_time desc limit 1");
        MemberBonus previousMemberBonus = memberBonusMapper.selectOne(previous);
        if (previousMemberBonus == null) {
            log.info("================previousMemberBonus empty==================");
            rc.setCode(500);
            rc.setMsg("查询不到奖金池信息");
            return rc;
        }
        log.info("previousMemberBonus event:" + previousMemberBonus.toString());
        QueryWrapper<MemberBonusInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("id", 4);
        List<MemberBonusInfo> memberBonusInfoList = memberBonusInfoMapper.selectList(queryWrapper);
        List<MemberVo> memberList = memberService.getMembersByECard(previousMemberBonus.getBeginDate(), previousMemberBonus.getEndDate());
        List<MemberVo> newList = new ArrayList<>();
        if (memberList != null && memberList.size() > 0) {
            if (memberList.size() > 50) {
                newList = memberList.subList(0, 50);
            } else {
                newList.addAll(memberList);
            }
            newList.forEach(mb -> {
                dealRank(previousMemberBonus, memberBonusInfoList, Integer.valueOf(mb.getRanking()), mb);
            });
            rc.setCode(200);
            Map<String, Object> map = new HashMap<>(2);
            MemberVo rankMember = memberService.getMemberByECard(member, previousMemberBonus.getBeginDate(), previousMemberBonus.getEndDate());
            map.put("ranking", rankMember.getRanking());
            map.put("totalAmount", previousMemberBonus.getTotalAmount());
            map.put("newList", newList);
            rc.setData(map);
        }
        return rc;
    }

    // 根据会员等级计算区间及费用
    private void dealRank(MemberBonus memberBonus, List<MemberBonusInfo> memberBonusInfoList, Integer rank, MemberVo mb) {
        if (memberBonusInfoList != null && memberBonusInfoList.size() > 0) {
            for (MemberBonusInfo memberBonusInfo : memberBonusInfoList) {
                if (rank.compareTo(memberBonusInfo.getBeginRank()) > -1
                        && rank.compareTo(memberBonusInfo.getEndRank()) < 1) {
                    // 满足区间，计算费用
                    BigDecimal getMoney = memberBonus.getTotalAmount().multiply(memberBonusInfo.getPer());
                    // 计算出平分人数
                    Integer totalCount = memberBonusInfo.getEndRank().intValue() - memberBonusInfo.getBeginRank().intValue() + 1;
                    if (totalCount.intValue() > 0) {
                        BigDecimal oneMoney = getMoney.divide(new BigDecimal(totalCount), 2, BigDecimal.ROUND_HALF_DOWN);
                        mb.setEarnings(oneMoney);
                    }
                } else {
                    continue;
                }
            }
        }
    }
}
