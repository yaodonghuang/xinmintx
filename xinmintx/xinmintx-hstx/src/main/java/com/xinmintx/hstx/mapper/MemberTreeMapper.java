package com.xinmintx.hstx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   YaoDongHuang
 */
public interface MemberTreeMapper extends BaseMapper<MemberTree> {
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
}
