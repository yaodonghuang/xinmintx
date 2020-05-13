package com.xinmintx.agent.service;

import com.xinmintx.agent.configuration.pay.PayConfig;
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
 * @Description: 提交代理分润测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AgentShareServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private RoleShareService service;

    /**
     * 提交人上级为boss或者无
     * 预期结果:
     * 推荐人332获得直推260
     * boss获得500
     * 公司账户2获得1040
     * 测试结果:通过
     */
    @Test
    public void agent1() {
        User user = userMapper.selectByPrimaryKey(332);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1944);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    /**
     * 提交人上级为其他角色,非boss
     * 预期结果:
     * 推荐人349获得直推260
     * 推荐人上级225获得间推200
     * boss获得500
     * 公司账户2获得840
     * 测试结果:通过
     */
    @Test
    public void agent2() {
        User user = userMapper.selectByPrimaryKey(349);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1945);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    /**
     * 被创建的代理为学员
     * 推荐人是其他人
     * 预期结果:
     * 推荐人350获得直推260
     * 推荐人上级351获得200
     * 讲师351获得90
     * boss获得500
     * 公司账户2获得750
     * 测试结果:通过
     */
    @Test
    public void agent3() {
        User user = userMapper.selectByPrimaryKey(350);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1945);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    /**
     * 被创建的代理为学员
     * 推荐人是为讲师
     * 预期结果:
     * 推荐人351获得直推260,获得讲师90,合计350
     * boss获得500
     * 公司账户2获得950
     * 测试结果:通过
     */
    @Test
    public void agent4() {
        User user = userMapper.selectByPrimaryKey(351);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1945);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    /**
     * 推荐是代理,这个代理属于合伙人团队
     * 预期结果:
     * 推荐人396获得直推260
     * 推荐人上级350获得间推200,获得团队奖励100,合计300
     * boss获得500
     * 公司账户2获得740
     * 测试结果:通过
     */
    @Test
    public void agent5() {
        User user = userMapper.selectByPrimaryKey(396);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1773);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    /**
     * TODO 推荐人是合伙人,是否像高级合伙人推荐合伙人一样获取团队奖励
     */
    @Test
    public void agent6() {
        User user = userMapper.selectByPrimaryKey(447);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1939);
        service.shareProfit(user, CreateRole.AGENT, uOrder);
    }

    @Autowired
    private PayConfig payConfig;

    @Test
    public void test() {
        System.out.println(payConfig.getAgent());
    }
}
