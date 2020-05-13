package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.Gift;
import com.xinmintx.factory.model.StoreInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExclusiveBoardMapper {
    List<StoreInformation> queryByClassify(String category);

    List<StoreInformation> queryByShop(int id);

    int addMerchantsGift(Gift gift);

    int delMerchantsGift(long giftId);

    int upMerchantsGift( Gift gift);

    List<StoreInformation> queryAllShop(@Param("regionCode") String regionCode, @Param("giftGroup") String giftGroup);

    List<StoreInformation> queryByGiftId(String giftId);

    List<StoreInformation> queryByShopId(Integer shopId);

    StoreInformation queryById(Integer shopId);
}
