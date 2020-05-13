package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.RoleShare;
import java.util.List;

/**
 * 分润规则Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-22
 */
public interface RoleShareMapper 
{
    /**
     * 查询分润规则
     * 
     * @param id 分润规则ID
     * @return 分润规则
     */
    public RoleShare selectRoleShareById(Long id);

    /**
     * 查询分润规则列表
     * 
     * @param roleShare 分润规则
     * @return 分润规则集合
     */
    public List<RoleShare> selectRoleShareList(RoleShare roleShare);

    /**
     * 新增分润规则
     * 
     * @param roleShare 分润规则
     * @return 结果
     */
    public int insertRoleShare(RoleShare roleShare);

    /**
     * 修改分润规则
     * 
     * @param roleShare 分润规则
     * @return 结果
     */
    public int updateRoleShare(RoleShare roleShare);

    /**
     * 删除分润规则
     * 
     * @param id 分润规则ID
     * @return 结果
     */
    public int deleteRoleShareById(Long id);

    /**
     * 批量删除分润规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleShareByIds(String[] ids);
}
