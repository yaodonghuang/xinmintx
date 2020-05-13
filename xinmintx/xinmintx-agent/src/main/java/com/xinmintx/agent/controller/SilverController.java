package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.model.Member;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.PayService;
import com.xinmintx.agent.service.SilverService;
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
@RequestMapping("/agent/silver")
public class SilverController {
    @Autowired
    private SilverService silverService;

    @Autowired
    private PayService payService;
    /**
     * purchase_price
     *
     * @param request
     * @param member
     * @return
     */
    @RequestMapping("/addSilver")
    @ResponseBody
    public ResultCode addSilver(HttpServletRequest request, Member member) {
        ResultCode resultCode = new ResultCode();
        User user = (User) request.getSession().getAttribute("user");
        member.setRecommender(user.getId());
        member.setMemberType(2);
        int i = silverService.addSilver(member);
        if (i > 0){
            resultCode.setCode(200);
            if (user.getUserRole() != 1) {
                resultCode.setData(payService.createOrder(user, CreateRole.SILVER_MEMBER, i));
            }
        }else{
            resultCode.setCode(500);
        }
        return resultCode;
    }

    /**
     * 跳转添加新民金卡页面
     *
     * @return
     */
    @GetMapping("/addSilver")
    public String addSilver() {
        return "silver/silver-add";
    }

    /**
     * 跳转到新民金卡页面
     */
    @GetMapping("/listSilver")
    public String listSilver(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Member> silverList = silverService.selectSilverByUserid(user.getId());
        model.addAttribute("silverList", silverList);
        return "silver/silver-list";
    }

    /**
     * 查询新民金卡
     *
     * @return
     */
    @GetMapping("/userId/{userId}")
    @ResponseBody
    public List<Member> selectSilverByUserid(@PathVariable int userId) {
        return silverService.selectSilverByUserid(userId);
    }
}
