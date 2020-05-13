package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.util.OpenIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/17 0017
 * @time: 上午 11:50
 * @Description:
 */
@RestController
@RequestMapping("/hs/msg")
public class MessageController {

    @Autowired
    private OpenIdUtils openIdUtils;

    @GetMapping("/sendMessage")
    public String sendMessage(String message) {
        openIdUtils.sendMessage("osGY-w6uGse-E1RkOBx-jRYDvTm4", message);
        return "success";
    }

    @GetMapping("/sendMessageTemplate")
    public String sendMessageTemplate() {
        openIdUtils.sendMessageByTemplate(null, null, null);
        return "success";
    }
}
