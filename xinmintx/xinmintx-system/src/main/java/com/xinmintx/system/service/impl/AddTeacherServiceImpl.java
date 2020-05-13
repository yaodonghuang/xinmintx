package com.xinmintx.system.service.impl;

import com.xinmintx.system.domain.AddTeacher;
import com.xinmintx.system.mapper.AddTeacherMapper;
import com.xinmintx.system.service.AddTeacherService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xinmintx.common.core.text.Convert;

/**
 * @ClassName:.AddTeacherServiceImpl
 * @author:chf
 * @Date:2020/1/17：17:18
 * @developerKits： win 10     jdk1.8
 */
@Service
public class AddTeacherServiceImpl implements AddTeacherService {
    @Autowired
    private AddTeacherMapper teacherMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    @Override
    public AddTeacher selectUserById(Long id)
    {
        return teacherMapper.selectUserById(id);
    }

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Override
    public List<AddTeacher> selectUserList(AddTeacher user)
    {
        return teacherMapper.selectUserList(user);
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(AddTeacher user)
    {
        user.setCreateTime(DateUtils.getNowDate());
        return teacherMapper.insertUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(AddTeacher user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return teacherMapper.updateUser(user);
    }

    /**
     * 删除用户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids)
    {
        return teacherMapper.deleteUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return teacherMapper.deleteUserById(id);
    }
}
