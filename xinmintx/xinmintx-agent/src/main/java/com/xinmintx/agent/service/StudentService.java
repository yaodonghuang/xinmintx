package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/1/16
 * @time: 14:16
 * @Description:
 */
public interface StudentService {

    List<User> QueryUserStudentByUserId(int userId);

    long CountUserStudentByUserId(int userId);

    long upgradeUserStudentByUserId(Integer id);

    List<User> queryStudentById(Integer id);

    Long queryStudentByIdNum(Integer id);

    Long queryStudentByUpNum(Integer id);



    List<User> queryStudentByUp(Integer id);

    long resultsDuringTheMonth(Integer id);
}
