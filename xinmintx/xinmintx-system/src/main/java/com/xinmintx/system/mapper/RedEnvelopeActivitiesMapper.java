package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.RedEnvelopeActivities;
import java.util.List;

/**
 * 活动Mapper接口
 * 
 * @author xinmintx
 * @date 2020-03-10
 */
public interface RedEnvelopeActivitiesMapper 
{
    /**
     * 查询活动
     * 
     * @param id 活动ID
     * @return 活动
     */
    public RedEnvelopeActivities selectRedEnvelopeActivitiesById(Long id);

    /**
     * 查询活动列表
     * 
     * @param redEnvelopeActivities 活动
     * @return 活动集合
     */
    public List<RedEnvelopeActivities> selectRedEnvelopeActivitiesList(RedEnvelopeActivities redEnvelopeActivities);

    /**
     * 新增活动
     * 
     * @param redEnvelopeActivities 活动
     * @return 结果
     */
    public int insertRedEnvelopeActivities(RedEnvelopeActivities redEnvelopeActivities);

    /**
     * 修改活动
     * 
     * @param redEnvelopeActivities 活动
     * @return 结果
     */
    public int updateRedEnvelopeActivities(RedEnvelopeActivities redEnvelopeActivities);

    /**
     * 删除活动
     * 
     * @param id 活动ID
     * @return 结果
     */
    public int deleteRedEnvelopeActivitiesById(Long id);

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRedEnvelopeActivitiesByIds(String[] ids);

    int updateActivities(RedEnvelopeActivities redEnvelopeActivities);

    RedEnvelopeActivities selectByStart(RedEnvelopeActivities redEnvelopeActivities);
}
