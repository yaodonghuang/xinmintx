package com.xinmintx.system.service;

import com.xinmintx.system.domain.MemberBenefit;
import java.util.List;

/**
 * 消费分润Service接口
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
public interface IMemberBenefitService 
{
    /**
     * 查询消费分润
     * 
     * @param id 消费分润ID
     * @return 消费分润
     */
    public MemberBenefit selectMemberBenefitById(Long id);

    /**
     * 查询消费分润列表
     * 
     * @param memberBenefit 消费分润
     * @return 消费分润集合
     */
    public List<MemberBenefit> selectMemberBenefitList(MemberBenefit memberBenefit);

    /**
     * 新增消费分润
     * 
     * @param memberBenefit 消费分润
     * @return 结果
     */
    public int insertMemberBenefit(MemberBenefit memberBenefit);

    /**
     * 修改消费分润
     * 
     * @param memberBenefit 消费分润
     * @return 结果
     */
    public int updateMemberBenefit(MemberBenefit memberBenefit);

    /**
     * 批量删除消费分润
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberBenefitByIds(String ids);

    /**
     * 删除消费分润信息
     * 
     * @param id 消费分润ID
     * @return 结果
     */
    public int deleteMemberBenefitById(Long id);
}
