package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Member;
import com.xinmintx.system.domain.MemberTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   YaoDongHuang
 */
public interface MemberTreeMapper {
    public void setTreeNoForMember(@Param("id") Integer id, @Param("treeCode") String treeCode);

    public Integer ifExistsData();

    public int insert(@Param("list") List<MemberTree> mtList);

    public String getTreeCode(@Param("str") String str);

    public Integer ifExistsMember(@Param("id") Integer id);

    public int updateMemberIdToNull(@Param("id") Integer id);

    public Integer ifExistsTreeCode(@Param("str") String str);

    public MemberTree getUpperMemberId(@Param("id") Integer id);

    public Long getTreeIdByMemberId(@Param("id") Integer id);

    public List<MemberTree> getNextMemberQty(@Param("id") Integer id);

    public String getTreeCodeByMemberId(@Param("memberId") Integer memberId);

    int insertMemberList(@Param("list") List<Member> memberList);

    int updateMemberIdToMemberTree();

    int updateMemberTreeCode(@Param("treeCode") String treeCode, @Param("id") Integer id);
    /**
     * 查询【请填写功能名称】
     *
     * @param treeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public MemberTree selectMemberTreeById(Long treeId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param memberTree 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MemberTree> selectMemberTreeList(MemberTree memberTree);

    /**
     * 新增【请填写功能名称】
     *
     * @param memberTree 【请填写功能名称】
     * @return 结果
     */
    public int insertMemberTree(MemberTree memberTree);

    /**
     * 修改【请填写功能名称】
     *
     * @param memberTree 【请填写功能名称】
     * @return 结果
     */
    public int updateMemberTree(MemberTree memberTree);

    /**
     * 删除【请填写功能名称】
     *
     * @param treeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteMemberTreeById(Long treeId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param treeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberTreeByIds(String[] treeIds);
}
