package com.xinmintx.system.service.impl;

import com.xinmintx.common.core.text.Convert;
import com.xinmintx.system.domain.Cron;
import com.xinmintx.system.mapper.CronMapper;
import com.xinmintx.system.service.ICronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时器Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
@Service
public class CronServiceImpl implements ICronService 
{
    @Autowired
    private CronMapper cronMapper;

    /**
     * 查询定时器
     * 
     * @param id 定时器ID
     * @return 定时器
     */
    @Override
    public Cron selectCronById(Long id)
    {
        return cronMapper.selectCronById(id);
    }

    /**
     * 查询定时器列表
     * 
     * @param cron 定时器
     * @return 定时器
     */
    @Override
    public List<Cron> selectCronList(Cron cron)
    {
        return cronMapper.selectCronList(cron);
    }

    /**
     * 新增定时器
     * 
     * @param cron 定时器
     * @return 结果
     */
    @Override
    public int insertCron(Cron cron)
    {
        return cronMapper.insertCron(cron);
    }

    /**
     * 修改定时器
     * 
     * @param cron 定时器
     * @return 结果
     */
    @Override
    public int updateCron(Cron cron)
    {
        return cronMapper.updateCron(cron);
    }

    /**
     * 删除定时器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCronByIds(String ids)
    {
        return cronMapper.deleteCronByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除定时器信息
     * 
     * @param id 定时器ID
     * @return 结果
     */
    @Override
    public int deleteCronById(Long id)
    {
        return cronMapper.deleteCronById(id);
    }

}
