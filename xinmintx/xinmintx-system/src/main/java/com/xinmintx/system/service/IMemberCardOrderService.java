package com.xinmintx.system.service;

import com.xinmintx.system.domain.MemberCardOrder;
import com.xinmintx.system.domain.MemberCardOrderDetail;

import java.util.List;

/**
 * 会员卡订单Service接口
 * 
 * @author xinmintx
 * @date 2020-02-15
 */
public interface IMemberCardOrderService 
{
    /**
     * 查询会员卡订单
     * 
     * @param id 会员卡订单ID
     * @return 会员卡订单
     */
    public MemberCardOrder selectMemberCardOrderById(Long id);

    /**
     * 查询会员卡订单列表
     * 
     * @param memberCardOrder 会员卡订单
     * @return 会员卡订单集合
     */
    public List<MemberCardOrder> selectMemberCardOrderList(MemberCardOrder memberCardOrder);

    /**
     * 新增会员卡订单
     * 
     * @param memberCardOrder 会员卡订单
     * @return 结果
     */
    public int insertMemberCardOrder(MemberCardOrder memberCardOrder);

    /**
     * 修改会员卡订单
     * 
     * @param memberCardOrder 会员卡订单
     * @return 结果
     */
    public int updateMemberCardOrder(MemberCardOrder memberCardOrder);

    /**
     * 批量删除会员卡订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberCardOrderByIds(String ids);

    /**
     * 删除会员卡订单信息
     * 
     * @param id 会员卡订单ID
     * @return 结果
     */
    public int deleteMemberCardOrderById(Long id);

    /**
     * 查询会员卡订单详情
     *
     * @param id
     * @return
     */
    List<MemberCardOrderDetail> selectMemberCardOrderDetail(int id);

    /**
     * 添加或修改快递单号
     *
     * @param id  id
     * @param courierNumber  快递号
     * @return
     */
    int insertCourierNumber(long id,String courierNumber);

}
