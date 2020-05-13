package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.List;

public interface PickupService {
    int addPickUp(User user);

    List<User> selectpickupByUserid(int userId);
}
