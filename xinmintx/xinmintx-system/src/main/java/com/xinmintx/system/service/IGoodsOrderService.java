package com.xinmintx.system.service;

import com.xinmintx.system.domain.GoodsOrder;
import com.xinmintx.system.domain.GoodsOrderDetail;

import java.util.List;

/**
 * 商品订单Service接口
 * 
 * @author xinmintx
 * @date 2019-12-13
 */
public interface IGoodsOrderService 
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
     * 批量删除商品订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsOrderByIds(String ids);

    /**
     * 删除商品订单信息
     * 
     * @param id 商品订单ID
     * @return 结果
     */
    public int deleteGoodsOrderById(Long id);


    /**
     * 获取订单详情
     * @param id  订单主表id
     * @return
     */
    List<GoodsOrderDetail> selectGoodsDetail(Integer id);


}
