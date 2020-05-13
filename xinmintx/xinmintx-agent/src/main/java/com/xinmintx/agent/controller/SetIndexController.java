package com.xinmintx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent/setIndex")
public class SetIndexController {

    /**
     * 跳转设置页面
     * @return
     */
    @GetMapping("/setIndex")
    public String setIndex(){
        return "setIndex";
    }
}
