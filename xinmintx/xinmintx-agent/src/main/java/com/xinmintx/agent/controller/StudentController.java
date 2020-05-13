package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.StudentService;
import com.xinmintx.agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/1/16 14:35
 * @Description:  学员
 */
@Controller
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private UserService userService;

    /**
     * 新增学员页面跳转
     *
     * @return
     */
    @RequestMapping("/addStudent/{id}")
    public String addStudent(@PathVariable Integer id, Model model){
        model.addAttribute("teacherId",id);
        return "student/student_add";
    }

    /**新增学员页面
     *
     * @return
     */
    @RequestMapping("/saveStudent")
    @ResponseBody
    public ResultCode saveStudent(User user){
        return userService.saveStudent(user);
    }
}
