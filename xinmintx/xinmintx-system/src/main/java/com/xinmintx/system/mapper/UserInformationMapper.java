package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.User;
import com.xinmintx.system.domain.UserExt;
import com.xinmintx.system.domain.UserInformation;

import java.util.List;
/**
 * 用户信息Mapper接口
 *
 * @author xinmintx
 * @date 2020-01-14
 */
public interface UserInformationMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    public UserExt selectUserById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息集合
     */
    public List<UserInformation> selectUserList(UserInformation user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(UserInformation user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(UserInformation user);

    /**
     * 删除用户信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteUserById(Long id);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);
}
