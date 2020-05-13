package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.model.MerchantBankCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantBankCardMapper {

    void insert(@Param("merchantBankCard") MerchantBankCard merchantBankCard);

    List<MerchantBankCard> queryByMerchantId(@Param("merchantId") Integer merchantId);

    int getByCardNum(@Param("cardNum") String cardNum);
}
