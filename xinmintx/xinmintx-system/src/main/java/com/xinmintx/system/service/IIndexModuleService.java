package com.xinmintx.system.service;

import com.xinmintx.system.domain.IndexModule;
import java.util.List;

/**
 * 首页模块配置Service接口
 * 
 * @author xinmintx
 * @date 2020-01-06
 */
public interface IIndexModuleService 
{
    /**
     * 查询首页模块配置
     * 
     * @param id 首页模块配置ID
     * @return 首页模块配置
     */
    public IndexModule selectIndexModuleById(Long id);

    /**
     * 查询首页模块配置列表
     * 
     * @param indexModule 首页模块配置
     * @return 首页模块配置集合
     */
    public List<IndexModule> selectIndexModuleList(IndexModule indexModule);

    /**
     * 新增首页模块配置
     * 
     * @param indexModule 首页模块配置
     * @return 结果
     */
    public int insertIndexModule(IndexModule indexModule);

    /**
     * 修改首页模块配置
     * 
     * @param indexModule 首页模块配置
     * @return 结果
     */
    public int updateIndexModule(IndexModule indexModule);

    /**
     * 批量删除首页模块配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIndexModuleByIds(String ids);

    /**
     * 删除首页模块配置信息
     * 
     * @param id 首页模块配置ID
     * @return 结果
     */
    public int deleteIndexModuleById(Long id);
}
