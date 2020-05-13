package com.xinmintx.community.mapper;

import com.xinmintx.community.model.CommunityVotingRescissionNotice;
import com.xinmintx.community.model.Rescission;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CommunityVotingRescissionNoticeMapper {

    boolean isVotingByCommunityIdAndType(@Param("communityId")Integer communityId, @Param("type")Integer type);

    void insert(@Param("communityVotingRescissionNotice")CommunityVotingRescissionNotice communityVotingRescissionNotice);

    void deleteByCommunityIdAndMerchantIdAndType(@Param("communityId")Integer communityId, @Param("merchantId")Integer merchantId, @Param("type")Integer type);
}
