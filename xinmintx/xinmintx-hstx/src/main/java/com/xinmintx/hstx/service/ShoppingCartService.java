package com.xinmintx.hstx.service;


import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.ShoppingCartDetail;
import com.xinmintx.hstx.pojo.vo.CarVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopCarVo;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartDetail checkCar (Integer memberId, Integer goodsId,Integer skuId,Integer type);

    void addShoppingCar (Integer memberId, Integer goodsId, Integer count,Integer skuId,Integer type);

    void updateShoppingCar (ShoppingCartDetail shoppingCartDetail);

    boolean checkGoods (Integer goodsId);

    boolean checkGoodsSku (Integer skuId);

    List<ShopCarVo> selectCars(Member member);

    void deleteCar(List<Integer> list);

    void updateCar(List<CarVo> list);

    int changeCar(Integer id,Integer count);

    ResultCode addGoodPanicBuyCar (Integer memberId,Integer panicBuyId, Integer skuId, Integer count);

}
