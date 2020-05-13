package com.xinmintx.community.service;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.dto.CommunityAddMerchantDTO;
import com.xinmintx.community.model.Community;

import java.util.List;
import java.util.Map;

/**
 * 社区service
 */
public interface CommunityService {
    ResultCode createCommunity(Community community, String token);

    ResultCode joinCommunity(Community community, String token);

    ResultCode changePresident(Long communityId, Integer memberId, String token);

    ResultCode saveRegion(String regionCode, String token);

    List<Map<String,Object>> getByCommunityId(Integer communityId, String merchantCategory);

    void addMerchant(CommunityAddMerchantDTO communityAddMerchantDTO);

    ResultCode changeMemberName(String memberName, Long id, String token);

    String sendcode(String phone);

    ResultCode getMerchantList(Long communityId, String merchantCategory);

    ResultCode queryGoodsDetail(Long communityId, Long goodsId);

    ResultCode fuzzyQueryCommunity(String token, String name);

    ResultCode getSignMerchantInfo(Long communityId, String token);
}