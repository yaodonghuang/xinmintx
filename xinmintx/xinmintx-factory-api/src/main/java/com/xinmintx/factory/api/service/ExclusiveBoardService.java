package com.xinmintx.factory.api.service;

import com.xinmintx.factory.api.pojo.Gift;

import java.util.List;


public interface ExclusiveBoardService {
    List queryByClassify(String category);

    List queryByShop(int id);


    int addMerchantsGift(Gift gift);

    int delMerchantsGift(long giftId);

    int upMerchantsGift(Gift gift);
    List queryAllShop(String regionCode, String giftGroup);

    List queryById(String giftId);
}
