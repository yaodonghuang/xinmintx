package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.UserPickTurnoverMapper;
import com.xinmintx.system.domain.UserPickTurnover;
import com.xinmintx.system.service.IUserPickTurnoverService;
import com.xinmintx.common.core.text.Convert;

/**
 * 提货点营业额百分比设置Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
@Service
public class UserPickTurnoverServiceImpl implements IUserPickTurnoverService 
{
    @Autowired
    private UserPickTurnoverMapper userPickTurnoverMapper;

    /**
     * 查询提货点营业额百分比设置
     * 
     * @param id 提货点营业额百分比设置ID
     * @return 提货点营业额百分比设置
     */
    @Override
    public UserPickTurnover selectUserPickTurnoverById(Long id)
    {
        return userPickTurnoverMapper.selectUserPickTurnoverById(id);
    }

    /**
     * 查询提货点营业额百分比设置列表
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 提货点营业额百分比设置
     */
    @Override
    public List<UserPickTurnover> selectUserPickTurnoverList(UserPickTurnover userPickTurnover)
    {
        return userPickTurnoverMapper.selectUserPickTurnoverList(userPickTurnover);
    }

    /**
     * 新增提货点营业额百分比设置
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 结果
     */
    @Override
    public int insertUserPickTurnover(UserPickTurnover userPickTurnover)
    {
        return userPickTurnoverMapper.insertUserPickTurnover(userPickTurnover);
    }

    /**
     * 修改提货点营业额百分比设置
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 结果
     */
    @Override
    public int updateUserPickTurnover(UserPickTurnover userPickTurnover)
    {
        return userPickTurnoverMapper.updateUserPickTurnover(userPickTurnover);
    }

    /**
     * 删除提货点营业额百分比设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserPickTurnoverByIds(String ids)
    {
        return userPickTurnoverMapper.deleteUserPickTurnoverByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提货点营业额百分比设置信息
     * 
     * @param id 提货点营业额百分比设置ID
     * @return 结果
     */
    @Override
    public int deleteUserPickTurnoverById(Long id)
    {
        return userPickTurnoverMapper.deleteUserPickTurnoverById(id);
    }
}
