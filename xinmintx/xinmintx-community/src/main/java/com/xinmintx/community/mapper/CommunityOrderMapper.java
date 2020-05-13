package com.xinmintx.community.mapper;

import com.xinmintx.community.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CommunityOrderMapper {
    int insertOrder(GoodsOrder goodsOrder);

    int insertOrderDetailList(@Param("list") List<GoodsOrderDetail> godList);

    int updateTotalAmount(@Param("id") Integer id, @Param("totalPrice") BigDecimal totalPrice);

    MerchantGoods getGoodsInfoById(@Param("id") Long id);

    String getAddressById(@Param("id") Integer id);

    Map<String, String> getOrderInfo(@Param("id") Integer id);

    List<String> getProDesc(@Param("orderId") Integer id);

    BigDecimal getManyAmount(@Param("list") List<Integer> ids);

    int insertUorderInfo(uOrder uorder);

    int updateUOrderIdInIds(@Param("uOrderId") Integer uOrderId, @Param("list") List<Integer> ids);

    BigDecimal getDeputyCost(@Param("communityId") Long communityId, @Param("type") Integer type);

    List<Long> getMerchantIds(@Param("list") List<Integer> ids);

    BigDecimal getMerchantDeliveryFee(@Param("list") List<Long> merchantList, @Param("communityId") Long communityId, @Param("type") Integer type);

    uOrder getUorderById(@Param("orderNo") String orderNo);

    int updateUOrder(uOrder uorder);

    List<GoodsOrder> getOrderIdByOrderNum(@Param("orderNo") String orderNo);

    int updateOrderAndDetailState(@Param("orderId") Integer orderId, @Param("orderState") Integer orderState, @Param("ifPay") Integer ifPay);

    Map<String,String> getMemberSendFee(@Param("communityId") Long communityId, @Param("type") Integer type);

    int updateOrderInfoOfPay(@Param("list") List<Integer> id, @Param("str") String str);

    Integer ifDeputyHelp(@Param("communityId") Long communityId, @Param("memberId") Integer memberId);

    String getDeputyInfo(@Param("orderId") Integer orderId);

    int updateMemberMoney(@Param("memberId") String memberId, @Param("money") String money);

    BigDecimal getFreezingAmount(@Param("memberId") String memberId);

    int insertLogs(MemberAmountLog log);

    List<GoodsOrder> getOrderList(@Param("communityId") Long communityId, @Param("memberId") Integer memberId);

    Integer ifExistsOrderNum(@Param("orderNum") String orderNum);

    int updateOrderNumById(@Param("orderId") Integer orderId, @Param("orderNum") String orderNum);

    int updateMerchantMoney(@Param("merchantId") Long merchantId, @Param("freezingAmount") BigDecimal freezingAmount);

    int insertMerchantLogs(MerchantAmountLog log);

    BigDecimal getBalance(@Param("merchantId") Long merchantId);

    String getPhoneOfDeputy(@Param("communityId") Long communityId, @Param("type") Integer type);

    int updateOrderInfoOfConsignee(@Param("list") List<Integer> id, @Param("str") String str);

    String getUserInfo(@Param("orderId") Integer orderId);

    UserAccount getUserAccount(@Param("userId") String userId);

    int insertUserAccount(UserAccount userAccount);

    int updateUserAccount(UserAccount userAccount);

    int insertUserAccountRecord(UserAccountRecord userAccountRecord);

    Integer ifAutoPrint(@Param("orderId") Integer orderId);
}
