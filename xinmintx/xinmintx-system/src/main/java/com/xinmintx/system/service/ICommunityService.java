package com.xinmintx.system.service;

import com.xinmintx.system.domain.Community;
import java.util.List;

/**
 * 社区Service接口
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
public interface ICommunityService 
{
    /**
     * 查询社区
     * 
     * @param id 社区ID
     * @return 社区
     */
    public Community selectCommunityById(Long id);

    /**
     * 查询社区列表
     * 
     * @param community 社区
     * @return 社区集合
     */
    public List<Community> selectCommunityList(Community community);

    /**
     * 新增社区
     * 
     * @param community 社区
     * @return 结果
     */
    public int insertCommunity(Community community);

    /**
     * 修改社区
     * 
     * @param community 社区
     * @return 结果
     */
    public int updateCommunity(Community community);

    /**
     * 批量删除社区
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommunityByIds(String ids);

    /**
     * 删除社区信息
     * 
     * @param id 社区ID
     * @return 结果
     */
    public int deleteCommunityById(Long id);
}
