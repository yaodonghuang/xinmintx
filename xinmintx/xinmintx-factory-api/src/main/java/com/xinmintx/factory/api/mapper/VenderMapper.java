package com.xinmintx.factory.api.mapper;

import com.xinmintx.factory.api.pojo.Factory;
import com.xinmintx.factory.api.pojo.GoodsOrder;
import com.xinmintx.factory.api.pojo.VenderOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VenderMapper {
    Factory selectByTel(String phone);

    void updateBytel(@Param("vender") Factory vender);

    void addBytel(Factory factory);
    Factory queryByToken(String token);

    int upStatu(@Param("orderNum") String orderNum, @Param("statu") int status);

    Integer selectIphone(@Param("phone") String phone);

    GoodsOrder queryByOrderId(String orderId);

    int upMainOrder(@Param("orderNum") String orderNum, @Param("statu") int statu);

    void upSonStatu(@Param("order") long order, @Param("statu") int statu);

    int updateSonStatusList(@Param("list") List<Long> idList, @Param("statu") int statu);

    VenderOrder queryById(String orderNum);
}
