package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.OrderMapper;
import com.xinmintx.system.domain.Order;
import com.xinmintx.system.service.IOrderService;
import com.xinmintx.common.core.text.Convert;

/**
 * 订单Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        order.setUpdateTime(DateUtils.getNowDate());
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids)
    {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }
}
