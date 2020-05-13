package com.xinmintx.agent.controller;

import com.xinmintx.agent.service.ShareLinkService;
import com.xinmintx.agent.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName:.微信分享链接
 * @author:chf
 * @Date:2019/12/5：10:15
 * @developerKits： win 10     jdk1.8
 */
@RestController
@RequestMapping(value = "/wx/link")
public class ShareController {
    @Autowired
    private ShareLinkService shareService;

    @PostMapping("/shareLink")
    public Map<String,String> WXShareLink(String url){
        return shareService.ShareLink(url);
    }

}
