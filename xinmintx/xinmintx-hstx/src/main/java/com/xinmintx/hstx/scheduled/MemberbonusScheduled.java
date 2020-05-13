package com.xinmintx.hstx.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 会员奖金池分配定时器
 *
 * @author hyd
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class MemberbonusScheduled implements SchedulingConfigurer {
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
    private CronMapper cronMapper;
    @Resource
    private IMemberService memberService;

    /**
     * 奖金池分配奖金定时器
     *
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                () -> MemberbonusCount(),
//                triggerContext -> {
//                    Cron cron = cronMapper.selectById(1);
//                    if (cron == null || StringUtils.isEmpty(cron.getCron())) {
//                        // 默认每天12点
//                        cron.setCron("0 0 0 * * *");
//                    }
//                    log.info(cron.getCron());
//                    return new CronTrigger(cron.getCron()).nextExecutionTime(triggerContext);
//                }
//        );
    }

    /**
     * 奖金池计算分配
     */
    public void MemberbonusCount() {
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
        }
        log.info("================memberList info empty==================");
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

    // 获取奖金池结束触发时间
    private String getTiggerTime() {
        StringBuffer sb = new StringBuffer();
        QueryWrapper<MemberBonus> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("status", 1);
        queryWrapperOne.le("UNIX_TIMESTAMP(begin_date)", System.currentTimeMillis() / 1000);
        queryWrapperOne.ge("UNIX_TIMESTAMP(end_date)", System.currentTimeMillis() / 1000);
        memberBonus = memberBonusMapper.selectOne(queryWrapperOne);
        if (memberBonus != null) {
            log.info("MemberBonus event:" + memberBonus.toString());
            Date endDate = memberBonus.getEndDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            sb.append(cal.get(Calendar.SECOND)).append(" ").append(cal.get(Calendar.MINUTE)).append(" ")
                    .append(cal.get(Calendar.HOUR_OF_DAY)).append(" ").append(cal.get(Calendar.DAY_OF_MONTH))
                    .append(" ").append(cal.get(Calendar.MONTH) + 1).append(" ?");
        }
        return sb.toString();
    }
}
