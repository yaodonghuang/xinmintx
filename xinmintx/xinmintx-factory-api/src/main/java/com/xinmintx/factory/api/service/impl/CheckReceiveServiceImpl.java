package com.xinmintx.factory.api.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.mapper.OrderMapper;
import com.xinmintx.factory.api.pojo.GoodsOrder;
import com.xinmintx.factory.api.service.CheckReceiveService;
import com.xinmintx.factory.api.service.ConfirmReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class CheckReceiveServiceImpl implements CheckReceiveService {
    @Resource
    private OrderMapper odMapper;
    @Resource
    private ConfirmReceiptService confirmReceiptService;

    // 确认收货调第三方接口
    @Override
    public ResultCode checkReceive(Integer orderId) {
        ResultCode rc;
        Map<String, String> paramMap = new HashMap<>();
        GoodsOrder order = odMapper.getOriOrderById(orderId);
        String orderNo = odMapper.getOrderNoById(order.getUOrderId());// 获取支付订单号
        if (StringUtils.isNotEmpty(orderNo)) {
            paramMap.put("orderId", orderNo);
            rc = confirmReceiptService.confirmReceipt(paramMap);
        } else {
            rc = new ResultCode();
            rc.setCode(500);
            rc.setMsg("订单未支付金额,请确认订单是否支付!");
        }
        return rc;
    }
}
