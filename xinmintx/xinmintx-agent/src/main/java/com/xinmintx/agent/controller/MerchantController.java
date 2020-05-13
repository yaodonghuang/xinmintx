package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.Merchant;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/13 0013
 * @time: 上午 10:41
 * @Description:
 */
@Controller
@RequestMapping("/agent/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    /**
     * 跳转添加商户页面
     *
     * @return
     */
    @GetMapping("/addMerchant")
    public String addMerchant() {
        return "merchant/merchant-add";
    }

    /**
     * 添加商户
     *
     * @param merchant
     * @return
     */
    @PostMapping("/addMerchant")
    @ResponseBody
    public ResultCode addAgent(Merchant merchant, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return merchantService.addMerchant(user, merchant);
    }

    /**
     * 跳转到商户页面
     */
    @GetMapping("/listMerchant")
    public String listMerchant(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Merchant> merchantList = merchantService.selectMerchantByUserid(user.getId());
        model.addAttribute("merchantList", merchantList);
        return "merchant/merchant-list";
    }

    @GetMapping("/userId/{userId}")
    @ResponseBody
    public List<Merchant> selectMerchantByUserid(@PathVariable int userId) {
        return merchantService.selectMerchantByUserid(userId);
    }
}
