package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.GoodsOrderParentInterimVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/29 0029
 * @time: 上午 10:48
 * @Description:
 */
public interface GoodsOrderPayService {

    GoodsOrderParentInterimVo shoppingCartSettle(ShopVo shopVo);

    GoodsOrderParentInterimVo goodsSettle(ShopVo shopVo);

    GoodsOrderParentInterimVo goodsSettleByPanic(ShopVo shopVo);

    ResultCode createGoodsPayOrder(ShopVo shopVo);

    ResultCode goodsPay(ShopVo shopVo);

    ResultCode getGoodsPayOrder(Integer id);

}
