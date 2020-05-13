package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.*;
import com.xinmintx.hstx.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author Administrator
 */
@Api(tags = "商品")
@RestController
@RequestMapping("/hs/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取商品目录
     *
     * @return
     */
    @PostMapping("/getGoodsTypes")
    public ResultCode getGoodsTypes() {
        ResultCode resultCode = new ResultCode();
        List<GoodsTypeVo> goodsTypes = goodsService.getGoodsTypes();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(goodsTypes);
        return resultCode;
    }

    /**
     * 按规格查询商品列表
     *
     * @return
     */
    @PostMapping("/getGoodsByType/{id}/{price}/{sales}")
    public ResultCode getGoodsByType(@PathVariable("id") Integer id, @PathVariable("price") Integer price, @PathVariable("sales") Integer sales, @RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getGoodsByType(id, price, sales, pageData);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 搜索商品列表
     *
     * @return
     */
    @PostMapping("/getGoodList")
    public ResultCode getGoodList(String name, @RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getGoodList(name, 0, pageData);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 获取今日精选商品
     *
     * @return 1.今日精选, 2热销
     */
    @PostMapping("/getGoodChoiceness")
    public ResultCode getGoodChoiceness(@RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getGoodList(null, 1, pageData);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 获取热销商品
     *
     * @return 1.今日精选, 2热销
     */
    @PostMapping("/getGoodHot")
    public ResultCode getGoodHot(@RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getGoodList(null, 2, pageData);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 获取抢购商品
     *
     * @return
     */
    @PostMapping("/getGoodPanicBuy")
    public ResultCode getGoodPreorder(@RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getGoodPanicBuy(pageData, token);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 获取首页推荐商品
     *
     * @return
     */
    @PostMapping("/getGoodsFeaturedFirst")
    public ResultCode getGoodsFeaturedFirst() {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        List<Map<String, Object>> list = goodsService.getGoodsFeaturedFirst();
        if (list != null && list.size() > 0) {
            resultCode.setData(list);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 获取拼团商品
     *
     * @return
     */
    @PostMapping("/getPtGoods")
    public ResultCode getPtGoods(@RequestBody PageData pageData) {
        ResultCode resultCode = new ResultCode();
        List<GoodsVo> list = goodsService.getPtGoods(pageData);
        resultCode.setData(list);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 根据商品id查询商品详情
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id获取商品")
    @PostMapping("/getGoodById/{id}")
    public ResultCode<GoodsVo> getGoodById(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsVo goods = goodsService.getGoodById(id, token);
        if (goods != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goods);
        }
        return resultCode;
    }

    /**
     * 根据id查询抢购商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getGoodByPanicId/{id}")
    public ResultCode getGoodByPanicId(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsVo goods = goodsService.getGoodByPanicId(id, token);
        if (goods != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goods);
        }
        return resultCode;
    }

    /**
     * 根据商品id查询拼团商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getPtGoodById/{id}")
    public ResultCode getPtGoodById(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsVo goods = goodsService.getPtGoodById(id);
        if (goods != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goods);
        }
        return resultCode;
    }

    /**
     * 根据代理商分享id查询拼团商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getPtGoodByAgent/{id}")
    public ResultCode getPtGoodByAgent(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsVo goods = goodsService.getPtGoodByAgent(id);
        if (goods != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goods);
        }
        return resultCode;
    }

    /**
     * 商品规格
     *
     * @param id
     * @return
     */
    @PostMapping("/getSkuById/{id}")
    public ResultCode getSkuById(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsSkuVo goodsSku = goodsService.getMemberSkuByGoodsId(id, token);
        if (goodsSku != null) {
            resultCode.setData(goodsSku);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 根据抢购id查询商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getSkuBuPanicId/{id}")
    public ResultCode getSkuBuPanicId(@PathVariable("id") Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsSkuVo goodsSku = goodsService.getMemberSkuByPanicId(id, token);
        if (goodsSku != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goodsSku);
        }
        return resultCode;
    }
}
