package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.controller.TimedTask;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/1/16
 * @time: 14:19
 * @Description:
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TimedTask timedTask;


    @Override
    public List<User> QueryUserStudentByUserId(int userId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        //teacherId
        criteria.andTeacherIdEqualTo(userId);
        criteria.andStudentEqualTo(1);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public long CountUserStudentByUserId(int userId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTeacherIdEqualTo(userId);
        criteria.andStudentEqualTo(1);
        return userMapper.countByExample(userExample);
    }

    @Override
    public long upgradeUserStudentByUserId(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserRoleNotEqualTo(6);
        criteria.andUserRoleNotEqualTo(10);
        criteria.andTeacherIdEqualTo(id);
        criteria.andStatusNotEqualTo(0);
        criteria.andIsReviewEqualTo(1);
        criteria.andStudentEqualTo(1);
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> queryStudentById(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        criteria.andStudentEqualTo(1);
        criteria.andUserRoleEqualTo(10);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public Long queryStudentByIdNum(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        criteria.andStudentEqualTo(1);
        criteria.andUserRoleEqualTo(10);
        return userMapper.countByExample(userExample);
    }

    @Override
    public Long queryStudentByUpNum(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        criteria.andStudentEqualTo(1);
        criteria.andUserRoleNotEqualTo(10);
        criteria.andUserRoleNotEqualTo(6);
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> queryStudentByUp(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        criteria.andStudentEqualTo(1);
        criteria.andUserRoleNotEqualTo(10);
        criteria.andUserRoleNotEqualTo(6);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public long resultsDuringTheMonth(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserRoleNotEqualTo(6);
        criteria.andUserRoleNotEqualTo(10);
        criteria.andTeacherIdEqualTo(id);
        criteria.andStatusNotEqualTo(0);
        criteria.andIsReviewEqualTo(1);
        criteria.andStudentEqualTo(1);
        criteria.andCreateTimeGreaterThan(timedTask.date);
        return userMapper.countByExample(userExample);
    }
}
