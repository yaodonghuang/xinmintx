package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.mapper.GoodsSkuMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.ShoppingCartDetail;
import com.xinmintx.hstx.pojo.vo.CarVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopCarVo;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * @author sw
 * @Description: 购物车
 */
@RestController
@RequestMapping("/api/car")
public class ShoppingCartController extends BaseController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;


    /**
     * 添加购物车
     *
     * @param goodsId 商品id
     * @param skuId   规格id
     * @param count   数量
     * @param type    商品类型
     * @return ResultCode
     */
    @PostMapping("/addCar")
    public ResultCode addCar(Integer goodsId, Integer skuId, Integer count, Integer type) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取会员id
        int memberId = memberService.findMemberByToken(token).getId();
        //判断是否存在该商品
        boolean flag = shoppingCartService.checkGoods(goodsId);
        if (!flag) {
            resultCode.setCode(500);
            resultCode.setMsg("商品不存在");
            return resultCode;
        }
        //判断规格是否存在
        boolean flags = shoppingCartService.checkGoodsSku(skuId);
        if (!flags) {
            resultCode.setCode(500);
            resultCode.setMsg("规格不存在");
            return resultCode;
        }
        //查询购物车是否已经存在该商品
        ShoppingCartDetail shoppingCartDetail = shoppingCartService.checkCar(memberId, goodsId, skuId, type);
        //走这里说明购物车没有该物品,直接添加
        if (shoppingCartDetail == null) {
            shoppingCartService.addShoppingCar(memberId, goodsId, count, skuId, type);
            //走这里说明已经存在该物品,添加数量
        } else {
            shoppingCartDetail.setTotal(shoppingCartDetail.getTotal() + count);
            //获取库存
            Integer stockNum = goodsSkuMapper.selectById(skuId).getStockNum();
            Integer total = shoppingCartDetail.getTotal();
            //如果添加的数量大于库存就不能添加
            if (total <= stockNum) {
                shoppingCartService.updateShoppingCar(shoppingCartDetail);
            }
        }
        resultCode.setCode(200);
        resultCode.setMsg("添加成功");
        return resultCode;
    }

    /**
     * 获取购物车列表
     *
     * @return 购物车详情
     */
    @PostMapping("/selectCar")
    public ResultCode<Object> selectCar() {
        ResultCode<Object> resultCode = new ResultCode<>();
        Member member = memberService.findMemberByToken(token);
        List<ShopCarVo> shops = shoppingCartService.selectCars(member);
        if (shops == null || shops.size() == 0) {
            resultCode.setCode(500);
            resultCode.setMsg("购物车为空");
            resultCode.setData(shops);
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("查询成功");
        resultCode.setData(shops);
        return resultCode;
    }

    /**
     * 结算购物车
     *
     * @param list 购物车商品集合
     * @return ResultCode
     */
    @PostMapping("/updateCar")
    public ResultCode<Object> updateCar(@RequestBody List<CarVo> list) {
        ResultCode<Object> resultCode = new ResultCode<>();
        shoppingCartService.updateCar(list);
        resultCode.setCode(200);
        resultCode.setMsg("成功");
        return resultCode;
    }

    /**
     * 删除购物车
     *
     * @param ids 购物车id
     * @return ResultCode
     */
    @PostMapping("/deleteCar")
    public ResultCode<Object> deleteCar(@RequestBody Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        shoppingCartService.deleteCar(list);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("删除成功");
        return resultCode;
    }

    /**
     * 修改购物车数量
     *
     * @param id    购物车id
     * @param count 数量
     * @return
     */
    @PostMapping("/changeCar/{id}/{count}")
    public ResultCode<Object> changeCar(@PathVariable("id") Integer id, @PathVariable("count") Integer count) {
        int total = shoppingCartService.changeCar(id, count);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(total);
        return resultCode;
    }

    /**
     * 添加抢购商品购物车
     *
     * @param panicBuyId 限时抢购id
     * @param skuId      规格id
     * @param count      数量
     * @return ResultCode
     */
    @PostMapping("/addGoodPanicBuyCar")
    public ResultCode addGoodPanicBuyCar(Integer panicBuyId, Integer skuId, Integer count) {
        //获取会员id
        int memberId = memberService.findMemberByToken(token).getId();
        return shoppingCartService.addGoodPanicBuyCar(memberId,panicBuyId,skuId,count);
    }

}
