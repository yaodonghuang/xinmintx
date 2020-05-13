package com.xinmintx.merchant.service;


import com.xinmintx.merchant.model.Merchant;

public interface MerchantsLoginService {

    Merchant merchantLogin(String phone);

    String sendcode(String phone);
}
