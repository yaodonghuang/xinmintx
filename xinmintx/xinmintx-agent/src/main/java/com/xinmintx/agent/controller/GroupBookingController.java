package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.GroupBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:.GroupBookingController
 * @author:chf
 * @Date:2019/12/18：14:01
 * @developerKits： win 10     jdk1.8
 */
@Controller
@RequestMapping("/agent/GroupBooking")
public class GroupBookingController {
    @Autowired
    private GroupBookingService bookingService;

    @RequestMapping("addGroup")
    @ResponseBody
    public Map<Object,Object> addGroupBooking(HttpServletRequest request, Integer people, Integer shopingId){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        int userId = user.getId();
        int i=0;
        Map<Object,Object> map = new HashMap<>();
        if (user!=null){
            map = bookingService.addGroupBooking(userId,people,shopingId);
        }
        return map;
    }
}
