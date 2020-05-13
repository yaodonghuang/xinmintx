package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsOrder;
import java.util.List;

/**
 * 商品订单Mapper接口
 * 
 * @author xinmintx
 * @date 2019-12-13
 */
public interface GoodsOrderMapper 
{
    /**
     * 查询商品订单
     * 
     * @param id 商品订单ID
     * @return 商品订单
     */
    public GoodsOrder selectGoodsOrderById(Long id);

    /**
     * 查询商品订单列表
     * 
     * @param goodsOrder 商品订单
     * @return 商品订单集合
     */
    public List<GoodsOrder> selectGoodsOrderList(GoodsOrder goodsOrder);

    /**
     * 新增商品订单
     * 
     * @param goodsOrder 商品订单
     * @return 结果
     */
    public int insertGoodsOrder(GoodsOrder goodsOrder);

    /**
     * 修改商品订单
     * 
     * @param goodsOrder 商品订单
     * @return 结果
     */
    public int updateGoodsOrder(GoodsOrder goodsOrder);

    /**
     * 删除商品订单
     * 
     * @param id 商品订单ID
     * @return 结果
     */
    public int deleteGoodsOrderById(Long id);

    /**
     * 批量删除商品订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsOrderByIds(String[] ids);
}
