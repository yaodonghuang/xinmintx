package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodsOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface OrderMapper {
    String getSendStateById(@Param("detailId") Long detailId);

    int updateOrderStateAndcNum(@Param("detailId") Long detailId, @Param("courierNumber") String courierNumber, String sendState);

    Integer getOrderIdByDetailId(@Param("detailId") Long detailId);

    Integer ifExistsWaitSendPro(@Param("orderId") Integer orderId);

    int updateOrderState(@Param("orderId") Integer orderId);

    GoodsOrder getOrderById(@Param("id") Integer orderId);

    int updatePayStateById(@Param("id") Integer orderId, @Param("ifPay") Integer ifPay);

    String getTransactionId(@Param("id") Integer uOrderId);

//    int insertRefundInfo(goodsOrderRefund orderRefund);

    BigDecimal getTotalAmount(@Param("id") Integer detailId);

    int updateSendDate(@Param("id") Integer orderId);

    String getOrderNoById(@Param("id") Integer uOrderId);

    int updateDetailOrderState(@Param("id") Integer detailId, @Param("orderState") Integer orderState);

    Integer ifAllRefund(@Param("id") Integer orderId, @Param("orderState") Integer orderState);

    int updateOrderStateById(@Param("id") Integer orderId, @Param("orderState") Integer orderState);

    Integer getDetailOrderState(@Param("id") Integer detailId);

    GoodsOrder getOriOrderById(@Param("id") Integer orderId);
}
