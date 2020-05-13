package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.MemberCardOrder;
import java.util.List;

/**
 * 会员卡订单Mapper接口
 * 
 * @author xinmintx
 * @date 2020-02-15
 */
public interface MemberCardOrderMapper 
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
     * 删除会员卡订单
     * 
     * @param id 会员卡订单ID
     * @return 结果
     */
    public int deleteMemberCardOrderById(Long id);

    /**
     * 批量删除会员卡订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberCardOrderByIds(String[] ids);
}
