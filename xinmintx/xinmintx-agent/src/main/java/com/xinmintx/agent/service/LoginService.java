package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;
import com.xinmintx.agent.modelDTO.NumberMedol;

public interface LoginService {
    String sendcode(String phone);

    User selectByPhone(String phone);

    NumberMedol selectNumberByUserid(Integer userId);

    void saveOpenId(User user);

    double selectBalanceByUserid(Integer userId);

    User selectUserByOpenid(String openid);

    void updateUser(User user);

    void promotedPartner(User user);
}

