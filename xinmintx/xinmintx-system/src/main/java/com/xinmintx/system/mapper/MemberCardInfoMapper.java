package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.MemberCardInfo;
import java.util.List;

/**
 * 会员卡信息Mapper接口
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
public interface MemberCardInfoMapper 
{
    /**
     * 查询会员卡信息
     * 
     * @param id 会员卡信息ID
     * @return 会员卡信息
     */
    public MemberCardInfo selectMemberCardInfoById(Long id);

    /**
     * 查询会员卡信息列表
     * 
     * @param memberCardInfo 会员卡信息
     * @return 会员卡信息集合
     */
    public List<MemberCardInfo> selectMemberCardInfoList(MemberCardInfo memberCardInfo);

    /**
     * 新增会员卡信息
     * 
     * @param memberCardInfo 会员卡信息
     * @return 结果
     */
    public int insertMemberCardInfo(MemberCardInfo memberCardInfo);

    /**
     * 修改会员卡信息
     * 
     * @param memberCardInfo 会员卡信息
     * @return 结果
     */
    public int updateMemberCardInfo(MemberCardInfo memberCardInfo);

    /**
     * 删除会员卡信息
     * 
     * @param id 会员卡信息ID
     * @return 结果
     */
    public int deleteMemberCardInfoById(Long id);

    /**
     * 批量删除会员卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberCardInfoByIds(String[] ids);
}
