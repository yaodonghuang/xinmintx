package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.CommodityExt;
import com.xinmintx.agent.service.GoodsService;
import com.xinmintx.agent.service.UOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author Administrator
 */

@RestController
@RequestMapping("/agent/show")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UOrderService unifiedorder;

    /**
     * 查询商品列表
     *
     * @return
     */
    @RequestMapping("/getGood")
    public ResultCode getGoods(String name) {
        List<CommodityExt> list = goodsService.getGoods(name);
        ResultCode resultCode = new ResultCode();
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("成功");
        return resultCode;
    }

    /**
     * 根据商品id查询首页详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/getGoodById/{id}")
    public ResultCode getGoodById(@PathVariable("id") Integer id) {
        CommodityExt commodityExt = goodsService.getGoodById(id);
        String pictyre[] = goodsService.getPictures(id);
        Map<Object, Object> map = new HashMap<>();
        map.put("commodityExt", commodityExt);
        map.put("pictyre", pictyre);
        ResultCode resultCode = new ResultCode();
        resultCode.setData(map);
        resultCode.setCode(200);
        resultCode.setMsg("成功");
        return resultCode;
    }

    /**
     * 查询商品参数
     *
     * @param id
     * @return
     */
    @RequestMapping("/getParameter/{id}")
    public ResultCode getParameter(@PathVariable("id") Integer id) {
        String parameter = goodsService.getParameter(id);
        ResultCode resultCode = new ResultCode();
        resultCode.setData(parameter);
        resultCode.setCode(200);
        resultCode.setMsg("成功");
        return resultCode;
    }

    /**
     * 商品规格
     *
     * @param id
     * @return
     */
    @RequestMapping("/getType")
    public ResultCode getType(Integer id) {
        List<CommodityExt> list = goodsService.getType(id);
        ResultCode resultCode = new ResultCode();
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("成功");
        return resultCode;
    }
}
