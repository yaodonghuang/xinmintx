package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.model.Gift;
import com.xinmintx.merchant.model.StoreInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExclusiveBoardMapper {
    List<StoreInformation> queryByClassify(String category);

    List<StoreInformation> queryByShop(int id);

    int addMerchantsGift(@Param("gift") Gift gift);

    int delMerchantsGift(long giftId);

    int upMerchantsGift(@Param("gift") Gift gift);

    List<StoreInformation> queryAllShop(@Param("regionCode") String regionCode);

    List<StoreInformation> queryByGiftId(String giftId);

    List<StoreInformation> queryByShopId(Integer shopId);

    StoreInformation queryById(Integer shopId);
}
