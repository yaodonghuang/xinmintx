package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.CommunityMapper;
import com.xinmintx.system.domain.Community;
import com.xinmintx.system.service.ICommunityService;
import com.xinmintx.common.core.text.Convert;

/**
 * 社区Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
@Service
public class CommunityServiceImpl implements ICommunityService 
{
    @Autowired
    private CommunityMapper communityMapper;

    /**
     * 查询社区
     * 
     * @param id 社区ID
     * @return 社区
     */
    @Override
    public Community selectCommunityById(Long id)
    {
        return communityMapper.selectCommunityById(id);
    }

    /**
     * 查询社区列表
     * 
     * @param community 社区
     * @return 社区
     */
    @Override
    public List<Community> selectCommunityList(Community community)
    {
        return communityMapper.selectCommunityList(community);
    }

    /**
     * 新增社区
     * 
     * @param community 社区
     * @return 结果
     */
    @Override
    public int insertCommunity(Community community)
    {
        community.setCreateTime(DateUtils.getNowDate());
        return communityMapper.insertCommunity(community);
    }

    /**
     * 修改社区
     * 
     * @param community 社区
     * @return 结果
     */
    @Override
    public int updateCommunity(Community community)
    {
        community.setUpdateTime(DateUtils.getNowDate());
        return communityMapper.updateCommunity(community);
    }

    /**
     * 删除社区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommunityByIds(String ids)
    {
        return communityMapper.deleteCommunityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除社区信息
     * 
     * @param id 社区ID
     * @return 结果
     */
    @Override
    public int deleteCommunityById(Long id)
    {
        return communityMapper.deleteCommunityById(id);
    }
}
