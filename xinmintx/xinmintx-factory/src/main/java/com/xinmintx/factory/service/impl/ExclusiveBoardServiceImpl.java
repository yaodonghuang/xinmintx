package com.xinmintx.factory.service.impl;

import com.xinmintx.factory.mapper.ExclusiveBoardMapper;
import com.xinmintx.factory.model.Gift;
import com.xinmintx.factory.model.StoreInformation;
import com.xinmintx.factory.service.ExclusiveBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: com.xinmintx.api.service.impl.ExclusiveBoardServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/16 18:03
 * @Version: v1.0
 */
@Transactional
@Service
public class ExclusiveBoardServiceImpl implements ExclusiveBoardService {
    @Autowired
    private ExclusiveBoardMapper exclusiveBoardMapper;
    @Override
    public List queryByClassify(String category) {
        List<StoreInformation> list = exclusiveBoardMapper.queryByClassify(category);
        List list1 = new ArrayList<>();
        if (list!=null){
            for (StoreInformation sf :list){
                Integer shopId =sf.getId();
                List<StoreInformation> giftList=exclusiveBoardMapper.queryByShopId(shopId);
                if (giftList!=null && giftList.size() > 0){
                    list1.add(sf);
                }
            }
        }
        return list1;
    }

    @Override
    public List<StoreInformation> queryByShop(int id) {
        List<StoreInformation> list =exclusiveBoardMapper.queryByShop(id);
        return list;
    }

    @Override
    public int addMerchantsGift(Gift gift) {
        int i = exclusiveBoardMapper.addMerchantsGift(gift);
        return i;
    }

    @Override
    public int delMerchantsGift(long giftId) {
        int i  =exclusiveBoardMapper.delMerchantsGift(giftId);
        return i;
    }

    @Override
    public int upMerchantsGift( Gift gift) {
        int i =exclusiveBoardMapper.upMerchantsGift(gift);
        return i;
    }

    @Override
    public List queryAllShop(@RequestParam("regionCode") String regionCode,@RequestParam("giftGroup") String giftGroup) {
        List<StoreInformation>list = exclusiveBoardMapper.queryAllShop(regionCode, giftGroup);
        List<StoreInformation> list1 = new ArrayList<>();
        if (list!=null){
            for (StoreInformation sf :list){
                Integer shopId =sf.getId();
                List<StoreInformation> giftList=exclusiveBoardMapper.queryByShopId(shopId);
                if (giftList!=null && giftList.size() > 0){
//                    StoreInformation storeInformation =exclusiveBoardMapper.queryById(shopId);
                    list1.add(sf);
                }
            }
        }
        return list1;
    }

    @Override
    public List queryById(String giftId) {
        List<StoreInformation> list = exclusiveBoardMapper.queryByGiftId(giftId);
        return list;
    }


}
