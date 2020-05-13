package com.xinmintx.community.mapper;

import com.xinmintx.community.model.Complaint;
import com.xinmintx.community.model.Rescission;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface RescissionMapper {
    void add(@Param("rescission") Rescission rescission);

    boolean existByMemberId(@Param("memberId")Integer memberId);

    Map<String,Object> getByCommunityIdAndMerchantIdAndType(@Param("communityId")Integer communityId, @Param("merchantId")Integer merchantId, @Param("type")Integer type);

}
