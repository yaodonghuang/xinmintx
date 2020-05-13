package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.vo.GoodsSkuVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.PtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/17 0017
 * @time: 下午 15:59
 * @Description: 拼团商品
 */
@RestController
@RequestMapping("/hs/pt/order")
public class PtGoodsController {

    @Autowired
    private PtGoodsService service;

    /**
     * 根据拼团商品获取拼团商品的规格
     *
     * @param ptGoodId ptgood id
     * @return
     */
    @PostMapping("/getPtGoodsSkuByGoodsId/{ptGoodId}")
    public ResultCode getPtGoodsSkuByGoodsId(@PathVariable("ptGoodId") Integer ptGoodId) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsSkuVo goodsSku = service.getPtGoodsSku(ptGoodId);
        if (goodsSku != null) {
            resultCode.setData(goodsSku);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 根据拼团记录id获取拼团商品的规格
     *
     * @param ptCodeId ptCodeId id
     * @return
     */
    @PostMapping("/getPtGoodsSkuByCodeId/{ptCodeId}")
    public ResultCode getPtGoodsSkuByCodeId(@PathVariable("ptCodeId") Integer ptCodeId) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsSkuVo goodsSku = service.getPtGoodsSkuByCodeId(ptCodeId);
        if (goodsSku != null) {
            resultCode.setData(goodsSku);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

}
