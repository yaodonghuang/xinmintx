package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.service.WechatPayService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/27 0027
 * @time: 上午 10:06
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class WechatPayServiceImplTest {

    @Autowired
    private WechatPayService wechatPayService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleShareMapper roleShareMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;
    @Autowired
    private PayConfig payConfig;

    @Test
    void unifiedorder() {
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setUserId(71);
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc("测试");
        wechatPayBo.setOrderAmt(1L);
        wechatPayBo.setOpenId("o8hDDjpMDpYQJrUx1PdDN83eiFdA");
        wechatPayBo.setRetUrl(payConfig.getGoodsNotifyUrl());
        wechatPayBo.setType(5);
        Map<String, Object> unifiedorder = wechatPayService.unifiedorder(wechatPayBo);
        System.out.println(JSON.toJSONString(unifiedorder));
    }

    @Test
    public void test() {
        MemberReferrer one = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getMemberId, 263)
                .eq(MemberReferrer::getStatus, 1)
                .one();
        if (one != null) {
            Member referrer = memberMapper.selectById(one.getReferrerId());
            if (referrer.getUserId() != null) {
                //推荐人为代理商角色
                //推荐分成
                shareMoneyByUserId(referrer.getUserId(), 1);
                //推荐人上级分成
                //获取推荐人上级
                User user = userMapper.selectById(referrer.getUserId());
                shareMoneyByUserId(user.getId(), 2);
            }
        }
    }

    /**
     * 根据用户id和分润类型进行分润
     *
     * @param userId
     * @param type
     */
    public void shareMoneyByUserId(Integer userId, Integer type) {
        User user = userMapper.selectById(userId);
        //获取推荐人用户角色
        Integer userRole = user.getUserRole();
        //分润规则
        QueryWrapper<RoleShare> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_role", userRole);
        queryWrapper.eq("recommend_role", 6);
        queryWrapper.eq("recommend_type", type);
        RoleShare roleShare = roleShareMapper.selectOne(queryWrapper);
        if (roleShare != null) {
            //获取分润金额
            Double money = roleShare.getMoney();
            //分润
            shareMoney(user, money);
        }
    }

    /**
     * 给用户账户分润
     *
     * @param user
     * @param money
     */
    public void shareMoney(User user, Double money) {
        //获取用户账户
        UserAccount userAccount = new LambdaQueryChainWrapper<>(userAccountMapper).eq(UserAccount::getUserId, user.getId()).one();
        if (userAccount != null) {
            userAccount.setMoney(userAccount.getMoney() + money);
        } else {
            userAccount = new UserAccount();
            userAccount.setMoney(money);
            userAccount.setUserId(user.getId());
            userAccountMapper.insert(userAccount);
        }
        //更新用户账户记录
        UserAccountRecord userAccountRecord = new UserAccountRecord();
        userAccountRecord.setUserId(user.getId());
        userAccountRecord.setUserAccountId(userAccount.getId());
        userAccountRecord.setMoneyChange(BigDecimal.valueOf(money));
        userAccountRecord.setDescription("银卡升级分润");
        userAccountRecord.setCreateTime(new Date());
        userAccountRecordMapper.insert(userAccountRecord);
    }
    @Test
    public void aaaa(){
        System.out.println(payConfig.getGoodsNotifyUrl());
    }
}