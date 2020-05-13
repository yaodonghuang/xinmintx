package com.xinmintx.factory.controller;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.po.GoodsType;
import com.xinmintx.factory.service.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/18
 * @time: 14:28
 * @Description: 商品分类管理控制
 */
@Api(tags = "商品")
@RestController
@RequestMapping("/factory/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * @author: create by hjh
     * @time: 2020/3/18 14:48
     * @Description: 查询商品分类信息
     * @param token 登录信息
     * @param resultCode 请忽略，传参返还信息
     * @return: com.xinmintx.factory.common.ResultCode
     */
    @ApiOperation("查询商品分类信息")
    @PostMapping("/queryGoodsType")
    public ResultCode<List<GoodsType>> queryGoodsType(@RequestHeader("token")String token , @Ignore ResultCode<List<GoodsType>> resultCode) {
        resultCode.setData(goodsTypeService.queryGoodsType(token));
        return resultCode;
    }
}
