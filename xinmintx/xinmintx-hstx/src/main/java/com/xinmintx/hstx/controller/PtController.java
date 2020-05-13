package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.po.GoodPtgood;
import com.xinmintx.hstx.service.PtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2019/11/17 0016
 * @time: 下午 18:10
 * @Description: 拼团
 */
@RestController
@RequestMapping("/api/pt")
public class PtController extends BaseController {

    @Autowired
    private PtService ptService;

    /**
     * 获取拼团信息
     *
     * @param goodsId   商品id
     * @param groupType 拼团类型
     * @return ResultCode
     */
    @PostMapping("/getPt/{goodsId}/{groupType}")
    public ResultCode getPt(@PathVariable("goodsId") Integer goodsId, @PathVariable("groupType") Integer groupType) {
        List<HashMap> pt = ptService.getPt(goodsId, groupType);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(pt);
        return resultCode;
    }

    /**
     * 获取拼团详情
     *
     * @param id  团长id
     * @return    ResultCode
     */
    @PostMapping("/ptGetDetail/{id}")
    public ResultCode PtGetDetail(@PathVariable("id") Integer id) {
        HashMap ptDetail = ptService.getPtDetail(id);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(ptDetail);
        return resultCode;
    }

    /**
     * 查看惠商发起拼团人数
     *
     * @param goodsId 商品id
     * @return ResultCode
     */
    @PostMapping("/ptGetHsStyle/{goodsId}")
    public ResultCode PtGetHsStyle(@PathVariable("goodsId") Integer goodsId) {
        List<GoodPtgood> goodPtgoods = ptService.PtGetHsStyle(goodsId);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(goodPtgoods);
        return resultCode;
    }

    /**
     * 查看代理商发起拼团人数
     *
     * @param goodsPtUserId 拼团中间表id
     * @return ResultCode
     */
    @PostMapping("/ptGetProxyStyle/{goodsPtUserId}")
    public ResultCode PtGetProxyStyle(@PathVariable("goodsPtUserId") Integer goodsPtUserId) {
        List<GoodPtgood> goodPtgoods = ptService.PtGetProxyStyle(goodsPtUserId);
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(goodPtgoods);
        return resultCode;
    }

}
