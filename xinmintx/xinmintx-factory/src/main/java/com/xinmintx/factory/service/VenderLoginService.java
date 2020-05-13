package com.xinmintx.factory.service;

import com.xinmintx.factory.model.Factory;

public interface VenderLoginService {
    Factory venderLogin(String phone);

    String sendcode(String phone);

}

