package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/15 0015
 * @time: 下午 17:58
 * @Description:
 */
public interface PayService {

    Map<String, Object> createOrder(User user, int role, int userId);

}
