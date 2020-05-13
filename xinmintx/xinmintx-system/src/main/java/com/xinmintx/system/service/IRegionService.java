package com.xinmintx.system.service;

import com.xinmintx.system.domain.Region;
import java.util.List;

/**
 * 全国省市区（县）基础数据Service接口
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public interface IRegionService 
{
    /**
     * 查询全国省市区（县）基础数据
     * 
     * @param id 全国省市区（县）基础数据ID
     * @return 全国省市区（县）基础数据
     */
    public Region selectRegionById(Long id);

    /**
     * 查询全国省市区（县）基础数据列表
     * 
     * @param region 全国省市区（县）基础数据
     * @return 全国省市区（县）基础数据集合
     */
    public List<Region> selectRegionList(Region region);

    /**
     * 新增全国省市区（县）基础数据
     * 
     * @param region 全国省市区（县）基础数据
     * @return 结果
     */
    public int insertRegion(Region region);

    /**
     * 修改全国省市区（县）基础数据
     * 
     * @param region 全国省市区（县）基础数据
     * @return 结果
     */
    public int updateRegion(Region region);

    /**
     * 批量删除全国省市区（县）基础数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRegionByIds(String ids);

    /**
     * 删除全国省市区（县）基础数据信息
     * 
     * @param id 全国省市区（县）基础数据ID
     * @return 结果
     */
    public int deleteRegionById(Long id);
}
