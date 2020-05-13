package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/1/16 14:50
 * @Description:
 */
public interface UserService {
    ResultCode saveStudent (User user);
}
