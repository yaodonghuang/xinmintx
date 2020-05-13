package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.service.CommunityMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 社区消息controller
 *
 * @author hyd
 */
@RestController
@RequestMapping("/community/message")
@Transactional
public class CommunityMessageController {
    @Autowired
    private CommunityMessageService communityMessageService;

    @RequestMapping(value = "/messageList", method = RequestMethod.GET)
    public ResultCode messageList(@RequestParam(value = "communityId") Long id, @RequestHeader("token") String token) {
        ResultCode resultCode = communityMessageService.messageList(id, token);
        return resultCode;
    }
}
