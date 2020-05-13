package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberCardCostMapper;
import com.xinmintx.system.domain.MemberCardCost;
import com.xinmintx.system.service.IMemberCardCostService;
import com.xinmintx.common.core.text.Convert;

/**
 * 会员卡费用Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
@Service
public class MemberCardCostServiceImpl implements IMemberCardCostService 
{
    @Autowired
    private MemberCardCostMapper memberCardCostMapper;

    /**
     * 查询会员卡费用
     * 
     * @param id 会员卡费用ID
     * @return 会员卡费用
     */
    @Override
    public MemberCardCost selectMemberCardCostById(Long id)
    {
        return memberCardCostMapper.selectMemberCardCostById(id);
    }

    /**
     * 查询会员卡费用列表
     * 
     * @param memberCardCost 会员卡费用
     * @return 会员卡费用
     */
    @Override
    public List<MemberCardCost> selectMemberCardCostList(MemberCardCost memberCardCost)
    {
        return memberCardCostMapper.selectMemberCardCostList(memberCardCost);
    }

    /**
     * 新增会员卡费用
     * 
     * @param memberCardCost 会员卡费用
     * @return 结果
     */
    @Override
    public int insertMemberCardCost(MemberCardCost memberCardCost)
    {
        return memberCardCostMapper.insertMemberCardCost(memberCardCost);
    }

    /**
     * 修改会员卡费用
     * 
     * @param memberCardCost 会员卡费用
     * @return 结果
     */
    @Override
    public int updateMemberCardCost(MemberCardCost memberCardCost)
    {
        return memberCardCostMapper.updateMemberCardCost(memberCardCost);
    }

    /**
     * 删除会员卡费用对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberCardCostByIds(String ids)
    {
        return memberCardCostMapper.deleteMemberCardCostByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员卡费用信息
     * 
     * @param id 会员卡费用ID
     * @return 结果
     */
    @Override
    public int deleteMemberCardCostById(Long id)
    {
        return memberCardCostMapper.deleteMemberCardCostById(id);
    }
}
