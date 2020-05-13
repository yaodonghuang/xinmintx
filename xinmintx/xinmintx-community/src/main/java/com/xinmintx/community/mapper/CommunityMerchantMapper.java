package com.xinmintx.community.mapper;

import com.xinmintx.community.model.CommunityMerchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommunityMerchantMapper {
    void add(@Param("communityMerchant")CommunityMerchant communityMerchant);

    void deleteByCommunityIdAndMerchantIdAndType(@Param("communityId")Integer communityId, @Param("merchantId")Integer merchantId,@Param("type")Integer type);


    List<Map> getCommunitysByMerchantId(@Param("merchantId")Integer merchantId);

    boolean isExistByCommunityAndType(@Param("communityId")Integer communityId, @Param("type")int type);
}
