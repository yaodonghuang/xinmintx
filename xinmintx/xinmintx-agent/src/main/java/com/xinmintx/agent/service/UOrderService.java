package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UOrder;

import java.util.Map;

public interface UOrderService {

    UOrder findByTradeNo(String outTradeNo);

    UOrder findByUorderNo(String orderNo);

    int updateUorder(UOrder uOrder);

    Map<String, Object> unifiedorder(Long totalFee, String body, User user, int userId, String roleId) throws RuntimeException;

    Map<String, String> closeorder(Map<String, String> param);

    Map<String,String> queryOrder(Map<String, String> param);

    int addUorder(UOrder uOrder);
}
