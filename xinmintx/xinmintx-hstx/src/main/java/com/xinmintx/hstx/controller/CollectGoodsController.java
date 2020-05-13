package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.CollectGoodsService;
import com.xinmintx.hstx.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2019/12/13 0010
 * @time: 下午 17:48
 * @Description: 收藏商品
 */
@RestController
@RequestMapping("/api/collectGoods")
public class CollectGoodsController extends BaseController {

    @Autowired
    private IMemberService IMemberService;

    @Autowired
    private CollectGoodsService collectGoodsService;

    /**
     * 添加搜藏商品
     *
     * @param goodsId 商品id
     * @return ResultCode
     */
    @PostMapping("/addCollect/{goodsId}/{type}")
    public ResultCode addCollectGoods(@PathVariable("goodsId") Integer goodsId, @PathVariable("type") Integer type) {
        Member member = IMemberService.findMemberByToken(token);
        boolean flag = collectGoodsService.insertCollectGoods(member.getId(), goodsId, type);
        ResultCode<Object> resultCode = new ResultCode<>();
        if (flag) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            return resultCode;
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
    }

    /**
     * 获取全部收藏
     *
     * @return ResultCode
     */
    @DisableAuth
    @GetMapping("/getAllCollect")
    public ResultCode getAllCollectGoods() {
        ResultCode<Object> resultCode = new ResultCode<>();
        if (token == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        Member member = IMemberService.findMemberByToken(token);
        if (member == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        List collectGoodsList = collectGoodsService.getCollectGoodsList(member.getId());
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(collectGoodsList);
        return resultCode;
    }

    /**
     * 删除收藏
     *
     * @param ids 收藏主表id
     * @return ResultCode
     */
    @PostMapping("/deleteCollect")
    public ResultCode deleteCollectGoods(@RequestBody Integer[] ids) {
        collectGoodsService.deleteCollectGoods(ids);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 检查商品是否已经收藏
     *
     * @param goodsId 商品id
     * @param type    收藏类型
     * @return ResultCode
     */
    @DisableAuth
    @PostMapping("/checkCollect/{goodsId}/{type}")
    public ResultCode checkCollectGoods(@PathVariable("goodsId") Integer goodsId, @PathVariable("type") Integer type) {
        ResultCode<Object> resultCode = new ResultCode<>();
        if (token == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        Member member = IMemberService.findMemberByToken(token);
        if (member == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        boolean flag = collectGoodsService.checkCollectGoods(member.getId(), goodsId, type);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(flag);
        return resultCode;
    }
}
