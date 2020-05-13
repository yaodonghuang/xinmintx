package com.xinmintx.system.service;

import com.xinmintx.system.domain.BaseFeatredFirst;
import com.xinmintx.system.domain.GoodsFeaturedFirst;
import java.util.List;

/**
 * 首页推荐位置商品Service接口
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
public interface IGoodsFeaturedFirstService 
{
    /**
     * 查询首页推荐位置商品
     * 
     * @param id 首页推荐位置商品ID
     * @return 首页推荐位置商品
     */
    public BaseFeatredFirst selectGoodsFeaturedFirstById(Long id);

    /**
     * 查询首页推荐位置商品列表
     * 
     * @param baseFeatredFirst 首页推荐位置商品
     * @return 首页推荐位置商品集合
     */
    public List<BaseFeatredFirst> selectGoodsFeaturedFirstList(BaseFeatredFirst baseFeatredFirst);

    /**
     * 新增首页推荐位置商品
     * 
     * @param goodsFeaturedFirst 首页推荐位置商品
     * @return 结果
     */
    public int insertGoodsFeaturedFirst(GoodsFeaturedFirst goodsFeaturedFirst);

    /**
     * 修改首页推荐位置商品
     * 
     * @param goodsFeaturedFirst 首页推荐位置商品
     * @return 结果
     */
    public int updateGoodsFeaturedFirst(GoodsFeaturedFirst goodsFeaturedFirst);

    /**
     * 批量删除首页推荐位置商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsFeaturedFirstByIds(String ids);

    /**
     * 删除首页推荐位置商品信息
     * 
     * @param id 首页推荐位置商品ID
     * @return 结果
     */
    public int deleteGoodsFeaturedFirstById(Long id);
}
