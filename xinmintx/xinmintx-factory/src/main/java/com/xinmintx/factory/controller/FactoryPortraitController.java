package com.xinmintx.factory.controller;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.po.FactoryName;
import com.xinmintx.factory.service.FactoryPortraitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "工厂")
@RestController
@RequestMapping("/factory/portrait")
public class FactoryPortraitController {

    @Autowired
    private FactoryPortraitService factoryPortraitService;

    /**
     * 工厂设置 头像 名称
     * @param token
     * @param factoryName
     * @param resultCode
     * @author: create by chf
     * @return
     */
    @ApiOperation("工厂设置 头像 名称")
    @PostMapping("/addFactoryImge")
    @ResponseBody
    public ResultCode addFactoryImge(@RequestHeader("token")String token, FactoryName factoryName, @Ignore ResultCode resultCode){
        resultCode = factoryPortraitService.addFactoryImge(token,factoryName);
        return resultCode;
    }

    /**
     * @author: create by hjh
     * @time: 2020/3/18 11:37
     * @Description: 工厂头像链接 等参数获取
     * @param token 登录信息
     * @param resultCode 忽略，用作返回
     * @return: com.xinmintx.factory.common.ResultCode
     */
    @ApiOperation("工厂头像链接")
    @PostMapping("queryFactoryPortraitByFactoryId")
    public ResultCode queryFactoryPortraitByFactoryId(@RequestHeader("token")String token , @Ignore ResultCode resultCode){
        resultCode.setData(factoryPortraitService.queryFactoryPortraitByFactoryId(token));
        return resultCode;
    }

}
