package com.xinmintx.system.service;

import com.xinmintx.system.domain.GoodsPriceType;
import java.util.List;

/**
 * 用户商品价格分类Service接口
 * 
 * @author xinmintx
 * @date 2020-02-13
 */
public interface IGoodsPriceTypeService 
{
    /**
     * 查询用户商品价格分类
     * 
     * @param id 用户商品价格分类ID
     * @return 用户商品价格分类
     */
    public GoodsPriceType selectGoodsPriceTypeById(Long id);

    /**
     * 查询用户商品价格分类列表
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 用户商品价格分类集合
     */
    public List<GoodsPriceType> selectGoodsPriceTypeList(GoodsPriceType goodsPriceType);

    /**
     * 新增用户商品价格分类
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 结果
     */
    public int insertGoodsPriceType(GoodsPriceType goodsPriceType);

    /**
     * 修改用户商品价格分类
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 结果
     */
    public int updateGoodsPriceType(GoodsPriceType goodsPriceType);

    /**
     * 批量删除用户商品价格分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsPriceTypeByIds(String ids);

    /**
     * 删除用户商品价格分类信息
     * 
     * @param id 用户商品价格分类ID
     * @return 结果
     */
    public int deleteGoodsPriceTypeById(Long id);
}
