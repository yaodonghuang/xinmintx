package com.xinmintx.factory.service;

import com.xinmintx.factory.model.po.GoodsType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/18
 * @time: 14:33
 * @Description:
 */
public interface GoodsTypeService {

    /**
     * @author: create by hjh
     * @time: 2020/3/18 14:46
     * @Description: 获取商品分类
     * @param token
     * @return: java.util.List<com.xinmintx.factory.model.po.GoodsType>
     */
    List<GoodsType> queryGoodsType(String token);
}
