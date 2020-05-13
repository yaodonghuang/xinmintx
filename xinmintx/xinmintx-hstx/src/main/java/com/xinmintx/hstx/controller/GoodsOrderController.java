package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.GoodsOrderService;
import com.xinmintx.hstx.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2019/12/10 0010
 * @time: 上午 9:00
 * @Description: 订单
 */
@RestController
@RequestMapping("/api/goods/order")
public class GoodsOrderController extends BaseController {

    @Autowired
    private GoodsOrderService goodsOrderService;


    /**
     * 根据会员id查询全部订单列表
     *
     * @return 全部订单详情
     */
    @PostMapping("/getGoodsOrder")
    public ResultCode getAllGoodsOrder() {
        return goodsOrderService.queryAllOrderByMemberId(member.getId());
    }

    /**
     * 获取订单详情
     *
     * @param id 主订单id
     * @return resultCode
     */
    @PostMapping("/getDetail/{id}")
    public ResultCode getDetail(@PathVariable("id") Integer id) {
        return goodsOrderService.getGoodsOrderDetail(id);
    }


    /**
     * 取消订单
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @PostMapping("/cancelOrder/{id}")
    public ResultCode cancelOrder(@PathVariable("id") Integer id) {
        return goodsOrderService.cancelOrder(id);
    }


    /**
     * 删除订单
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @PostMapping("/deleteOrder/{id}")
    public ResultCode deleteOrder(@PathVariable("id") Integer id) {
        return goodsOrderService.deleteOrder(id);
    }

    /**
     * 提醒发货
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @PostMapping("/remindOrder/{id}")
    public ResultCode remindOrder(@PathVariable("id") Integer id) {
        return goodsOrderService.remindOrder(id);
    }

    /**
     * 修改收货地址
     *
     * @param orderId   主订单id
     * @param addressId 地址id
     * @return ResultCode
     */
    @PostMapping("/updateAddress/{orderId}/{addressId}")
    public ResultCode updateAddress(@PathVariable("orderId") Integer orderId, @PathVariable("addressId") Integer addressId) {
        return goodsOrderService.updateAddress(orderId, addressId);
    }


    /**
     * 用户退款
     *
     * @param id 主订单表id
     * @return ResultCode
     */
    @PostMapping("/cancelOrderDetail")
    public ResultCode cancelOrderDetail(Integer id,String reason,String message) {
        return goodsOrderService.cancelOrderDetail(id,reason,message);
    }

    /**
     * 获取退款详情
     *
     * @param id  子订单表id
     * @return    ResultCode
     */
    @PostMapping("/getCancelOrderDetail/{id}")
    public ResultCode getCancelOrderDetail(@PathVariable("id") Integer id) {
        return goodsOrderService.queryCancelOrderDetail(id);
    }


    /**
     * 取消退款
     *
     * @param id  主订单id
     * @return    ResultCode
     */
    @PostMapping("/cancelRefund/{id}")
    public ResultCode cancelRefund(@PathVariable("id") Integer id) {
        return goodsOrderService.cancelRefund(id);
    }


    /**
     * 确认收货
     *
     * @param id  主订单id
     * @return    ResultCode
     */
    @PostMapping("/confirmReceipt/{id}")
    public ResultCode confirmReceipt(@PathVariable("id") Integer id) {
        return goodsOrderService.confirmReceipt(id);
    }



    /**
     * 延长收货
     *
     * @param id  主订单表id
     * @return    ResultCode
     */
    @PostMapping("/receipt/{id}")
    public ResultCode receipt(@PathVariable("id") Integer id) {
        return goodsOrderService.receipt(id);
    }

    /**
     * 用户退货
     *
     * @param id  子订单表id
     * @return    ResultCode
     */
    @PostMapping("/returns")
    public ResultCode returns(Integer id,String reason,String message) {
        return goodsOrderService.returns(id,reason,message);
    }

    /**
     * 取消退货
     *
     * @param id   子订单id
     * @return     ResultCode
     */
    @PostMapping("/cancelReturn/{id}")
    public ResultCode cancelReturn(@PathVariable("id") Integer id) {
        return goodsOrderService.cancelReturn(id);
    }

}
