package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.dto.DeliveryVo;
import com.xinmintx.merchant.model.MerchantDelivery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantDeliveryMapper {

    void add(@Param("merchantDelivery") MerchantDelivery merchantDelivery);

    void updateById(@Param("merchantDelivery") MerchantDelivery merchantDelivery);

    List<DeliveryVo> getByMerchantIdAndCommunityId(@Param("merchantId")Integer merchantId, @Param("communityId")Integer communityId);
}
