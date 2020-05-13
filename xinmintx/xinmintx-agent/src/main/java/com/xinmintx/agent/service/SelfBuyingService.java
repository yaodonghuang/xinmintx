package com.xinmintx.agent.service;




import com.xinmintx.agent.common.PayBean;
import com.xinmintx.agent.model.ShippingAddress;
import com.xinmintx.agent.model.UOrder;

import java.util.Map;

public interface SelfBuyingService {

    UOrder findByTradeNo(String outTradeNo);

    UOrder findByUorderNo(String orderNo);

    int updateUorder(UOrder uOrder);

    Map<String, Object> unifiedorder(PayBean payBean) throws RuntimeException;

    Map<String, String> closeorder(Map<String, String> param);

    Map<String,String> queryOrder(Map<String, String> param);

    int addUorder(UOrder uOrder);


}
