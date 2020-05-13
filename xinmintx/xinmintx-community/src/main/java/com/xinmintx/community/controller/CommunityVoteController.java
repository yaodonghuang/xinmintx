package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.CommunityVote;
import com.xinmintx.community.service.CommunityVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 社区投票controller
 * @author hyd
 */
@RestController
@RequestMapping("/community/vote")
@Transactional
public class CommunityVoteController {
    @Autowired
    private CommunityVoteService communityVoteService;

    /**
     * 发起投票
     * @param communityVote
     * @param token
     * @return
     */
    @RequestMapping(value = "/beginVote", method = RequestMethod.POST)
    public ResultCode beginVote(@RequestBody CommunityVote communityVote, @RequestHeader String token) {
        ResultCode resultCode = communityVoteService.beginVote(communityVote, token);
        return resultCode;
    }

    /**
     * 用户获取投票信息
     * @param communityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/voteInfo", method = RequestMethod.GET)
    public ResultCode voteInfo(@RequestParam("communityId") Long communityId, @RequestHeader String token) {
        ResultCode resultCode = communityVoteService.voteInfo(communityId, token);
        return resultCode;
    }

    /**
     * 用户投票接口
     * @param communityVote
     * @param token
     * @return
     */
    @RequestMapping(value = "/memberVote", method = RequestMethod.POST)
    public ResultCode memberVote(@RequestBody CommunityVote communityVote, @RequestHeader String token) {
        ResultCode resultCode = communityVoteService.memberVote(communityVote, token);
        return resultCode;
    }
}
