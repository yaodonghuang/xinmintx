package com.xinmintx.hstx.service;

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
}
