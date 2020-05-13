package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户信息Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public interface UserMapper 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息ID
     * @return 用户信息
     */
    public User selectUserById(Long id);

    /**
     * 根据区域代码查询分公司
     * @param regionCode
     * @return
     */
    @Select("select * from user where region_code=#{regionCode} and user_role=3")
    public User selectUserByRegionCode(String regionCode);


    /**
     * 查询用户信息列表
     * 
     * @param user 用户信息
     * @return 用户信息集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);

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

    /**
     * 根据用户手机号查询
     * @param phone
     * @return
     */
    public int selectUserByPhone(String phone);

}
