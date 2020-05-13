package com.xinmintx.system.service;

import com.xinmintx.common.core.domain.Ztree;
import com.xinmintx.system.domain.GoodsType;
import com.xinmintx.system.domain.GoodsTypeBean;

import java.util.List;

/**
 * 商品分类Service接口
 * 
 * @author xinmintx
 * @date 2019-12-12
 */
public interface IGoodsTypeService 
{
    /**
     * 查询商品分类
     * 
     * @param id 商品分类ID
     * @return 商品分类
     */
    public GoodsType selectGoodsTypeById(Long id);

    /**
     * 查询商品分类列表
     * 
     * @param goodsType 商品分类
     * @return 商品分类集合
     */
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType);

    /**
     * 新增商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    public int insertGoodsType(GoodsType goodsType);

    /**
     * 修改商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    public int updateGoodsType(GoodsType goodsType);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsTypeByIds(String ids);

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类ID
     * @return 结果
     */
    public int deleteGoodsTypeById(Long id);

    List<Ztree> menuTreeData();

    String checkMenuNameUnique(GoodsType goodsType);

    int selectCountMenuByParentId(Long id);
}
