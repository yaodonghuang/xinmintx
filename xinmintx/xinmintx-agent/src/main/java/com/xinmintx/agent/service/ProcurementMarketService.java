package com.xinmintx.agent.service;

import com.xinmintx.agent.model.CommodityType;
import com.xinmintx.agent.modelDTO.CommodityAndImage;

import java.util.List;

public interface ProcurementMarketService {

    List<CommodityAndImage> commodityAndImageList(Integer userId);

    List<CommodityType> selectShoppingType();

    List<CommodityAndImage> selectByTypeShopping(Integer id);

    List<CommodityAndImage> selectHotShopping(Integer id);
}
