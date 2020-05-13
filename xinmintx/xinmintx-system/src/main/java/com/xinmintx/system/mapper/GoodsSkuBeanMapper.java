package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsSku;
import com.xinmintx.system.domain.GoodsSkuBean;

import java.util.List;

public interface GoodsSkuBeanMapper {

    List<GoodsSku> selectSku(String goodsId);
    List<GoodsSku> selectSkuById(String id);
}
