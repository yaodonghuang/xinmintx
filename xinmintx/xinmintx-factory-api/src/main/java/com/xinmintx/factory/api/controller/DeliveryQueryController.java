package com.xinmintx.factory.api.controller;

import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.mapper.VenderMapper;
import com.xinmintx.factory.api.pojo.GoodsOrder;
import com.xinmintx.factory.api.pojo.GoodsOrderDetail;
import com.xinmintx.factory.api.pojo.VenderOrder;
import com.xinmintx.factory.api.service.DeliveryService;
import com.xinmintx.factory.api.service.IFactoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: com.xinmintx.factory.controller.DeliveryQueryController
 * @Author:Pikachu
 * @Date: 2019/12/4 13:48
 * @Version: v1.0
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryQueryController {
    @Resource
    private DeliveryService deliveryService;
    @Resource
    private IFactoryInfoService factoryInfoService;
    @Resource
    private VenderMapper venderMapper;

    /**
     * 查询发货状态
     *
     * @param token
     * @param statu
     * @return
     */

    @RequestMapping("/query")
    public ResultCode queryStatus(@RequestHeader("token") String token, @RequestParam(required = false) Integer statu) {
        ResultCode code = new ResultCode();
        List list = deliveryService.query(token, statu);
        if (list != null && list.size() > 0) {
            code.setCode(200);
            code.setMsg("SUCCESS");
            code.setData(list);
        } else {
            code.setCode(200);
            code.setMsg("订单为空");
        }
        return code;
    }

    /**
     * 查询订单评价
     *
     * @param token
     * @return
     */

    @RequestMapping("/queryEvaluate")
    public ResultCode queryEvaluate(@RequestHeader("token") String token) {
        ResultCode code = new ResultCode();
        List list = deliveryService.queryEvaluate(token);
        if (list != null && list.size() > 0) {
            code.setCode(200);
            code.setMsg("SUCCESS");
            code.setData(list);
        } else {
            code.setCode(200);
            code.setMsg("订单为空");
        }

        return code;
    }

    /**
     * 根据时间段查询订单
     *
     * @param token
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping("/queryByTime")
    public ResultCode queryByTime(@RequestHeader("token") String token, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate) {
        ResultCode code = new ResultCode();
        List list = deliveryService.queryByTime(token, beginDate, endDate);
        if (list != null && list.size() > 0) {
            code.setCode(200);
            code.setMsg("SUCCESS");
            code.setData(list);
        } else {
            code.setCode(200);
            code.setMsg("时间段内没有订单");
        }
        return code;
    }

    /**
     * 查询所有订单
     *
     * @param token
     * @return
     */
    @RequestMapping("/queryAll")
    public ResultCode queryAll(@RequestHeader("token") String token) {
        ResultCode code = new ResultCode();
        List list = deliveryService.queryAll(token);
        if (list != null && list.size() > 0) {
            code.setCode(200);
            code.setMsg("SUCCESS");
            code.setData(list);
        } else {
            code.setCode(200);
            code.setMsg("没有订单或token不正确");
        }
        return code;
    }

    /**
     * 修改子订单状态
     *
     * @param statu
     * @param orderNum
     * @return
     */
    @RequestMapping("/upStatu")
    public ResultCode upStatu(@RequestParam("statu") int statu, @RequestParam("orderNum") String orderNum) {
        ResultCode code = new ResultCode();
        if (3==statu){
            VenderOrder order =deliveryService.queryById(orderNum);
            if (order!=null){
                if (12==order.getOrderState()){
                    code.setCode(500);
                    code.setMsg("申请退货成功，修改失败");
                    return code;
                }else if(13==order.getOrderState()){
                    code.setCode(500);
                    code.setMsg("退货退款成功，修改失败");
                    return code;
                }
            }else {
                code.setCode(500);
                code.setMsg("订单号输入错误");
                return code;
            }
        }
        int i = deliveryService.upStatu(statu, orderNum);
        if (i > 0) {
            code.setCode(200);
            code.setMsg("状态修改成功");
        } else {
            code.setCode(500);
            code.setMsg("状态修改失败");
        }
        return code;
    }

    /**
     * 根据主订单号查询
     *
     * @param orderId
     * @return
     */
    @PostMapping("/queryByOrderId")
    public ResultCode queryByOrderId(@RequestParam("orderId") String orderId) {
        ResultCode code = new ResultCode();
        GoodsOrder order = deliveryService.queryByOrderId(orderId);
        if (order != null) {
            code.setCode(200);
            code.setMsg("SUCCESS");
            code.setData(order);
        } else {
            code.setCode(200);
            code.setMsg("查询失败");
        }
        return code;
    }

    /**
     * 修改主订单状态
     *
     * @param statu
     * @param orderNum
     * @return
     */
    @RequestMapping("/upMainStatu")
    public ResultCode upMainStatu(@RequestParam("statu") int statu, @RequestParam("orderNum") String orderNum) {
        ResultCode code = new ResultCode();
        if (orderNum != null) {
            if(2==statu){
                String orderId = orderNum;
                GoodsOrder order = deliveryService.queryByOrderId(orderId);
                if (order!=null){
                    if (9==(order.getOrderState())){
                        code.setCode(500);
                        code.setMsg("已成功退款");
                        return code;
                    }
                }else {
                    code.setCode(500);
                    code.setMsg("订单号输入错误");
                    return code;
                }
            }
            Integer orderId = Integer.valueOf(orderNum);
            List<GoodsOrderDetail> godList = factoryInfoService.selectOrder(orderId);
            List<Long> idList = new ArrayList<>();
            if(godList != null && godList.size() > 0){
                godList.forEach(god->{
                    idList.add(god.getId());
                });
            }
            if(idList != null && idList.size() > 0){
                venderMapper.updateSonStatusList(idList,statu);
            }
            int i = deliveryService.upMainStatu(statu, orderNum);
            if (i > 0) {
                code.setCode(200);
                code.setMsg("状态修改成功");
            } else {
                code.setCode(500);
                code.setMsg("状态修改失败");
            }

        } else {
            code.setCode(500);
            code.setMsg("订单号为空");
        }
        return code;
    }
}
