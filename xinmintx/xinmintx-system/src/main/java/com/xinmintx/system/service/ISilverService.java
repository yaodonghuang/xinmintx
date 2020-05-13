package com.xinmintx.system.service;

import com.xinmintx.system.domain.Member;

import java.util.List;

/**
 * 银卡信息Service接口
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public interface ISilverService 
{
    /**
     * 查询银卡信息
     * 
     * @param id 银卡信息ID
     * @return 银卡信息
     */
    public Member selectSilverById(Long id);

    Member selectSilverById(Integer id);

    /**
     * 查询银卡信息列表
     * 
     * @param member 银卡信息
     * @return 银卡信息集合
     */
    public List<Member> selectSilverList(Member member);

    /**
     * 新增银卡信息
     * 
     * @param member 银卡信息
     * @return 结果
     */
    public int insertSilver(Member member);

    /**
     * 修改银卡信息
     * 
     * @param member 银卡信息
     * @return 结果
     */
    public int updateSilver(Member member);

    /**
     * 批量删除银卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSilverByIds(String ids);

    /**
     * 删除银卡信息信息
     * 
     * @param id 银卡信息ID
     * @return 结果
     */
    public int deleteSilverById(Long id);

    int deleteSilverByIds(Integer ids);

    int deleteSilverById(Integer id);
}
