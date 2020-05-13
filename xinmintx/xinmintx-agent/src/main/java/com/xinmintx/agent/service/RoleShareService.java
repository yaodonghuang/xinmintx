package com.xinmintx.agent.service;

import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/21 0021
 * @time: 下午 20:51
 * @Description: 分润规则
 */
public interface RoleShareService {

    /**
     * 创建角色实现分润逻辑
     *
     * @param user       提交人(提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人))
     * @param createRole 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @param uOrder     订单
     */
    void shareProfit(User user, Integer createRole, UOrder uOrder);
}
