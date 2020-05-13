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
 * @Description: 提交分公司分润测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyShareServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private RoleShareService service;

    /**
     * 提交人上级为boss或者无
     * 预期结果:
     * 推荐人351获得直推5000
     * 测试结果:通过
     */
    @Test
    public void company() {
        User user = userMapper.selectByPrimaryKey(351);
        service.shareProfit(user, CreateRole.COMPANY, null);
    }
}
