package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/1/16 14:50
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加学员
     *
     * @param user 学员对象
     * @return ResultCode
     */
    @Override
    public ResultCode saveStudent(User user) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");

        User teacher = userMapper.selectByPrimaryKey(user.getTeacherId());
        if (teacher == null) {
            resultCode.setCode(500);
            resultCode.setMsg("讲师不存在");
            return resultCode;
        }
        if(teacher.getLecturerStatus()== null || teacher.getLecturerStatus() != 1) {
            resultCode.setCode(500);
            resultCode.setMsg("讲师不存在");
            return resultCode;
        }
        //根据手机号查询数据库是否存在该用户
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(user.getCellphone());
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            User u = users.get(0);
            //如果是学员
            if (u.getUserRole() == 10) {
                //更新讲师
                u.setTeacherId(user.getTeacherId());
                userMapper.updateByPrimaryKey(u);
                return resultCode;
            } else {
                resultCode.setCode(500);
                resultCode.setMsg("用户信息已存在");
                return resultCode;
            }
            //不存在该用户
        } else {
            user.setUserRole(10);
            user.setStatus(0);
            user.setIsReview(0);
            user.setCreateTime(new Date());
            user.setStudent(1);
            userMapper.insert(user);
        }
        return resultCode;
    }
}
