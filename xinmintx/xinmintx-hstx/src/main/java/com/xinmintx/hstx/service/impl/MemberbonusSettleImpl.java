package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberAmountLog;
import com.xinmintx.hstx.pojo.po.MemberBonus;
import com.xinmintx.hstx.pojo.po.MemberBonusInfo;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.MemberbonusSettleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author hyd
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberbonusSettleImpl implements MemberbonusSettleService {
    private static List<MemberBonusInfo> memberBonusInfoList;
    private static MemberBonus memberBonus;
    @Resource
    private MemberBonusInfoMapper memberBonusInfoMapper;
    @Resource
    private MemberBonusMapper memberBonusMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberAmountLogMapper memberAmountLogMapper;
    @Resource
    private IMemberService memberService;

    /**
     * 奖金池结算
     */
    @Override
    public void bonusSettle() {
        List<MemberVo> memberList = initData();
        if (memberList != null && memberList.size() > 0) {
            log.info("memberQty：" + memberList.size());
            if (System.currentTimeMillis() >= memberBonus.getEndDate().getTime()) {
                for (int i = 0; i < memberList.size(); i++) {
                    // 只分配20人
                    if (i > 20) {
                        break;
                    }
                    MemberVo memberVo = memberList.get(i);
                    log.info("memberInfo:" + memberVo.toString());
                    dealRank(Integer.valueOf(memberVo.getRanking()), memberVo);
                }
                // 更新奖金池活动结束,奖金清零
                memberBonus.setStatus(0);
                memberBonus.setTotalAmount(BigDecimal.ZERO);
                memberBonusMapper.updateById(memberBonus);
            } else {
                log.info("memberBonus not finished,endDate:" + memberBonus.getEndDate(), ",id:" + memberBonus.getId());
            }
        } else {
            log.info("================memberList info empty==================");
        }
    }

    // 根据会员等级计算区间及费用
    private void dealRank(Integer rank, Member mb) {
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
                        if (oneMoney.compareTo(BigDecimal.ZERO) == 1) {
                            // 给用户分钱
                            sendMoneyToMember(mb, oneMoney);
                        }
                    }
                } else {
                    continue;
                }
            }
        }
    }

    // 给用户分钱
    private void sendMoneyToMember(Member mb, BigDecimal oneMoney) {
        // 大于零，给用户分钱
        BigDecimal cashAmount = mb.getCashAmount() == null ? BigDecimal.ZERO : mb.getCashAmount();
        mb.setCashAmount(cashAmount.add(oneMoney));
        memberMapper.updateById(mb);
        // 保存日志
        MemberAmountLog memberAmountLog = new MemberAmountLog();
        memberAmountLog.setMemberId(Long.valueOf(mb.getId()));
        memberAmountLog.setType("2");
        memberAmountLog.setPrice(oneMoney);
        memberAmountLog.setBalance(mb.getCashAmount());
        memberAmountLog.setRemark("奖金池分配用户金额");
        memberAmountLog.setSource(8);
        memberAmountLogMapper.insert(memberAmountLog);
    }

    // 获取会员排名信息
    private List<MemberVo> initData() {
        QueryWrapper<MemberBonus> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("status", 1);
        memberBonus = memberBonusMapper.selectOne(queryWrapperOne);
        if (memberBonus == null) {
            log.info("================memberBonus empty==================");
            return null;
        }
        log.info("MemberBonus event:" + memberBonus.toString());
        if (memberBonusInfoList == null || memberBonusInfoList.size() == 0) {
            QueryWrapper<MemberBonusInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.le("id", 4);
            memberBonusInfoList = memberBonusInfoMapper.selectList(queryWrapper);
        }
        List<MemberVo> memberList = memberService.getMembersByECard(memberBonus.getBeginDate(), memberBonus.getEndDate());
        return memberList;
    }
}
