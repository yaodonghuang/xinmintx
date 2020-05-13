package com.xinmintx.system.service;

import com.xinmintx.system.domain.Member;
import com.xinmintx.system.domain.MemberTree;

import java.util.List;

/**
 * 会员卡Service接口
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
public interface IMemberService 
{
    /**
     * 查询会员卡
     * 
     * @param id 会员卡ID
     * @return 会员卡
     */
    public Member selectMemberById(Long id);

    /**
     * 查询会员卡列表
     * 
     * @param member 会员卡
     * @return 会员卡集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员卡
     * 
     * @param member 会员卡
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改会员卡
     * 
     * @param member 会员卡
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除会员卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberByIds(String ids);

    /**
     * 删除会员卡信息
     * 
     * @param id 会员卡ID
     * @return 结果
     */
    public int deleteMemberById(Long id);

    List<MemberTree> selectTreeCode();
}
