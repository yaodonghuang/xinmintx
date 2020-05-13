package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberBonusMapper;
import com.xinmintx.system.domain.MemberBonus;
import com.xinmintx.system.service.IMemberBonusService;
import com.xinmintx.common.core.text.Convert;

/**
 * 会员奖金池Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
@Service
public class MemberBonusServiceImpl implements IMemberBonusService 
{
    @Autowired
    private MemberBonusMapper memberBonusMapper;

    /**
     * 查询会员奖金池
     * 
     * @param id 会员奖金池ID
     * @return 会员奖金池
     */
    @Override
    public MemberBonus selectMemberBonusById(Long id)
    {
        return memberBonusMapper.selectMemberBonusById(id);
    }

    /**
     * 查询会员奖金池列表
     * 
     * @param memberBonus 会员奖金池
     * @return 会员奖金池
     */
    @Override
    public List<MemberBonus> selectMemberBonusList(MemberBonus memberBonus)
    {
        return memberBonusMapper.selectMemberBonusList(memberBonus);
    }

    /**
     * 新增会员奖金池
     * 
     * @param memberBonus 会员奖金池
     * @return 结果
     */
    @Override
    public int insertMemberBonus(MemberBonus memberBonus)
    {
        if(memberBonus.getStatus()==1){
            Integer i  = memberBonusMapper.queryMemberBonus();
            if (i != null){
                return 0;
            }
    }
        long beginDate = memberBonus.getBeginDate().getTime();
        long endDate = memberBonus.getEndDate().getTime();
        if (beginDate>endDate){
            return 0;
        }
            memberBonus.setCreateTime(DateUtils.getNowDate());
            return memberBonusMapper.insertMemberBonus(memberBonus);
    }

    /**
     * 修改会员奖金池
     * 
     * @param memberBonus 会员奖金池
     * @return 结果
     */
    @Override
    public int updateMemberBonus(MemberBonus memberBonus)
    {   if (memberBonus.getStatus()==1){
        MemberBonus memberBonus1  = memberBonusMapper.queryMemberBonusInfo();
        if (memberBonus1!=null){
            if (memberBonus1.getId().equals(memberBonus.getId())){
                long beginDate = memberBonus.getBeginDate().getTime();
                long endDate = memberBonus.getEndDate().getTime();
                if (beginDate<endDate){
                    memberBonus.setUpdateTime(DateUtils.getNowDate());
                    return memberBonusMapper.updateMemberBonus(memberBonus);
                }else {
                    return 0;
                }

            }else {
                return 0;
            }
        }else {
            long beginDate = memberBonus.getBeginDate().getTime();
            long endDate = memberBonus.getEndDate().getTime();
            if (beginDate<endDate){
                memberBonus.setUpdateTime(DateUtils.getNowDate());
                return memberBonusMapper.updateMemberBonus(memberBonus);
            }else {
                return 0;
            }
        }
    }else {
        long beginDate = memberBonus.getBeginDate().getTime();
        long endDate = memberBonus.getEndDate().getTime();
        if (beginDate<endDate){
            memberBonus.setUpdateTime(DateUtils.getNowDate());
            return memberBonusMapper.updateMemberBonus(memberBonus);
        }else {
            return 0;
        }
    }
    }

    /**
     * 删除会员奖金池对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberBonusByIds(String ids)
    {
        return memberBonusMapper.deleteMemberBonusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员奖金池信息
     * 
     * @param id 会员奖金池ID
     * @return 结果
     */
    @Override
    public int deleteMemberBonusById(Long id)
    {
        return memberBonusMapper.deleteMemberBonusById(id);
    }
}
