package com.xinmintx.factory.service;

import com.xinmintx.factory.model.Merchant;

public interface MerchantsLoginService {

    Merchant merchantLogin(String phone);

    String sendcode(String phone);
}
