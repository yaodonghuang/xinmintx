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
 * @Description: 提交合伙人分润测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PartnerShareServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private RoleShareService service;

    /**
     * 高级合伙人提交合伙人
     * 预期结果:
     * 推荐人225获得直推2600,获得团队奖励1000,合计3600
     * boss获得1800
     * 公司账户2获得4400
     * 测试结果:通过
     */
    @Test
    public void partner1() {
        User user = userMapper.selectByPrimaryKey(225);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1179);
        service.shareProfit(user, CreateRole.PARTNER, uOrder);
    }

    /**
     * 合伙人提交合伙人
     * 提交者上级是高级合伙人
     * 高级合伙人间推合伙人
     * 预期结果:
     * 推荐人349获得直推2600
     * 推荐人上级225获得间推1000,获得团队奖励1000,合计2000
     * boss获得1800
     * 公司账户2获得3400
     * 测试结果:通过
     */
    @Test
    public void partner2() {
        User user = userMapper.selectByPrimaryKey(349);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1926);
        service.shareProfit(user, CreateRole.PARTNER, uOrder);
    }

    /**
     * 合伙人提交合伙人
     * 提交者属于高级合伙人团队
     * 合伙人间推合伙人,同属于一个团队
     * 预期结果:
     * 推荐人443获得直推2600
     * 推荐人上级349获得间推1000
     * 高级合伙人225获得团队奖励1000
     * boss获得1800
     * 公司账户2获得3400
     * 测试结果:通过
     */
    @Test
    public void partner3() {
        User user = userMapper.selectByPrimaryKey(443);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1935);
        service.shareProfit(user, CreateRole.PARTNER, uOrder);
    }

    /**
     * 合伙人提交合伙人
     * 提交者上级是boss
     * boss间推合伙人
     * 预期结果:
     * 推荐人351获得直推2600
     * boss获得1800
     * 公司账户2获得5400
     * 测试结果:通过
     */
    @Test
    public void partner4() {
        User user = userMapper.selectByPrimaryKey(351);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1941);
        service.shareProfit(user, CreateRole.PARTNER, uOrder);
    }

    /**
     * 合伙人提交合伙人
     * 合伙人间推合伙人
     * 预期结果:
     * 推荐人447获得直推2600
     * 推荐人上级351获得间推1000
     * boss获得1800
     * 公司账户2获得4400
     * 测试结果:通过
     */
    @Test
    public void partner5() {
        User user = userMapper.selectByPrimaryKey(447);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1939);
        service.shareProfit(user, CreateRole.PARTNER, uOrder);
    }
}
