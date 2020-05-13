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
 * @Description: 提交商户分润测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MerchantShareServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private RoleShareService service;

    /**
     * 提交人非员工
     * 提交人上级为boss或者无
     * 预期结果:
     * 推荐人351获得直推390
     * 公司账户2获得590
     * 测试结果:通过
     */
    @Test
    public void merchant1() {
        User user = userMapper.selectByPrimaryKey(351);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1925);
        service.shareProfit(user, CreateRole.GOLD_MERCHANTS, uOrder);
    }

    /**
     * 提交人非员工
     * 提交人上级为其他角色,非boss
     * 预期结果:
     * 推荐人396获得直推390
     * 推荐人上级350获得间推200
     * 公司账户2获得390
     * 测试结果:通过
     */
    @Test
    public void merchant2() {
        User user = userMapper.selectByPrimaryKey(396);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1924);
        service.shareProfit(user, CreateRole.GENERAL_MERCHANTS, uOrder);
    }

    /**
     * 提交人为员工,未设置分润金额
     * 预期结果:
     * 员工上级419获得390
     * 公司账户2获得590
     * 测试结果:通过
     */
    @Test
    public void merchant3() {
        User user = userMapper.selectByPrimaryKey(451);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1922);
        service.shareProfit(user, CreateRole.GENERAL_MERCHANTS, uOrder);
    }

    /**
     * 提交人为员工,已设置分润金额
     * 预期结果:
     * 员工421获得直推200
     * 员工上级350获得190
     * 公司账户2获得590
     * 测试结果:通过
     */
    @Test
    public void merchant4() {
        User user = userMapper.selectByPrimaryKey(421);
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(1923);
        service.shareProfit(user, CreateRole.GENERAL_MERCHANTS, uOrder);
    }
}
