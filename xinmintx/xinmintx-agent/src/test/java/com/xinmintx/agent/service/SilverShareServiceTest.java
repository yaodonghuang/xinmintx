package com.xinmintx.agent.service;

import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.mapper.UOrderMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/23 0023
 * @time: 下午 12:40
 * @Description: 提交新民金卡分润测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SilverShareServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private RoleShareService service;

    /**
     * 提交人上级为boss或者无
     * 预期结果:
     * 推荐人332获得直推50
     * boss获得10
     * 公司账户2获得334
     * 测试结果:通过
     */
    @Test
    public void silver1() {
        User user = userMapper.selectByPrimaryKey(332);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1937);
        service.shareProfit(user, CreateRole.SILVER_MEMBER, uOrder);
    }

    /**
     * 提交人上级为其他角色,非boss
     * 预期结果:
     * 推荐人396获得直推50
     * 推荐人上级350获得间推10
     * boss获得10
     * 公司账户2获得324
     * 测试结果:通过
     */
    @Test
    public void silver2() {
        User user = userMapper.selectByPrimaryKey(396);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1894);
        service.shareProfit(user, CreateRole.SILVER_MEMBER, uOrder);
    }
}
