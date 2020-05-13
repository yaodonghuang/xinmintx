package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserAccountRecord;
import com.xinmintx.agent.service.StudentService;
import com.xinmintx.agent.service.UserAccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName:.DetailController
 * @author:chf
 * @Date:2020/1/16：14:03
 * @developerKits： win 10     jdk1.8
 */
@Controller
@RequestMapping("/user/detail")
public class DetailController {
    @Autowired
    private UserAccountRecordService accountRecordService;


    /**
     * 余额明细查询
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/queryDetail")
    public String queryDetail(HttpServletRequest request, Model map){
        User user =(User) request.getSession().getAttribute("user");
        List<UserAccountRecord> userAccountRecords = accountRecordService.queryDetail(user.getId());
        map.addAttribute("userAR",userAccountRecords);
        return "detail";
    }

}
