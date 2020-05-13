package com.xinmintx.community.mapper;

import com.xinmintx.community.model.CommunityVote;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CommunityVoteMapper {
    Integer ifBelongToCommunity(@Param("communityId") Long communityId, @Param("memberId")Integer memberId);

    Integer ifBeginVote(@Param("communityId") Long communityId, @Param("memberId")Integer memberId);

    int deleteAllVoteInfo(@Param("communityId") Long communityId);

    Integer ifComplete(@Param("communityId") Long communityId, @Param("ifComplete") Byte ifComplete);

    int updateMemberVoteInfo(@Param("communityId") Long communityId, @Param("memberId") Integer memberId, @Param("ifVote") Byte ifVote);

    int insert(CommunityVote communityVote);

    Integer ifMemberVote(@Param("communityId") Long communityId, @Param("memberId")Integer memberId, @Param("ifVote")Byte ifVote);

    Map<String, Object> getVoteInfo(@Param("communityId") Long communityId);

    int updateNumOfVote(CommunityVote communityVote);

    Integer getCountByCommunityId(@Param("communityId")Long communityId, @Param("ifVote")Byte ifVote);

    Integer getAgreeNum(@Param("id")Long id);

    int updatePresident(@Param("communityId")Long communityId);

    Long getMemberIdByCommunityId(@Param("communityId")Long communityId);

    Integer ifExistsConsigneeAddress(@Param("communityId")Long communityId);
}
