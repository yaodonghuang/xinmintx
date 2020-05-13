package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Advertising;
import java.util.List;

/**
 * 广告Mapper接口
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
public interface AdvertisingMapper 
{
    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    public Advertising selectAdvertisingById(Long id);

    /**
     * 查询广告列表
     * 
     * @param advertising 广告
     * @return 广告集合
     */
    public List<Advertising> selectAdvertisingList(Advertising advertising);

    /**
     * 新增广告
     * 
     * @param advertising 广告
     * @return 结果
     */
    public int insertAdvertising(Advertising advertising);

    /**
     * 修改广告
     * 
     * @param advertising 广告
     * @return 结果
     */
    public int updateAdvertising(Advertising advertising);

    /**
     * 删除广告
     * 
     * @param id 广告ID
     * @return 结果
     */
    public int deleteAdvertisingById(Long id);

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdvertisingByIds(String[] ids);
}
