package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;
import com.xinmintx.hstx.service.PtOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/17 0017
 * @time: 下午 18:15
 * @Description: 创建拼团信息
 */
@RestController
@RequestMapping("/api/pt/order")
public class PtOrderController extends BaseController {

    @Autowired
    private PtOrderService ptOrderService;

    /**
     * 发起拼团
     *
     * @param shopVo  购买信息
     * @return 支付信息
     */
    @PostMapping("/initiateGroup")
    public ResultCode initiateGroup(@RequestBody ShopVo shopVo) {
        shopVo.setToken(token);
        return ptOrderService.initiateGroup(shopVo);
    }

    /**
     * 参加拼团
     *
     * @param shopVo  购买信息
     * @return 支付信息
     */
    @PostMapping("/joinGroup")
    public ResultCode joinGroup(@RequestBody ShopVo shopVo) {
        shopVo.setToken(token);
        return ptOrderService.joinGroup(shopVo);
    }

}
