package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMapper {
    String getSendStateById(@Param("detailId") Long detailId);

    int updateOrderStateAndcNum(@Param("detailId") Long detailId, @Param("courierNumber") String courierNumber, String sendState);

    Integer getOrderIdByDetailId(@Param("detailId") Long detailId);

    Integer ifExistsWaitSendPro(@Param("orderId") Integer orderId);

    int updateOrderState(@Param("orderId") Integer orderId);

    GoodsOrder getOrderById(@Param("id") Integer orderId);

    int updatePayStateById(@Param("id") Integer orderId,@Param("ifPay") Integer ifPay);

    String getTransactionId(@Param("id") Integer uOrderId);

    int insertRefundInfo(goodsOrderRefund orderRefund);

    BigDecimal getTotalAmount(@Param("id") Integer detailId);

    int updateSendDate(@Param("id") Integer orderId);

    String getOrderNoById(@Param("id") Integer uOrderId);

    int updateDetailOrderState(@Param("id") Integer detailId, @Param("orderState") Integer orderState);

    Integer ifAllRefund(@Param("id") Integer orderId, @Param("orderState") Integer orderState);

    int updateOrderStateById(@Param("id") Integer orderId, @Param("orderState") Integer orderState);

    int updateOrderDetailStateById(@Param("id") Integer orderId, @Param("orderState") Integer orderState);

    Integer getDetailOrderState(@Param("id") Integer detailId);

    GoodsOrder getOriOrderById(@Param("id") Integer orderId);

    Factory getCashMoney(@Param("id") Long factoryId);

    Factory getFactoryCashByToken(@Param("token") String token);

    int insertFactoryCashInfo(@Param("id") Long factoryId, @Param("requestSn") String requestSn);

    List<PoboNotify> getCashLog(@Param("id") Long factoryId);

    int updateFactoryMoney(@Param("money") BigDecimal money, @Param("requestSn") String requestSn);

    int upReturnInformation(@Param("orderId") Integer orderId,@Param("refundInformation")String refundInformation,@Param("returnMessage")String returnMessage);

    List<GoodsOrderDetail> selectOrder(int orderId);

    int upReturnsSingleNumber(@Param("orderId") Long orderId, @Param("courierNumber") String courierNumber);

    int updateOrderCourierNumber(@Param("orderId") Integer orderId, @Param("courierNumber") String courierNumber);

    int settleOrder(@Param("orderId") Integer orderId);

}
