package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.Merchant;
import com.xinmintx.agent.model.User;

import java.util.List;

public interface MerchantService {

    ResultCode addMerchant(User user, Merchant merchant);

    List<Merchant> selectMerchantByUserid(int userId);
}
