package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.dto.MerchantDeliveryVo;
import com.xinmintx.merchant.model.CommunityMerchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommunityMerchantMapper {
    void add(@Param("communityMerchant") CommunityMerchant communityMerchant);

    void deleteByCommunityIdAndMerchantId(@Param("communityId") Integer communityId, @Param("merchantId") Integer merchantId);


    List<Map> getCommunitysByMerchantId(@Param("merchantId") Integer merchantId);


    String queryCommunityNameByCommunityId(@Param("communityId") Long communityId);
}
