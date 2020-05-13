package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.Factory;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.FactoryServce;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/agent/plant")
public class PlantController {

    @Resource
    private FactoryServce factoryServce;

    /**
     * 跳转工厂
     *
     * @return
     */
    @GetMapping("/addPlant")
    public String addAgent() {
        return "plant-add";
    }

    /**
     * 添加工厂
     *
     * @param factory
     * @param request
     * @return
     */
    @PostMapping("/addFactory")
    @ResponseBody
    public ResultCode addFactory(Factory factory, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        factory.setReferrerid(user.getId());
        factory.setCreateTime(new Date());
        factory.setPhone(factory.getPhoneId());
        factory.setName(factory.getPersonname());
        int i = factoryServce.addFactory(factory);
        ResultCode resultCode = new ResultCode();
        if (i > 0) {
            resultCode.setCode(200);
        } else {
            resultCode.setCode(500);
        }
        return resultCode;
    }

}
