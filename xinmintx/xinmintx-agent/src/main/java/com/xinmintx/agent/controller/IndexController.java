package com.xinmintx.agent.controller;

import com.xinmintx.agent.annotation.DisableAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/14 0014
 * @time: 下午 18:51
 * @Description:
 */
@Controller
public class IndexController {

    @DisableAuth
    @GetMapping("/")
    public String index() {
        return "login/login";
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @DisableAuth
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }
}
