package com.xinmintx.system.service;

import com.xinmintx.system.domain.User;
import com.xinmintx.system.domain.UserExt;
import com.xinmintx.system.domain.UserInformation;

import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author xinmintx
 * @date 2020-01-14
 */
public interface UserInformationService {
    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    public List<UserExt> selectUserById(UserExt user);

    public List<UserExt> selectById(Long id);

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
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(String ids);

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    public int deleteUserById(Long id);
}
