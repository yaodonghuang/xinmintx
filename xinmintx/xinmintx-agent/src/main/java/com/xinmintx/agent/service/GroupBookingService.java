package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.Map;

public interface GroupBookingService {
    Map<Object,Object> addGroupBooking(int userId, Integer people, Integer shopingId);
}
