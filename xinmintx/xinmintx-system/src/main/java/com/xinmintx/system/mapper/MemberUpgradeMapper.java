package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.MemberUpgrade;
import java.util.List;

/**
 * 会员卡升级金额或积分Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-28
 */
public interface MemberUpgradeMapper 
{
    /**
     * 查询会员卡升级金额或积分
     * 
     * @param id 会员卡升级金额或积分ID
     * @return 会员卡升级金额或积分
     */
    public MemberUpgrade selectMemberUpgradeById(Integer id);

    /**
     * 查询会员卡升级金额或积分列表
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 会员卡升级金额或积分集合
     */
    public List<MemberUpgrade> selectMemberUpgradeList(MemberUpgrade memberUpgrade);

    /**
     * 新增会员卡升级金额或积分
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 结果
     */
    public int insertMemberUpgrade(MemberUpgrade memberUpgrade);

    /**
     * 修改会员卡升级金额或积分
     * 
     * @param memberUpgrade 会员卡升级金额或积分
     * @return 结果
     */
    public int updateMemberUpgrade(MemberUpgrade memberUpgrade);

    /**
     * 删除会员卡升级金额或积分
     * 
     * @param id 会员卡升级金额或积分ID
     * @return 结果
     */
    public int deleteMemberUpgradeById(Integer id);

    /**
     * 批量删除会员卡升级金额或积分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberUpgradeByIds(String[] ids);
}
