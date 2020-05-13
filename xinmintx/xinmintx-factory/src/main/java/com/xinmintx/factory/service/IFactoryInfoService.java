package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.model.GoodsOrderDetail;

import java.util.List;

public interface IFactoryInfoService {
    /**
     * 待发货扫描快递单号修改状态方法
     * @param detailId  详情id
     * @param courierNumber  快递单号
     * @return
     */
    int updateOrderStateAndcNum(Long detailId, String courierNumber);

    /**
     * 根据订单id查询退款信息
     * @param orderId
     * @return
     */
    GoodsOrder getOrderInfoById(Integer orderId);

    int updatePayStateById(Integer orderId, Integer ifPay);

    ResultCode factoryGetMoney(Factory ft);

    ResultCode getFactoryCashInfo(String token);

    ResultCode getFactoryCashLog(String token);

    int upReturnInformation(Integer orderId, String refundInformation,String returnMessage);

    List<GoodsOrderDetail> selectOrder(Integer orderId);

    int upReturnsSingleNumber(Long orderId, String courierNumber);
}