package com.xinmintx.community.service;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.CommunityVote;

/**
 * @author hyd
 */
public interface CommunityVoteService {
    ResultCode beginVote(CommunityVote communityVote, String token);

    ResultCode voteInfo(Long communityId, String token);

    ResultCode memberVote(CommunityVote communityVote, String token);
}
