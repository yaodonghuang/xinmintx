package com.xinmintx.merchant.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MerchantExtMapper {

    Map getByPhone(@Param("phone") String phone);

    Map getInfo(@Param("token") String token);

    Integer getIdByToken(@Param("token") String token);


    Map getShopInfo(@Param("token") String token);

    void updateShopInfo(@Param("token") String token, @Param("shopName") String shopName, @Param("shopAddress") String shopAddress);
}
