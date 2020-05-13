package com.xinmintx.factory.service.impl;

import com.xinmintx.factory.mapper.UserMapper;
import com.xinmintx.factory.model.User;
import com.xinmintx.factory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: com.xinmintx.factory.service.impl.UserServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/27 9:36
 * @Version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> query() {
        return userMapper.query();
    }
}
