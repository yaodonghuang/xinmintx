package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.UOrderMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.ShareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/27 0027
 * @time: 下午 17:14
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareServiceImplTest {

    @Autowired
    private ShareService shareService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    /**
     * 创建角色实现分润逻辑
     *
     * @param user   提交人(提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人))
     * @param roleId 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @param uOrder 订单
     */
    @Test
    public void shareBenefit() {
        User user = userMapper.selectByPrimaryKey(414);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1919);
        shareService.shareBenefit(user, "1", uOrder);
    }
}
