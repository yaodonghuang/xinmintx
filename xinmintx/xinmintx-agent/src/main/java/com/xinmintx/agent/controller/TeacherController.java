package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName:.StudentListController
 * @author:chf
 * @Date:2020/1/17：9:30
 * @developerKits： win 10     jdk1.8
 */
@Controller
@RequestMapping("/agent/teacher")
public class TeacherController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询学员
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/jumpStudent")
    public String jumpStudent(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute("user");
        List<User> list = studentService.queryStudentById(user.getId());
        List<User> users = studentService.queryStudentByUp(user.getId());
        Long listsNum = studentService.queryStudentByIdNum(user.getId());
        Long usersNum = studentService.queryStudentByUpNum(user.getId());
        model.addAttribute("lists",list);
        model.addAttribute("listsNum",listsNum);
        model.addAttribute("usersNum",usersNum);
        model.addAttribute("users",users);
        return "student_list";
    }
    /**
     * 跳转我的课堂
     * @return
     */
    @RequestMapping("/myClass")
    public String getMyClass(HttpServletRequest request, Model map){
        User user = (User)request.getSession().getAttribute("user");
        //我的学员列表
        List<User> users = studentService.QueryUserStudentByUserId(user.getId());
        //我的学员总人数
        long studentNum = studentService.CountUserStudentByUserId(user.getId());
        //升级学员人数
        long upgradeStudentNum = studentService.upgradeUserStudentByUserId(user.getId());
        //当月业绩
        //long num = studentService.resultsDuringTheMonth(user.getId());
        map.addAttribute("user",user);
        map.addAttribute("studentNum",studentNum);
        map.addAttribute("upgradeStudentNum",upgradeStudentNum);
        map.addAttribute("users",users);
        return "teacher";
    }

    /**
     * 跳转我的业绩
     * @return
     */
    @RequestMapping("/jumpMyyJ")
    public String jumpMyyJ(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "performance";
    }
}
