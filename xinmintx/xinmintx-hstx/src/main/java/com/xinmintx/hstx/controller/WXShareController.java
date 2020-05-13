package com.xinmintx.hstx.controller;


import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.service.WXShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName:.微信分享链接
 * @author:sw
 * @Date:2019/12/5：10:15
 * @developerKits： win 10     jdk1.8
 */
@RestController
@RequestMapping(value = "/wxShare/link")
public class WXShareController {

    @Autowired
    private WXShareService wxShareService;

    @DisableAuth
    @PostMapping("/shareLink")
    public Map<String,String> WXShareLink(String url){
        return wxShareService.ShareLink(url);
    }
}
