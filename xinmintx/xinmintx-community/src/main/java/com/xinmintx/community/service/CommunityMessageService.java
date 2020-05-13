package com.xinmintx.community.service;

import com.xinmintx.community.common.ResultCode;

/**
 *  社区消息
 * @author hyd
 */
public interface CommunityMessageService {
    ResultCode messageList(Long communityId, String token);
}
