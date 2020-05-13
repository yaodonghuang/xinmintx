package com.xinmintx.community.controller;

import com.google.gson.Gson;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.model.MerchantGoodsList;
import com.xinmintx.community.model.PayNotify;
import com.xinmintx.community.service.CommunityOrderService;
import com.xinmintx.community.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 社区订单controller
 *
 * @author hyd
 */
@RestController
@RequestMapping("/community/order")
@Transactional
public class CommunityOrderController {
    @Autowired
    private CommunityOrderService communityOrderService;

    /**
     * 获取社区菜品列表
     *
     * @param id
     * @param type
     * @param name
     * @return
     */
    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public ResultCode getGoodsList(@RequestParam(value = "communityId") Long id, @RequestParam(value = "type", required = false) Integer type, @RequestParam(value = "name", required = false) String name) {
        ResultCode resultCode = communityOrderService.getGoodsList(id, type, name);
        return resultCode;
    }

    /**
     * 订单创建接口
     *
     * @param objects
     * @param token
     * @return
     */
    @RequestMapping(value = "/orderCreation", method = RequestMethod.POST)
    public ResultCode orderCreation(@RequestBody(required=false) String objects, @RequestHeader("token") String token) {
        MerchantGoodsList mgl = new Gson().fromJson(objects, MerchantGoodsList.class);
        ResultCode resultCode;
        if (StringUtil.judgeIsSetBiddingTime()) {
            resultCode = communityOrderService.orderCreation(mgl.getMgList(), token);
        } else {
            resultCode = new ResultCode();
            resultCode.setCode(500);
            resultCode.setMsg("晚上20:00至早上8:00不能下单");
        }
        return resultCode;
    }

    /**
     * 支付回调接口
     *
     * @param payNotify
     * @return
     */
    @RequestMapping(value = "/orderPayNotify", method = RequestMethod.POST)
    public ResultCode orderPayNotify(PayNotify payNotify) {
        ResultCode resultCode = communityOrderService.orderPayNotify(payNotify);
        return resultCode;
    }

    /**
     * 获取配送费信息
     *
     * @param objects
     * @return
     */
    @RequestMapping(value = "/feeInfo", method = RequestMethod.POST)
    public ResultCode getFeeInfo(@RequestBody String objects, @RequestHeader("token") String token) {
        MerchantGoodsList mgl = new Gson().fromJson(objects, MerchantGoodsList.class);
        ResultCode resultCode = communityOrderService.getFeeInfo(mgl.getMgList(), token);
        return resultCode;
    }

    /**
     * 我的订单支付接口
     */
    @RequestMapping(value = "/payInfoOne", method = RequestMethod.POST)
    public ResultCode PayInfoOne(@RequestParam("orderId") Integer id, @RequestParam("memberId") Integer memberId, @RequestParam("communityId") Long communityId) {
        ResultCode resultCode = communityOrderService.payInfoOne(id, memberId, communityId);
        return resultCode;
    }
}
