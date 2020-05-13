package com.xinmintx.community.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MerchantMapper {

    Map getByPhone(@Param("phone")String phone);

}
