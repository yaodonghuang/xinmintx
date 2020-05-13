package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.po.IndexModule;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IndexModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/6 0006
 * @time: 下午 17:16
 * @Description: 首页配置
 */
@RestController
@RequestMapping("/hs/index")
public class IndexModuleController {

    @Autowired
    private IndexModuleService indexModuleService;

    @PostMapping("/getIndexModule")
    public ResultCode getIndexModule(){
        ResultCode resultCode = new ResultCode();
        List<IndexModule> list = indexModuleService.indexModuleService();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(list);
        return resultCode;
    }


}
