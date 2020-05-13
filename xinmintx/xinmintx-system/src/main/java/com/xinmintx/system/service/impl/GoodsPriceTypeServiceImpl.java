package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodsPriceTypeMapper;
import com.xinmintx.system.domain.GoodsPriceType;
import com.xinmintx.system.service.IGoodsPriceTypeService;
import com.xinmintx.common.core.text.Convert;

/**
 * 用户商品价格分类Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-13
 */
@Service
public class GoodsPriceTypeServiceImpl implements IGoodsPriceTypeService 
{
    @Autowired
    private GoodsPriceTypeMapper goodsPriceTypeMapper;

    /**
     * 查询用户商品价格分类
     * 
     * @param id 用户商品价格分类ID
     * @return 用户商品价格分类
     */
    @Override
    public GoodsPriceType selectGoodsPriceTypeById(Long id)
    {
        return goodsPriceTypeMapper.selectGoodsPriceTypeById(id);
    }

    /**
     * 查询用户商品价格分类列表
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 用户商品价格分类
     */
    @Override
    public List<GoodsPriceType> selectGoodsPriceTypeList(GoodsPriceType goodsPriceType)
    {
        return goodsPriceTypeMapper.selectGoodsPriceTypeList(goodsPriceType);
    }

    /**
     * 新增用户商品价格分类
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 结果
     */
    @Override
    public int insertGoodsPriceType(GoodsPriceType goodsPriceType)
    {
        return goodsPriceTypeMapper.insertGoodsPriceType(goodsPriceType);
    }

    /**
     * 修改用户商品价格分类
     * 
     * @param goodsPriceType 用户商品价格分类
     * @return 结果
     */
    @Override
    public int updateGoodsPriceType(GoodsPriceType goodsPriceType)
    {
        return goodsPriceTypeMapper.updateGoodsPriceType(goodsPriceType);
    }

    /**
     * 删除用户商品价格分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPriceTypeByIds(String ids)
    {
        return goodsPriceTypeMapper.deleteGoodsPriceTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户商品价格分类信息
     * 
     * @param id 用户商品价格分类ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPriceTypeById(Long id)
    {
        return goodsPriceTypeMapper.deleteGoodsPriceTypeById(id);
    }
}
