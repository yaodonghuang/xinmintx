package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.RegionMapper;
import com.xinmintx.system.domain.Region;
import com.xinmintx.system.service.IRegionService;
import com.xinmintx.common.core.text.Convert;

/**
 * 全国省市区（县）基础数据Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class RegionServiceImpl implements IRegionService 
{
    @Autowired
    private RegionMapper regionMapper;

    /**
     * 查询全国省市区（县）基础数据
     * 
     * @param id 全国省市区（县）基础数据ID
     * @return 全国省市区（县）基础数据
     */
    @Override
    public Region selectRegionById(Long id)
    {
        return regionMapper.selectRegionById(id);
    }

    /**
     * 查询全国省市区（县）基础数据列表
     * 
     * @param region 全国省市区（县）基础数据
     * @return 全国省市区（县）基础数据
     */
    @Override
    public List<Region> selectRegionList(Region region)
    {
        return regionMapper.selectRegionList(region);
    }

    /**
     * 新增全国省市区（县）基础数据
     * 
     * @param region 全国省市区（县）基础数据
     * @return 结果
     */
    @Override
    public int insertRegion(Region region)
    {
        return regionMapper.insertRegion(region);
    }

    /**
     * 修改全国省市区（县）基础数据
     * 
     * @param region 全国省市区（县）基础数据
     * @return 结果
     */
    @Override
    public int updateRegion(Region region)
    {
        return regionMapper.updateRegion(region);
    }

    /**
     * 删除全国省市区（县）基础数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRegionByIds(String ids)
    {
        return regionMapper.deleteRegionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除全国省市区（县）基础数据信息
     * 
     * @param id 全国省市区（县）基础数据ID
     * @return 结果
     */
    @Override
    public int deleteRegionById(Long id)
    {
        return regionMapper.deleteRegionById(id);
    }
}
