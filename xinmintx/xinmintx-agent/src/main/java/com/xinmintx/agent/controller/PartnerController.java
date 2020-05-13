package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.PartnerService;
import com.xinmintx.agent.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PayService payService;
    /**
     * 添加合伙人
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/addPartner")
    @ResponseBody
    public ResultCode addPartner(HttpServletRequest request, User user) {
        ResultCode resultCode = new ResultCode();
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1.getUserRole() == 7){
            user.setShareholderId(user1.getId());
        }
        if(user1.getShareholderId() != null){
            user.setShareholderId(user1.getShareholderId());
        }
        user.setRecommender(user1.getId());
        int i = partnerService.addPartner(user);
        //判断添加用户是否为boss 不是boss  需要创建订单并且支付
        if (i > 0) {
            resultCode.setCode(200);
            if (user1.getUserRole() != 1) {
                resultCode.setData(payService.createOrder(user1, CreateRole.PARTNER, i));
            }
        }else{
            resultCode.setCode(500);
        }
        return resultCode;
    };

    /**
     * 跳转添加合伙人页面
     *
     * @return
     */
    @GetMapping("/addPartner")
    public String addPartner() {
        return "partner/partner-add";
    }

    /**
     * 跳转代理页面
     */
    @GetMapping("/listPartner")
    public String listAgent(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<User> partnerlist = partnerService.selectPartnerByUserid(user.getId());
        model.addAttribute("partnerlist", partnerlist);
        return "partner/partner-list";
    }

    /**
     * 查看代理页面
     */
    @GetMapping("/userId/{userId}")
    public List<User> selectAgentByUserid(@PathVariable int userId) {
        return partnerService.selectPartnerByUserid(userId);
    }

    /**
     * 通过手机号搜索合伙人
     */
    @GetMapping("/select/{phone}")
    @ResponseBody
    public ResultCode<User> selectPartnerByCellphone(@PathVariable String phone) {
        ResultCode<User> resultCode = new ResultCode<>();
        User user = partnerService.selectPartnerByCellphone(phone);
        if (user == null) {
            resultCode.setCode(500);
        } else {
            resultCode.setData(user);
            resultCode.setCode(200);
        }
        return resultCode;
    }

    /**
     * 通过手机号搜索合伙人
     */
    @GetMapping("/getParterPhone")
    @ResponseBody
    public User getParterPhone(@RequestParam("cellphone") String cellphone) {
        User user = partnerService.selectPartnerAndShareholder(cellphone);
        return user;
    }
}
