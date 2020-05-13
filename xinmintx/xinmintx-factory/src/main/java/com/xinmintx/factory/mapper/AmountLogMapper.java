package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.MemberAmountLog;
import com.xinmintx.factory.model.MerchantAmountLog;
import com.xinmintx.factory.model.UserAccountRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AmountLogMapper {
    int updateMemberCash(@Param("cost") String cost, @Param("cashAmount") BigDecimal cashAmount, @Param("memberId") String memberId);

    int updateMerchantCash(@Param("cost") String cost, @Param("merchantId") String merchantId);

    int insertLogs(@Param("list") List<MemberAmountLog> list);

    int insertMerchantLogs(@Param("list") List<MerchantAmountLog> list);

    BigDecimal getMemberCashAmount(@Param("memberId") String memberId);

    BigDecimal getMerchantCashAmount(@Param("merchantId") String merchantId);

    BigDecimal getMerchantFreezAmount(@Param("merchantId") String merchantId);

    BigDecimal getMerchantServiceCharge(@Param("merchantId") String merchantId);

    int updateMerchantCashAmount(@Param("cost") BigDecimal cost, @Param("merchantId") String merchantId);

    BigDecimal getUserCashAmount(@Param("userId") String userId);

    int updateUserCashAmount(@Param("userId") String userId, @Param("money") BigDecimal money, @Param("finalCost") BigDecimal finalCost);

    int insertAccountRecord(UserAccountRecord userAccountRecord);

    Integer getUserAccountId(@Param("userId") String userId);
}
