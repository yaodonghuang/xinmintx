package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberBenefitMapper;
import com.xinmintx.system.domain.MemberBenefit;
import com.xinmintx.system.service.IMemberBenefitService;
import com.xinmintx.common.core.text.Convert;

/**
 * 消费分润Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
@Service
public class MemberBenefitServiceImpl implements IMemberBenefitService 
{
    @Autowired
    private MemberBenefitMapper memberBenefitMapper;

    /**
     * 查询消费分润
     * 
     * @param id 消费分润ID
     * @return 消费分润
     */
    @Override
    public MemberBenefit selectMemberBenefitById(Long id)
    {
        return memberBenefitMapper.selectMemberBenefitById(id);
    }

    /**
     * 查询消费分润列表
     * 
     * @param memberBenefit 消费分润
     * @return 消费分润
     */
    @Override
    public List<MemberBenefit> selectMemberBenefitList(MemberBenefit memberBenefit)
    {
        return memberBenefitMapper.selectMemberBenefitList(memberBenefit);
    }

    /**
     * 新增消费分润
     * 
     * @param memberBenefit 消费分润
     * @return 结果
     */
    @Override
    public int insertMemberBenefit(MemberBenefit memberBenefit)
    {
        return memberBenefitMapper.insertMemberBenefit(memberBenefit);
    }

    /**
     * 修改消费分润
     * 
     * @param memberBenefit 消费分润
     * @return 结果
     */
    @Override
    public int updateMemberBenefit(MemberBenefit memberBenefit)
    {
        return memberBenefitMapper.updateMemberBenefit(memberBenefit);
    }

    /**
     * 删除消费分润对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberBenefitByIds(String ids)
    {
        return memberBenefitMapper.deleteMemberBenefitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除消费分润信息
     * 
     * @param id 消费分润ID
     * @return 结果
     */
    @Override
    public int deleteMemberBenefitById(Long id)
    {
        return memberBenefitMapper.deleteMemberBenefitById(id);
    }
}
