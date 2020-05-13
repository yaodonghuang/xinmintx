package com.xinmintx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/agent/dataGram")
public class dataGramController {
    /**
     * 跳转页面资料包
     * @return
     */
    @GetMapping("/dataGram")
    public String dataGram(){
        return "data";
    }

}
