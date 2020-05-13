package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.UserPickTurnover;
import java.util.List;

/**
 * 提货点营业额百分比设置Mapper接口
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
public interface UserPickTurnoverMapper 
{
    /**
     * 查询提货点营业额百分比设置
     * 
     * @param id 提货点营业额百分比设置ID
     * @return 提货点营业额百分比设置
     */
    public UserPickTurnover selectUserPickTurnoverById(Long id);

    /**
     * 查询提货点营业额百分比设置列表
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 提货点营业额百分比设置集合
     */
    public List<UserPickTurnover> selectUserPickTurnoverList(UserPickTurnover userPickTurnover);

    /**
     * 新增提货点营业额百分比设置
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 结果
     */
    public int insertUserPickTurnover(UserPickTurnover userPickTurnover);

    /**
     * 修改提货点营业额百分比设置
     * 
     * @param userPickTurnover 提货点营业额百分比设置
     * @return 结果
     */
    public int updateUserPickTurnover(UserPickTurnover userPickTurnover);

    /**
     * 删除提货点营业额百分比设置
     * 
     * @param id 提货点营业额百分比设置ID
     * @return 结果
     */
    public int deleteUserPickTurnoverById(Long id);

    /**
     * 批量删除提货点营业额百分比设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPickTurnoverByIds(String[] ids);
}
