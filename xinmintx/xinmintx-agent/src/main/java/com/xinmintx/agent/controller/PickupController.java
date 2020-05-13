package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.PayService;
import com.xinmintx.agent.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/pickup")
public class PickupController {

    @Autowired
    private PayService payService;

    @Autowired
    private PickupService pickupService;

    /**
     * 跳转页面
     * @return
     */
    @GetMapping("/jumpPick")
    public String jumpPick() {
        return "pickup/pick-add";
    }
    /**提货点升级代理
     * @param request
     * @return
     */
    @PostMapping("/pickuPupgrade")
    @ResponseBody
    public ResultCode pickuPupgrade(HttpServletRequest request) {
        ResultCode resultCode = new ResultCode();
        User user = (User) request.getSession().getAttribute("user");
        resultCode.setCode(200);
        resultCode.setData(payService.createOrder(user, CreateRole.AGENT, user.getId()));
        return resultCode;
    }

    /**
     * 查看提货点
     *
     * @return
     */
    @GetMapping("/listPickUp")
    public String selectStaffByUserid(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<User> agentlist = pickupService.selectpickupByUserid(user.getId());
        model.addAttribute("agentlist", agentlist);
        return "pickup/pick-list";
    }
    /**
     * 添加
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/addPickUp")
    @ResponseBody
    public ResultCode addPickUp(HttpServletRequest request, User user) {
        ResultCode resultCode = new ResultCode();
        User user1 = (User) request.getSession().getAttribute("user");
        user.setRecommender(user1.getId());
        int i = pickupService.addPickUp(user);
        if (i > 0) {
            resultCode.setCode(200);
        } else {
            resultCode.setCode(400);
        }
        return resultCode;
    }
}
