package com.xinmintx.merchant.service;



import com.xinmintx.merchant.model.Gift;

import java.util.List;


public interface ExclusiveBoardService {
    List queryByClassify(String category);

    List queryByShop(int id);


    int addMerchantsGift(Gift gift);

    int delMerchantsGift(long giftId);

    int upMerchantsGift( Gift gift);
    List queryAllShop(String regionCode);

    List queryById(String giftId);
}

