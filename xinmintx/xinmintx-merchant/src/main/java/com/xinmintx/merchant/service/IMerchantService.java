package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.MerchantGoods;
import com.xinmintx.merchant.model.PoboNotify;

import java.util.List;

/**
 * 礼包service
 */
public interface IMerchantService {
    // 商户核销接口
    ResultCode writeOff(Integer memberId, String uuid, String token);

    // 商户待核销列表
    ResultCode waitWriteOff(String token);

    // 商户待核销详情列表
    ResultCode waitWriteOffDetail(Long giftId);

    // 商户已核销列表
    ResultCode alreadyWriteOff(String token);

    ResultCode createGoods(MerchantGoods merchantGoods,String token);

    ResultCode queryMerchantGoods(Long goodsId);

    ResultCode upMerchantGoods(MerchantGoods merchantGoods);

    ResultCode queryMerchantGoodsList(String token);

    ResultCode upGoodsState(Long goodsId, Integer state);

    ResultCode queryGoodsListByState(String token, Integer state);

    ResultCode delGoods(Long goodsId);

    ResultCode getCommunityOrderList(Integer communityId, String token,Integer type);

    ResultCode getCommunityOrder(String token,Integer type);

    ResultCode upacceptOrder(String token, Integer communityId,Integer acceptOrder);

    ResultCode upOrderStatu(Integer orderId, Integer orderStatu);

    ResultCode orderHistory(String token,String times);

    ResultCode historicalOrderDetails(String token, String times, Integer communityId);

    ResultCode queryBank(String bank);
    ResultCode refuseOrder(Integer communityId, String token);

    ResultCode paymentOnBehalfOfNotify(PoboNotify pn);

    ResultCode queryGoodsDetail(Long communityId, Long goodsId);

    ResultCode totalOrder(String token);

    ResultCode getOrderListByStatus(Integer communityId, String token, Integer type, Integer orderStatus);

    ResultCode queryMerchantAuditStatus(String token);
}
