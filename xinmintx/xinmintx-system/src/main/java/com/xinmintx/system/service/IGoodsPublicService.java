package com.xinmintx.system.service;

import com.xinmintx.system.domain.GoodsPublic;
import java.util.List;

/**
 * 商品详情公共部分配置Service接口
 * 
 * @author xinmintx
 * @date 2020-02-24
 */
public interface IGoodsPublicService 
{
    /**
     * 查询商品详情公共部分配置
     * 
     * @param id 商品详情公共部分配置ID
     * @return 商品详情公共部分配置
     */
    public GoodsPublic selectGoodsPublicById(Long id);

    /**
     * 查询商品详情公共部分配置列表
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 商品详情公共部分配置集合
     */
    public List<GoodsPublic> selectGoodsPublicList(GoodsPublic goodsPublic);

    /**
     * 新增商品详情公共部分配置
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 结果
     */
    public int insertGoodsPublic(GoodsPublic goodsPublic);

    /**
     * 修改商品详情公共部分配置
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 结果
     */
    public int updateGoodsPublic(GoodsPublic goodsPublic);

    /**
     * 批量删除商品详情公共部分配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsPublicByIds(String ids);

    /**
     * 删除商品详情公共部分配置信息
     * 
     * @param id 商品详情公共部分配置ID
     * @return 结果
     */
    public int deleteGoodsPublicById(Long id);
}
