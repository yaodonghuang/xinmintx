package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.StaffProfit;
import com.xinmintx.agent.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 添加
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/addStaff")
    @ResponseBody
    public ResultCode addStaff(HttpServletRequest request, User user) {
        ResultCode resultCode = new ResultCode();
        User user1 = (User) request.getSession().getAttribute("user");
        user.setRecommender(user1.getId());
        int i = staffService.addStaff(user);
        if (i > 0) {
            resultCode.setCode(200);
        } else {
            resultCode.setCode(400);
        }
        return resultCode;
    }


    /**
     * 跳转添加员工页面
     *
     * @return
     */
    @GetMapping("/addStaff")
    public String addStaff() {
        return "staff/staff-add";
    }

    /**
     * 跳转员工设置
     *
     * @return
     */
    @GetMapping("/goStaffBase/{id}")
    public String goStaffBase(@PathVariable int id, Model model) {
        model.addAttribute("userId", id);
        return "staff/staff-base";
    }

    /**
     * 跳转到员工页面
     */
    @GetMapping("/listStaff")
    public String listStaff(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<User> stafflist = staffService.selectStaffByUserid(user.getId());
        model.addAttribute("stafflist", stafflist);
        return "staff/staff-list";
    }

    /**
     * 产看员工
     *
     * @param userId
     * @return
     */
    @GetMapping("/userId/{userId}")
    @ResponseBody
    public List<User> selectStaffByUserid(@PathVariable int userId) {
        return staffService.selectStaffByUserid(userId);
    }

    /**
     * 跳转基本商户奖金设置页面
     *
     * @return
     */
    @GetMapping("/setBasic/{userId}")
    public String dataGram(@PathVariable int userId, Model model) {
        StaffProfit staffProfit = staffService.selectStaffProfit(userId,5);
        model.addAttribute("staffProfit", staffProfit);
        return "staff/setBasic";
    }

    /**
     * 跳转黄金商户奖金设置页面
     *
     * @return
     */
    @GetMapping("/setGold/{userId}")
    public String setGold(@PathVariable int userId, Model model) {
        StaffProfit staffProfit = staffService.selectStaffProfit(userId,4);
        model.addAttribute("staffProfit", staffProfit);
        return "staff/setGold";
    }

    @GetMapping("/setBasic/{userId}/{money}")
    @ResponseBody
    public ResultCode updateBasic(@PathVariable int userId, @PathVariable double money) {
        ResultCode resultCode = new ResultCode();
        int i = staffService.updateBasic(userId,money);
        if (i > 0) {
            resultCode.setCode(200);
        } else {
            resultCode.setCode(400);
        }
        return resultCode;
    }

    @GetMapping("/setGold/{userId}/{money}/{commission}")
    @ResponseBody
    public ResultCode updateGold(@PathVariable int userId, @PathVariable double money, @PathVariable double commission) {
        ResultCode resultCode = new ResultCode();
        int i = staffService.updateGold(userId,money,commission);
        if (i > 0) {
            resultCode.setCode(200);
        } else {
            resultCode.setCode(400);
        }
        return resultCode;
    }
}
