package com.xinmintx.agent.service;

import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/22 0022
 * @time: 上午 11:55
 * @Description:
 */
public interface ShareService {

    /**
     * 创建角色实现分润逻辑
     *
     * @param user   提交人(提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人))
     * @param roleId 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @param uOrder 订单
     */
    void shareBenefit(User user, String roleId, UOrder uOrder);

    /**
     * 分公司获取奖励
     * @param userId 合伙人id
     */
    void companyAward(int userId);
}
