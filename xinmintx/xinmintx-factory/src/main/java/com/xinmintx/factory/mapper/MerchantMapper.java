package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.Merchant;
import org.apache.ibatis.annotations.Param;

public interface MerchantMapper {
    Merchant selectByTel(String phone);

    void updateBytel(@Param("merchant") Merchant merchant);

    void addBytel(@Param("merchants") Merchant merchants);

    Integer selectIphone(String phone);

    Merchant queryByToken(String token);
}
