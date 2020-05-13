package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.MemberAmountLog;
import com.xinmintx.merchant.model.Merchant;
import com.xinmintx.merchant.model.MerchantAmountLog;
import com.xinmintx.merchant.model.PoboNotify;

import java.util.List;

public interface WalletService {
    Merchant queryBalance(String token);

    List<PoboNotify> queryWithdrawDeposit(String token);

    ResultCode factoryGetMoney(Merchant merchant);

    List<MerchantAmountLog> queryTransaction(String token, Integer type);
}
