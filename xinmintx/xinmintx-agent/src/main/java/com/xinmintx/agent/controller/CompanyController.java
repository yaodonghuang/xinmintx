package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.Region;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.CompanyService;
import com.xinmintx.agent.service.ReginService;
import com.xinmintx.agent.service.RoleShareService;
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
@RequestMapping("agent/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RoleShareService roleShareService;

    @Autowired
    private ReginService reginService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加分公司
     *
     * @param request
     * @param user    partnerId 为合作人id
     * @param photo
     * @return
     */
    @RequestMapping("/addCompany")
    @ResponseBody
    public ResultCode addCompany(HttpServletRequest request, User user, Integer[] photo) {
        ResultCode code = new ResultCode();
        User user1 = (User) request.getSession().getAttribute("user");
        user.setRecommender(user1.getId());
        int userId = companyService.addCompany(user, photo);
        if (userId == -1) {
            code.setCode(500);
            code.setMsg("该区域已存在分公司");
        } else if (userId == 0) {
            code.setCode(500);
            code.setMsg("添加失败,账号已存在");
        } else {
            //改变该地区的状态设为有分公司
            String regionName = user.getRegionName().split(",")[1];
            if (regionName != null) {
                Region region = reginService.selectRegin(regionName);
                if (region != null) {
                    reginService.updateReginStart(region);
                }
            }
            //分公司添加成功,合伙人获取奖励
            if (user.getPartnerId() != null) {
                User referrer = userMapper.selectByPrimaryKey(user.getPartnerId());
                roleShareService.shareProfit(referrer, CreateRole.COMPANY, null);
            }
            code.setCode(200);
            code.setMsg("添加成功");
        }
        return code;
    }

    /**
     * 跳转添加分公司页面
     *
     * @return
     */
    @GetMapping("/addCompany")
    public String addCompany() {
        return "company/company-add";
    }

    /**
     * 跳转到分公司页面
     */
    @GetMapping("/listCompany")
    public String listCompany(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<User> companylist = companyService.selectCompanyByUserid(user.getId());
        model.addAttribute("companylist", companylist);
        return "company/company-list";
    }

    /**
     * 查询分公司
     */
    @GetMapping("/userId/{userId}")
    @ResponseBody
    public List<User> selectCompanyByUserid(@PathVariable int userId) {
        return companyService.selectCompanyByUserid(userId);
    }

}
