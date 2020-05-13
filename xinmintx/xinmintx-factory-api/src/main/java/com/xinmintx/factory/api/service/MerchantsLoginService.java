package com.xinmintx.factory.api.service;


import com.xinmintx.factory.api.pojo.Merchant;

public interface MerchantsLoginService {

    Merchant merchantLogin(String phone);

    String sendcode(String phone);
}
