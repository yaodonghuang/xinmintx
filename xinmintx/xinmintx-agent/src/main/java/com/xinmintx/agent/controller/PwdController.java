package com.xinmintx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent/pwd")
public class PwdController {
    /**
     * 跳转设置密码
     * @return
     */
    @GetMapping("/pwd")
    public String pwd(){
        return "password";
    }
}
