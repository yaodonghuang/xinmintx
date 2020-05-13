package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberUpgradeMapper;
import com.xinmintx.system.domain.MemberUpgrade;
import com.xinmintx.system.service.IMemberUpgradeService;
import com.xinmintx.common.core.text.Convert;

/**
 * 会员卡升级金额或积分Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-28
 */
@Service
public class MemberUpgradeServiceImpl implements IMemberUpgradeService 
{
    @Autowired
    private MemberUpgradeMapper memberUpgradeMapper;

    /**
     * 查询会员卡升级金额或积分
     * 
     * @param id 会员卡升级金额或积分ID
     * @return 会员卡升级金额或积分
     */
    @Override
    public MemberUpgrade selectMemberUpgradeById(Integer id)
    {
        return memberUpgradeMapper.selectMemberUpgradeById(id);
    }

    /**
     * 查询会员卡升级金额或积分列表
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 会员卡升级金额或积分
     */
    @Override
    public List<MemberUpgrade> selectMemberUpgradeList(MemberUpgrade memberUpgrade)
    {
        return memberUpgradeMapper.selectMemberUpgradeList(memberUpgrade);
    }

    /**
     * 新增会员卡升级金额或积分
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 结果
     */
    @Override
    public int insertMemberUpgrade(MemberUpgrade memberUpgrade)
    {
        return memberUpgradeMapper.insertMemberUpgrade(memberUpgrade);
    }

    /**
     * 修改会员卡升级金额或积分
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 结果
     */
    @Override
    public int updateMemberUpgrade(MemberUpgrade memberUpgrade)
    {
        return memberUpgradeMapper.updateMemberUpgrade(memberUpgrade);
    }

    /**
     * 删除会员卡升级金额或积分对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberUpgradeByIds(String ids)
    {
        return memberUpgradeMapper.deleteMemberUpgradeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员卡升级金额或积分信息
     * 
     * @param id 会员卡升级金额或积分ID
     * @return 结果
     */
    @Override
    public int deleteMemberUpgradeById(Integer id)
    {
        return memberUpgradeMapper.deleteMemberUpgradeById(id);
    }
}
