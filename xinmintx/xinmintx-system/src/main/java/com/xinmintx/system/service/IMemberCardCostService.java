package com.xinmintx.system.service;

import com.xinmintx.system.domain.MemberCardCost;
import java.util.List;

/**
 * 会员卡费用Service接口
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
public interface IMemberCardCostService 
{
    /**
     * 查询会员卡费用
     * 
     * @param id 会员卡费用ID
     * @return 会员卡费用
     */
    public MemberCardCost selectMemberCardCostById(Long id);

    /**
     * 查询会员卡费用列表
     * 
     * @param memberCardCost 会员卡费用
     * @return 会员卡费用集合
     */
    public List<MemberCardCost> selectMemberCardCostList(MemberCardCost memberCardCost);

    /**
     * 新增会员卡费用
     * 
     * @param memberCardCost 会员卡费用
     * @return 结果
     */
    public int insertMemberCardCost(MemberCardCost memberCardCost);

    /**
     * 修改会员卡费用
     * 
     * @param memberCardCost 会员卡费用
     * @return 结果
     */
    public int updateMemberCardCost(MemberCardCost memberCardCost);

    /**
     * 批量删除会员卡费用
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberCardCostByIds(String ids);

    /**
     * 删除会员卡费用信息
     * 
     * @param id 会员卡费用ID
     * @return 结果
     */
    public int deleteMemberCardCostById(Long id);
}
