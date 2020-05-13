package com.xinmintx.agent.service;

import com.xinmintx.agent.model.StaffProfit;
import com.xinmintx.agent.model.User;

import java.util.List;

public interface StaffService {
    public int addStaff(User user);

    List<User> selectStaffByUserid(int userId);

    int updateBasic(int userId, double money);

    int updateGold(int userId, double money, double commission);

    StaffProfit selectStaffProfit(int userId, int i);
}
