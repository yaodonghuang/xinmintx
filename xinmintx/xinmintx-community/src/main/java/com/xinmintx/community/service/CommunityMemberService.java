package com.xinmintx.community.service;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.Community;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityMemberService {
    ResultCode queryCommunity(String token);

    ResultCode myCommunity(String token);

    ResultCode upNotice(String token , String icon, String notice, Long id);

    ResultCode queryCommunityById(String token,Integer id);

    ResultCode queryProprieter(String token, Integer id);

    ResultCode upAssist(Integer communityId, String token, Integer deputyHelp);

    ResultCode getCommunityMemberList(Integer communityId);

    ResultCode outOfCommunity(Integer communityId, String token);
}
