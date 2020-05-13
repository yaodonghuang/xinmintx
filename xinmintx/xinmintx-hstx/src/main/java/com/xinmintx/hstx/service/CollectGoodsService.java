package com.xinmintx.hstx.service;

import java.util.List;

public interface CollectGoodsService {

    boolean insertCollectGoods (Integer memberId,Integer goodsId,Integer type);

    List getCollectGoodsList(Integer memberId);

    void deleteCollectGoods(Integer[] id);

    boolean checkCollectGoods(Integer memberId,Integer goodsId,Integer type);
}