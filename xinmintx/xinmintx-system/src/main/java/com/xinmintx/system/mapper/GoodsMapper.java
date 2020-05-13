package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Goods;
import java.util.List;
import java.util.Map;

/**
 * 商品Mapper接口
 * 
 * @author xinmintx
 * @date 2019-12-11
 */
public interface GoodsMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    public Goods selectGoodsById(Long id);

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsByIds(String[] ids);

    String selectById(Long id);

    Integer selectTypeId(Integer firstCode);

    String selectParameter(String id);
}
