package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.service.RefundBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/7 0007
 * @time: 下午 17:24
 * @Description: 退款分润扣除金额
 */
@Service
public class RefundBenefitServiceImpl implements RefundBenefitService {

    @Autowired
    private FreezeMapper freezeMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private FactoryMapper factoryMapper;

    @Override
    public void refundBenefit(GoodsOrder goodsOrder) {
        if (goodsOrder.getDividedState() != 1){
            return;
        }
        //2.根据订单获取已冻结的记录
        List<Freeze> freezes = new LambdaQueryChainWrapper<>(freezeMapper)
                .eq(Freeze::getOrderId, goodsOrder.getId())
                .eq(Freeze::getState, 1).list();
        if (freezes == null && freezes.size() < 1){
            return;
        }
        freezes.forEach(freeze -> {
            int type = freeze.getType();
            switch (type) {
                //3.退款
                case 1:
                case 3:
                    //通证
                    //user
                    userBenefit(freeze);
                    break;
                case 2:
                case 6:
                    //新民币
                    memberCurrencyBenefit(freeze);
                    break;
                case 4:
                    //factory
                    factoryBenefit(freeze);
                    break;
                case 5:
                    //新民豆
                    memberBeansBenefit(freeze);
                    break;
                default:
                    break;
            }
            freeze.setUpdateTime(new Date());
            freeze.setState(3);
            freeze.updateById();
        });
        goodsOrder.setDividedState(3);
        goodsOrder.updateById();
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
            userAccount.setFreezeMoney(freezeMoney - money);
            userAccount.updateById();
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
        member.setFreezeCurrency(freezeCurrency.subtract(money));
        member.updateById();
    }

    /**
     * 新民豆
     *
     * @param freeze
     */
    public void memberBeansBenefit(Freeze freeze) {
        int memberId = freeze.getMemberId();
        BigDecimal money = freeze.getMoney();
        Member member = memberMapper.selectById(memberId);
        BigDecimal freezeBeans = member.getFreezeBeans();
        member.setFreezeBeans(freezeBeans.subtract(money));
        member.updateById();
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
        BigDecimal freezingAmount = factory.getFreezingAmount();
        factory.setFreezingAmount(freezingAmount.subtract(money));
        factory.updateById();
    }
}
