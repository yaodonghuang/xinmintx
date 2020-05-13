package com.xinmintx.community.service;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.MerchantGoods;
import com.xinmintx.community.model.PayNotify;

import java.util.List;

/**
 * 社区订单service
 */
public interface CommunityOrderService {
    ResultCode getGoodsList(Long id, Integer type, String name);

    ResultCode orderCreation(List<MerchantGoods> mgList, String token);

    ResultCode orderPayNotify(PayNotify payNotify);

    ResultCode getFeeInfo(List<MerchantGoods> mgList, String token);

    ResultCode payInfoOne(Integer id, Integer memberId, Long communityId);
}
