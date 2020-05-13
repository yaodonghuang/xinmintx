package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.IndexModuleMapper;
import com.xinmintx.system.domain.IndexModule;
import com.xinmintx.system.service.IIndexModuleService;
import com.xinmintx.common.core.text.Convert;

/**
 * 首页模块配置Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-01-06
 */
@Service
public class IndexModuleServiceImpl implements IIndexModuleService 
{
    @Autowired
    private IndexModuleMapper indexModuleMapper;

    /**
     * 查询首页模块配置
     * 
     * @param id 首页模块配置ID
     * @return 首页模块配置
     */
    @Override
    public IndexModule selectIndexModuleById(Long id)
    {
        return indexModuleMapper.selectIndexModuleById(id);
    }

    /**
     * 查询首页模块配置列表
     * 
     * @param indexModule 首页模块配置
     * @return 首页模块配置
     */
    @Override
    public List<IndexModule> selectIndexModuleList(IndexModule indexModule)
    {
        return indexModuleMapper.selectIndexModuleList(indexModule);
    }

    /**
     * 新增首页模块配置
     * 
     * @param indexModule 首页模块配置
     * @return 结果
     */
    @Override
    public int insertIndexModule(IndexModule indexModule)
    {
        return indexModuleMapper.insertIndexModule(indexModule);
    }

    /**
     * 修改首页模块配置
     * 
     * @param indexModule 首页模块配置
     * @return 结果
     */
    @Override
    public int updateIndexModule(IndexModule indexModule)
    {
        return indexModuleMapper.updateIndexModule(indexModule);
    }

    /**
     * 删除首页模块配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIndexModuleByIds(String ids)
    {
        return indexModuleMapper.deleteIndexModuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除首页模块配置信息
     * 
     * @param id 首页模块配置ID
     * @return 结果
     */
    @Override
    public int deleteIndexModuleById(Long id)
    {
        return indexModuleMapper.deleteIndexModuleById(id);
    }
}
