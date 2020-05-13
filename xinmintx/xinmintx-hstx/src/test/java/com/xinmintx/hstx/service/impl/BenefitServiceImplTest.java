package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.GoodsOrderDetailMapper;
import com.xinmintx.hstx.mapper.GoodsOrderMapper;
import com.xinmintx.hstx.mapper.MemberBonusMapper;
import com.xinmintx.hstx.mapper.RoleShareMapper;
import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;
import com.xinmintx.hstx.pojo.po.MemberBonus;
import com.xinmintx.hstx.pojo.po.RoleShare;
import com.xinmintx.hstx.service.BenefitService;
import com.xinmintx.hstx.service.ReturnGoodsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
class BenefitServiceImplTest {

    @Autowired
    private BenefitService benefitService;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private ReturnGoodsService returnGoodsService;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;
    @Autowired
    private RoleShareMapper roleShareMapper;
    @Autowired
    private MemberBonusMapper memberBonusMapper;

    @Test
    void benfit() {
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(839);
        benefitService.benfit(goodsOrder);
    }

    @Test
    void test() {
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(361);
        returnGoodsService.returnGoods(goodsOrderDetail, 1);
    }

    @Test
    void test2() {
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(361);
        returnGoodsService.returnGoods(goodsOrderDetail, -1);
    }
    @Test
    void test3() {
        //获取奖池金额
        RoleShare prizePool = roleShareMapper.selectById(67);
        if (prizePool != null) {
            Double money = prizePool.getMoney();
            BigDecimal bigDecimalMoney = BigDecimal.valueOf(money);
            Date date = new Date();
            MemberBonus memberBonus = new LambdaQueryChainWrapper<>(memberBonusMapper)
                    .le(MemberBonus::getBeginDate, date) //大于开始时间
                    .ge(MemberBonus::getEndDate, date)  //小于结束时间
                    .eq(MemberBonus::getStatus, 1)      //开启的
                    .one();
            if (memberBonus != null) {
                //获取原奖金池总金额
                BigDecimal totalAmount = memberBonus.getTotalAmount();
                if (totalAmount == null) {
                    totalAmount = BigDecimal.ZERO;
                }
                memberBonus.setTotalAmount(totalAmount.add(bigDecimalMoney));
                memberBonus.updateById();
            }
        }
    }
}