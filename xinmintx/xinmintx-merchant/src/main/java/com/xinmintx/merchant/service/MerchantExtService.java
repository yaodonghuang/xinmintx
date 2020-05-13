package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.dto.MerchantBindCardDTO;
import com.xinmintx.merchant.model.MerchantBankCard;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

public interface MerchantExtService {
    List<Map> getMyCommunity(String token);

    Map getByPhone(String phone);

    Map getInfo(String token);

    void rescission(String token, Integer communityId);

    Map getShopInfo(String token);

    void updateShopInfo(String token, String shopName, String shopAddress);

    void bindCard(String token,MerchantBindCardDTO merchantBindCardDTO);

    List<MerchantBankCard> getBankCardByMerchantId(String token);

}
