package com.xinmintx.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.RedEnvelopeActivitiesMapper;
import com.xinmintx.system.domain.RedEnvelopeActivities;
import com.xinmintx.system.service.IRedEnvelopeActivitiesService;
import com.xinmintx.common.core.text.Convert;

/**
 * 活动Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-10
 */
@Service
public class RedEnvelopeActivitiesServiceImpl implements IRedEnvelopeActivitiesService 
{
    @Autowired
    private RedEnvelopeActivitiesMapper redEnvelopeActivitiesMapper;

    /**
     * 查询活动
     * 
     * @param id 活动ID
     * @return 活动
     */
    @Override
    public RedEnvelopeActivities selectRedEnvelopeActivitiesById(Long id)
    {
        return redEnvelopeActivitiesMapper.selectRedEnvelopeActivitiesById(id);
    }

    /**
     * 查询活动列表
     * 
     * @param redEnvelopeActivities 活动
     * @return 活动
     */
    @Override
    public List<RedEnvelopeActivities> selectRedEnvelopeActivitiesList(RedEnvelopeActivities redEnvelopeActivities)
    {
        if (redEnvelopeActivities.getRedStartTime() !=null) {
            redEnvelopeActivities.setStartTime(redEnvelopeActivities.getRedStartTime().getTime()/1000);
        }
        if (redEnvelopeActivities.getRedEndTime() !=null){
            redEnvelopeActivities.setEndTime(redEnvelopeActivities.getRedEndTime().getTime()/1000);
        }
        return redEnvelopeActivitiesMapper.selectRedEnvelopeActivitiesList(redEnvelopeActivities);
    }

    /**
     * 新增活动
     * 
     * @param redEnvelopeActivities 活动
     * @return 结果
     */
    @Override
    public int insertRedEnvelopeActivities(RedEnvelopeActivities redEnvelopeActivities)
    {
        if (redEnvelopeActivities.getStart() == 1){
            RedEnvelopeActivities redEnvelopeActivities1 = redEnvelopeActivitiesMapper.selectByStart(redEnvelopeActivities);
            if (redEnvelopeActivities1!=null){
                return 0;
            }
        }
        long startTime = redEnvelopeActivities.getRedStartTime().getTime();
        long endTime = redEnvelopeActivities.getRedEndTime().getTime();
        if (startTime>endTime){
            return 0;
        }
        return redEnvelopeActivitiesMapper.insertRedEnvelopeActivities(redEnvelopeActivities);
    }

    /**
     * 修改活动
     * 
     * @param redEnvelopeActivities 活动
     * @return 结果
     */
    @Override
    public int updateRedEnvelopeActivities(RedEnvelopeActivities redEnvelopeActivities)
    {
        if (redEnvelopeActivities.getStart()==1) {
            RedEnvelopeActivities redEnvelopeActivities1 = redEnvelopeActivitiesMapper.selectByStart(redEnvelopeActivities);
            if (redEnvelopeActivities1!=null && !redEnvelopeActivities.getId().equals(redEnvelopeActivities1.getId())){
                return 0;
            }else {
                return redEnvelopeActivitiesMapper.updateActivities(redEnvelopeActivities);
            }
        }
        long startTime = redEnvelopeActivities.getRedStartTime().getTime();
        long endTime = redEnvelopeActivities.getRedEndTime().getTime();
        if (startTime>endTime){
            return 0;
        }
        return redEnvelopeActivitiesMapper.updateRedEnvelopeActivities(redEnvelopeActivities);
    }

    /**
     * 删除活动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRedEnvelopeActivitiesByIds(String ids)
    {
        return redEnvelopeActivitiesMapper.deleteRedEnvelopeActivitiesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动信息
     * 
     * @param id 活动ID
     * @return 结果
     */
    @Override
    public int deleteRedEnvelopeActivitiesById(Long id)
    {
        return redEnvelopeActivitiesMapper.deleteRedEnvelopeActivitiesById(id);
    }
}
