package com.xinmintx.system.service;

import com.xinmintx.system.domain.Cron;
import java.util.List;

/**
 * 定时器Service接口
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
public interface ICronService 
{
    /**
     * 查询定时器
     * 
     * @param id 定时器ID
     * @return 定时器
     */
    public Cron selectCronById(Long id);

    /**
     * 查询定时器列表
     * 
     * @param cron 定时器
     * @return 定时器集合
     */
    public List<Cron> selectCronList(Cron cron);

    /**
     * 新增定时器
     * 
     * @param cron 定时器
     * @return 结果
     */
    public int insertCron(Cron cron);

    /**
     * 修改定时器
     * 
     * @param cron 定时器
     * @return 结果
     */
    public int updateCron(Cron cron);

    /**
     * 批量删除定时器
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCronByIds(String ids);

    /**
     * 删除定时器信息
     * 
     * @param id 定时器ID
     * @return 结果
     */
    public int deleteCronById(Long id);

}
