package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.ResultCode;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/10 0010
 * @time: 上午 9:01
 * @Description:
 */
public interface GoodsOrderService {

    ResultCode queryAllOrderByMemberId(Integer memberId);

    ResultCode getGoodsOrderDetail(Integer id);

    ResultCode cancelOrder(Integer id);

    ResultCode deleteOrder(Integer id);

    ResultCode remindOrder(Integer id);

    ResultCode updateAddress(Integer orderId, Integer addressId);

    ResultCode cancelOrderDetail(Integer id,String reason,String message);

    ResultCode queryCancelOrderDetail(Integer id);

    ResultCode cancelRefund(Integer id);

    ResultCode confirmReceipt(Integer id);

    ResultCode cancelReturn(Integer id);

    ResultCode receipt(Integer id);

    ResultCode returns(Integer id,String reason,String message);


}