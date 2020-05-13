package com.xinmintx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent/area")
public class AreaController {
    /**
     * 跳转地区
     *
     * @return
     */
    @GetMapping("/addArea")
    public String addAgent() {
        return "area";
    }
}
