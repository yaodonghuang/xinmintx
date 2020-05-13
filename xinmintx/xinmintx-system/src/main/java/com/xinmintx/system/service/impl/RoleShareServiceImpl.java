package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.RoleShareMapper;
import com.xinmintx.system.domain.RoleShare;
import com.xinmintx.system.service.IRoleShareService;
import com.xinmintx.common.core.text.Convert;

/**
 * 分润规则Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-22
 */
@Service
public class RoleShareServiceImpl implements IRoleShareService 
{
    @Autowired
    private RoleShareMapper roleShareMapper;

    /**
     * 查询分润规则
     * 
     * @param id 分润规则ID
     * @return 分润规则
     */
    @Override
    public RoleShare selectRoleShareById(Long id)
    {
        return roleShareMapper.selectRoleShareById(id);
    }

    /**
     * 查询分润规则列表
     * 
     * @param roleShare 分润规则
     * @return 分润规则
     */
    @Override
    public List<RoleShare> selectRoleShareList(RoleShare roleShare)
    {
        return roleShareMapper.selectRoleShareList(roleShare);
    }

    /**
     * 新增分润规则
     * 
     * @param roleShare 分润规则
     * @return 结果
     */
    @Override
    public int insertRoleShare(RoleShare roleShare)
    {
        return roleShareMapper.insertRoleShare(roleShare);
    }

    /**
     * 修改分润规则
     * 
     * @param roleShare 分润规则
     * @return 结果
     */
    @Override
    public int updateRoleShare(RoleShare roleShare)
    {
        return roleShareMapper.updateRoleShare(roleShare);
    }

    /**
     * 删除分润规则对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleShareByIds(String ids)
    {
        return roleShareMapper.deleteRoleShareByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除分润规则信息
     * 
     * @param id 分润规则ID
     * @return 结果
     */
    @Override
    public int deleteRoleShareById(Long id)
    {
        return roleShareMapper.deleteRoleShareById(id);
    }
}
