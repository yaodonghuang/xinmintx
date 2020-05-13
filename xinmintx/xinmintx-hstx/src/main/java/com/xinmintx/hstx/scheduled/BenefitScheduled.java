package com.xinmintx.hstx.scheduled;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/7 0007
 * @time: 下午 13:39
 * @Description: 分润定时
 */
@Slf4j
@Component
@Transactional
public class BenefitScheduled {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private FreezeMapper freezeMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private FactoryMapper factoryMapper;

    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "1/20 * * * * ?")
    public void goodsBenefit() {
        //1.筛选出订单为已经确认收获的并且已冻结的订单
        List<GoodsOrder> goodsOrders = new LambdaQueryChainWrapper<>(goodsOrderMapper)
                .eq(GoodsOrder::getOrderState, 5)
                .eq(GoodsOrder::getDividedState, 1)
                .list();
        //1.1订单列表中筛选出已经确认收获8天的记录
        if (goodsOrders.size() < 1){
            return;
        }
        goodsOrders.forEach(goodsOrder -> {
            Date updateTime = goodsOrder.getUpdateTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(updateTime);
            //8天以后的日期
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 8);
            Date time = calendar.getTime();
            int i = DateUtil.compareDateWithNow(time);
            if (i < 1) {
//            if (true) {
                //确认收获8天及8天以上的数据
                //2.根据订单获取已冻结的记录
                List<Freeze> freezes = new LambdaQueryChainWrapper<>(freezeMapper)
                        .eq(Freeze::getOrderId, goodsOrder.getId())
                        .eq(Freeze::getState, 1).list();
                if (freezes.size() < 1){
                    return;
                }
                freezes.forEach(freeze -> {
                    int type = freeze.getType();
                    switch (type) {
                        //3.分润
                        case 1:
                        case 3:
                            //通证
                            //user
                            userBenefit(freeze);
                            break;
                        case 2:
                            //新民币
                            memberCurrencyBenefit(freeze);
                        case 6:
                            //矩阵新民币
                            memberDATACurrencyBenefit(freeze);
                            break;
                        case 4:
                            //factory
                            factoryBenefit(freeze);
                            break;
                        case 5:
                            //消费获取新民币
                            memberNewCurrencyBenefit(freeze);
                            break;
                        default:
                            break;
                    }
                    freeze.setUpdateTime(new Date());
                    freeze.setState(2);
                    freeze.updateById();
                });
                goodsOrder.setDividedState(2);
                goodsOrder.updateById();
            }
        });
    }

    /**
     * user分润
     *
     * @param freeze
     */
    public void userBenefit(Freeze freeze) {
        int userId = freeze.getMemberId();
        double money = freeze.getMoney().doubleValue();
        //获取用户账户表
        UserAccount userAccount = new LambdaQueryChainWrapper<>(userAccountMapper).eq(UserAccount::getUserId, userId).one();
        if (userAccount != null) {
            //现有冻结金额
            double freezeMoney = userAccount.getFreezeMoney();
            double accountMoney = userAccount.getMoney() == null ? 0 : userAccount.getMoney();
            userAccount.setFreezeMoney(freezeMoney - money);
            userAccount.setMoney(accountMoney + money);
            userAccount.updateById();
            ///记录账户变动
            UserAccountRecord record = new UserAccountRecord();
            record.setUserId(userId);
            record.setUserAccountId(userAccount.getId());
            record.setMoneyChange(BigDecimal.valueOf(money));
            record.setDescription("商品购买分润");
            record.setCreateTime(new Date());
            record.insert();
        }

    }

    /**
     * 新民币
     *
     * @param freeze
     */
    public void memberCurrencyBenefit(Freeze freeze) {
        int memberId = freeze.getMemberId();
        BigDecimal money = freeze.getMoney();
        Member member = memberMapper.selectById(memberId);
        BigDecimal freezeCurrency = member.getFreezeCurrency();
        BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
        member.setFreezeCurrency(freezeCurrency.subtract(money));
        member.setNewCurrency(newCurrency.add(money));
        member.updateById();
        MemberCurrencyRecord record = new MemberCurrencyRecord();
        record.setMemberId(member.getId());
        record.setCurrencyChange(money);
        record.setDescription("商品购买分润");
        record.setCreateTime(new Date());
        record.insert();
    }
    /**
     * 新民币
     *
     * @param freeze
     */
    public void memberDATACurrencyBenefit(Freeze freeze) {
        int memberId = freeze.getMemberId();
        BigDecimal money = freeze.getMoney();
        Member member = memberMapper.selectById(memberId);
        BigDecimal freezeCurrency = member.getFreezeCurrency();
        BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
        member.setFreezeCurrency(freezeCurrency.subtract(money));
        member.setNewCurrency(newCurrency.add(money));
        member.updateById();
        MemberCurrencyRecord record = new MemberCurrencyRecord();
        record.setMemberId(member.getId());
        record.setCurrencyChange(money);
        record.setDescription("商品购买分润");
        record.setCreateTime(new Date());
        record.insert();
        MemberDataBonusRecord bonusRecord = new MemberDataBonusRecord();
        bonusRecord.setMemberId(memberId);
        bonusRecord.setBonusChange(money);
        bonusRecord.setDescription("商品购买分润");
        bonusRecord.setCreateTime(new Date());
        bonusRecord.insert();
    }

    /**
     * 消费获取新民币
     *
     * @param freeze
     */
    public void memberNewCurrencyBenefit(Freeze freeze) {
        int memberId = freeze.getMemberId();
        BigDecimal money = freeze.getMoney();
        Member member = memberMapper.selectById(memberId);
        BigDecimal freezeCurrency = member.getFreezeCurrency();
        BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
        member.setFreezeCurrency(freezeCurrency.subtract(money));
        member.setNewCurrency(newCurrency.add(money));
        member.updateById();
        MemberCurrencyRecord record = new MemberCurrencyRecord();
        record.setMemberId(member.getId());
        record.setCurrencyChange(money);
        record.setDescription("消费获取新民币");
        record.setCreateTime(new Date());
    }

    /**
     * factory分润
     *
     * @param freeze
     */
    public void factoryBenefit(Freeze freeze) {
        int factoryId = freeze.getMemberId();
        BigDecimal money = freeze.getMoney();
        Factory factory = factoryMapper.selectById(factoryId);
        BigDecimal cashAmount = factory.getCashAmount() == null ? BigDecimal.ZERO : factory.getCashAmount();
        BigDecimal freezingAmount = factory.getFreezingAmount();
        factory.setFreezingAmount(freezingAmount.subtract(money));
        factory.setCashAmount(cashAmount.add(money));
        factory.updateById();
        FactoryAmountRecord record = new FactoryAmountRecord();
        record.setFactoryId(factoryId);
        record.setAmountChange(money);
        record.setDescription("收入+" + money);
        record.setCreateTime(new Date());
        record.insert();
    }
}
