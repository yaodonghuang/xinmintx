package com.xinmintx.factory.api.controller;


import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.pojo.Factory;
import com.xinmintx.factory.api.pojo.GoodsOrderDetail;
import com.xinmintx.factory.api.service.IFactoryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/factoryInfo")
public class FactoryInfoController {
    @Autowired
    private IFactoryInfoService factoryInfoService;

    /**
     * 扫描单号
     *
     * @return
     */
    @RequestMapping("/scanNum/{detailId}")
    public ResultCode updateOrderStateAndcNum(@PathVariable("detailId") Long detailId, String courierNumber) {
        ResultCode code = new ResultCode();
        int result = factoryInfoService.updateOrderStateAndcNum(detailId, courierNumber);
        if (result == 1) {
            code.setCode(200);
            code.setMsg("SUCCESS");
        } else if (result == 0) {
            code.setCode(500);
            code.setMsg("待发货状态才能进行操作");
        } else {
            code.setCode(500);
            code.setMsg("FAIL");
        }
        return code;
    }

    /**
     * 工厂提现
     *
     * @param ft
     * @return
     */
    @RequestMapping("/cashWithdrawal")
    public ResultCode factoryGetMoney(@RequestBody Factory ft, @RequestHeader String token) {
        ft.setToken(token);
        ResultCode code = factoryInfoService.factoryGetMoney(ft);
        return code;
    }

    /**
     * 查询厂家余额和冻结余额接口
     *
     * @param token
     * @return
     */
    @GetMapping("/cashInfo")
    public ResultCode factoryGetCash(@RequestHeader String token) {
        return factoryInfoService.getFactoryCashInfo(token);
    }

    /**
     * 查询厂家提现日志接口
     *
     * @param token
     * @return
     */
    @GetMapping("/cashLog")
    public ResultCode factoryCashLog(@RequestHeader String token) {
        return factoryInfoService.getFactoryCashLog(token);
    }

    /**
     * 填写退款原因
     *
     * @param orderId
     * @param
     * @return
     */
    @PostMapping("/upReturnInformation")
    public ResultCode upReturnInformation(@RequestParam() Integer orderId, @RequestParam("refundInformation") String refundInformation, @RequestParam("returnMessage") String returnMessage) {
        ResultCode code = new ResultCode();
        List<GoodsOrderDetail> godList = factoryInfoService.selectOrder(orderId);
        if (godList != null && godList.size() > 0 && godList.get(0) != null) {
            GoodsOrderDetail goodsOrderDetail = godList.get(0);
            long order = goodsOrderDetail.getOrderState();
            if (order == 5 || order == 3) {
                int i = factoryInfoService.upReturnInformation(orderId, refundInformation, returnMessage);
                if (i > 0) {
                    code.setCode(200);
                    code.setMsg("退货退款申请中");
                } else {
                    code.setCode(500);
                    code.setMsg("退货退款申请失败");
                }
            } else {
                code.setCode(500);
                code.setMsg("退货退款申请失败");
            }
        } else {
            code.setCode(500);
            code.setMsg("订单不存在");
        }

        return code;
    }

    /**
     * 填写退货单号
     *
     * @param courierNumber
     * @param orderId
     * @return
     */
    @PostMapping("/upReturnsSingleNumber")
    public ResultCode upReturnsSingleNumber(@RequestParam("courierNumber") String courierNumber, @RequestParam("orderId") Long orderId) {
        ResultCode code = new ResultCode();
        int i = factoryInfoService.upReturnsSingleNumber(orderId, courierNumber);
        if (i > 0) {
            code.setCode(200);
            code.setMsg("退货单号填写成功");
        } else {
            code.setCode(500);
            code.setMsg("退货单号填写失败");
        }
        return code;
    }
}
