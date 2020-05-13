package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.GoodsOrderParentInterimVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;
import com.xinmintx.hstx.service.GoodsOrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/11 0011
 * @time: 下午 18:30
 * @Description: 创建商品订单, 创建支付订单
 */
@RestController
@RequestMapping("/api/order")
public class GoodsOrderPayController extends BaseController {

    @Autowired
    private GoodsOrderPayService goodsOrderPayService;

    /**
     * 商品购物车结算
     *
     * @param shopVo
     * @return
     */
    @PostMapping("/shoppingCartSettle")
    public ResultCode<GoodsOrderParentInterimVo> shoppingCartSettle(@RequestBody ShopVo shopVo) {
        ResultCode<GoodsOrderParentInterimVo> resultCode = new ResultCode<>();
        resultCode.setMsg("FAIL");
        resultCode.setCode(500);
        shopVo.setToken(token);
        GoodsOrderParentInterimVo goodsOrderVos = goodsOrderPayService.shoppingCartSettle(shopVo);
        if (goodsOrderVos != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goodsOrderVos);
        }
        return resultCode;
    }

    /**
     * 单个商品结算(普通商品立即购买)
     *
     * @param shopVo
     * @return
     */
    @PostMapping("/goodsSettle")
    public ResultCode<GoodsOrderParentInterimVo> goodsSettle(@RequestBody ShopVo shopVo) {
        ResultCode<GoodsOrderParentInterimVo> resultCode = new ResultCode<>();
        resultCode.setMsg("FAIL");
        resultCode.setCode(500);
        shopVo.setToken(token);
        GoodsOrderParentInterimVo goodsOrderVos = goodsOrderPayService.goodsSettle(shopVo);
        if (goodsOrderVos != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goodsOrderVos);
        }
        return resultCode;
    }


    /**
     * 单个商品结算(抢购商品)
     *
     * @param shopVo
     * @return
     */
    @PostMapping("/goodsSettleByPanic")
    public ResultCode<GoodsOrderParentInterimVo> goodsSettleByPanic(@RequestBody ShopVo shopVo) {
        ResultCode<GoodsOrderParentInterimVo> resultCode = new ResultCode<>();
        resultCode.setMsg("FAIL");
        resultCode.setCode(500);
        shopVo.setToken(token);
        GoodsOrderParentInterimVo goodsOrderVos = goodsOrderPayService.goodsSettleByPanic(shopVo);
        if (goodsOrderVos != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goodsOrderVos);
        }
        return resultCode;
    }


    /**
     * 创建订单和支付订单
     *
     * @param shopVo
     * @return
     */
    @PostMapping("/createGoodsPayOrder")
    public ResultCode createGoodsPayOrder(@RequestBody ShopVo shopVo) {
        shopVo.setToken(token);
        return goodsOrderPayService.createGoodsPayOrder(shopVo);
    }

    /**
     * 获取商品支付订单信息
     *
     * @return
     */
    @DisableAuth
    @PostMapping("/getGoodsPayOrder/{id}")
    public ResultCode getGoodsPayOrder(@PathVariable("id") Integer id) {
        return goodsOrderPayService.getGoodsPayOrder(id);
    }

    /**
     * 商品订单付款
     *
     * @param id 订单编号
     * @return
     */
    @PostMapping("/goodsPay/{id}")
    public ResultCode goodsPay(@PathVariable("id") Integer id, @RequestBody ShopVo shopVo) {
        if (shopVo.getPayType() == null || shopVo.getPayType() != 4) {
            shopVo.setPayType(1);
        }
        shopVo.setId(id);
        shopVo.setToken(token);
        return goodsOrderPayService.goodsPay(shopVo);
    }


}
