package com.xinmintx.factory.service;

import com.xinmintx.factory.model.Gift;
import com.xinmintx.factory.model.StoreInformation;

import java.util.List;
import java.util.Map;


public interface ExclusiveBoardService {
    List queryByClassify(String category);

    List queryByShop(int id);


    int addMerchantsGift(Gift gift);

    int delMerchantsGift(long giftId);

    int upMerchantsGift( Gift gift);
    List queryAllShop(String regionCode,String giftGroup);

    List queryById(String giftId);
}
