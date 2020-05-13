package com.xinmintx.system.service;

import com.xinmintx.system.domain.Member;
import com.xinmintx.system.domain.MemberTree;

import java.util.List;

/**
 * YaoDongHuang
 */
public interface IMemberTreeService {
    /**
     * 查询会员卡
     *
     * @param id       会员ID
     * @param treeCode 会员树编码
     */
    public void setTreeNumForMember(int id, String treeCode);

    /**
     * @param id 会员ID
     * @return 会员id上层两个会员id的list
     */
    public List<Integer> getMemberId(int id);

    /**
     *
     * @param id 会员ID
     * @return 会员id上层十个会员id的list
     */
    public List<Integer> getTenMemberId(int id);

    /**
     * @param id     会员ID
     * @param idList 上层分润的idList
     */
    public void saveMemberLine(int id, List<Integer> idList);

    /**
     *  根据会员id查询下游所有的账号数量
     * @param id 会员ID
     * @return
     */
    public Integer getNextMemberQty(int id);
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
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberTreeByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param treeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteMemberTreeById(Long treeId);
}
