package com.xinmintx.factory.api.service;


import com.xinmintx.factory.api.pojo.Factory;

public interface VenderLoginService {
    Factory venderLogin(String phone);

    String sendcode(String phone);

}

