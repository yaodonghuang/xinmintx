package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Factory;
import com.xinmintx.system.domain.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 厂家信息Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
public interface FactoryMapper 
{
    /**
     * 查询厂家信息
     * 
     * @param factoryId 厂家信息ID
     * @return 厂家信息
     */
    public Factory selectFactoryById(Long factoryId);

    /**
     * 查询厂家信息列表
     * 
     * @param factory 厂家信息
     * @return 厂家信息集合
     */
    public List<Factory> selectFactoryList(Factory factory);

    /**
     * 新增厂家信息
     * 
     * @param factory 厂家信息
     * @return 结果
     */
    public int insertFactory(Factory factory);

    /**
     * 修改厂家信息
     * 
     * @param factory 厂家信息
     * @return 结果
     */
    public int updateFactory(Factory factory);

    /**
     * 删除厂家信息
     * 
     * @param factoryId 厂家信息ID
     * @return 结果
     */
    public int deleteFactoryById(Long factoryId);

    /**
     * 批量删除厂家信息
     * 
     * @param factoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFactoryByIds(String[] factoryIds);

    Integer ifExistsCode(@Param("code") String code);

    Integer ifExistsFactory(@Param("name") String name, @Param("phone") String phone);

    List<Factory> selectFactory(String name);
}
