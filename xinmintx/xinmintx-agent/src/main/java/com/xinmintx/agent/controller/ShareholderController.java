package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.ShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/shareholder")
public class ShareholderController {

    @Autowired
    private ShareholderService shareholderService;

    /**
     * 跳转页面shareholder
     *
     * @return
     */
    @GetMapping("/jumpShareholder")
    public String shareholder(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<ShareholderTeam> shareholderTeam = shareholderService.selectShareholder(user);
        model.addAttribute("shareholderTeam", shareholderTeam);
        return "stockholder/shareholder_list";
    }

    /**
     * 跳转团队页面
     *
     * @return
     */
    @GetMapping("/listTeam")
    public String listTeam(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<ShareholderTeam> list = shareholderService.selectTeam(user.getId());
        model.addAttribute("list", list);
        return "stockholder/team";
    }

    /**
     * 升级高级合伙人
     * @param cellphone
     * @return
     */
    @RequestMapping("/updateUserRole")
    @ResponseBody
    public boolean updateUserRole(@RequestParam("cellphone") String cellphone) {
        return shareholderService.updateUserRole(cellphone);
    }

    /**
     * 查询合伙人
     * @param cellphone
     * @param model
     * @return
     */
    @RequestMapping("/jumpShareholderAdd/{cellphone}")
    public String jumpShareholderAdd(@PathVariable String cellphone, Model model) {
        User user = shareholderService.selectPartner(cellphone);
        model.addAttribute("user", user);
        return "stockholder/shareholder_add";
    }

}
