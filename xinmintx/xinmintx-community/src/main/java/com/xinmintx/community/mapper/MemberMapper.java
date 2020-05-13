package com.xinmintx.community.mapper;

import com.xinmintx.community.model.CommunityDeputy;
import com.xinmintx.community.model.Member;

public interface MemberMapper {

    Member selectByPhone(String phone);
}
