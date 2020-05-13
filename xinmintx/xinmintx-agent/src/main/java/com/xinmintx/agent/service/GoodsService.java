package com.xinmintx.agent.service;


import com.xinmintx.agent.model.Commodity;
import com.xinmintx.agent.model.CommodityExt;
import com.xinmintx.agent.model.CommodityKind;
import com.xinmintx.agent.model.CommodityTypology;

import java.util.List;

public interface GoodsService {

    Commodity showGoods(int id);

    List<CommodityExt> getGoods(String name);

    CommodityExt getGoodById(Integer id);

    String[] getPictures(Integer id);

    String getParameter(Integer id);

    List<CommodityExt> getType(Integer id);

    List<CommodityKind> getGoodType(Integer id);

    List<CommodityTypology> getTypeById(Integer id);

    List<CommodityTypology> showTypeById(Integer id);
}
