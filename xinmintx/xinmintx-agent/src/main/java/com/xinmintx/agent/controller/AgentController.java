package com.xinmintx.agent.controller;
import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.AgentService;
import com.xinmintx.agent.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/agent/agent")
public class AgentController {


    @Autowired
    private AgentService agentService;

    @Autowired
    private PayService payService;
    /**
     * 添加代理
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/addAgent")
    @ResponseBody
    public ResultCode<Map> addAgent(User user, HttpServletRequest request) {
        ResultCode resultCode = new ResultCode();
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1.getUserRole() == 2) {
            user.setPartnerId(user1.getId());
        }
        if (user1.getUserRole() == 5 && user1.getPartnerId() != null) {
            user.setPartnerId(user1.getPartnerId());
        }
        user.setRecommender(user1.getId());
        int i = agentService.addAgent(user);
        if (i > 0) {
            resultCode.setCode(200);
            if (user1.getUserRole() != 1) {
                resultCode.setData(payService.createOrder(user1, CreateRole.AGENT, i));
            }
        } else {
            resultCode.setCode(500);
        }
        return resultCode;

    }

    /**
     * 跳转添加代理页面
     *
     * @return
     */
    @GetMapping("/addAgent")
    public String addAgent() {
        return "agent/agent-add";
    }


    /**
     * 跳转代理页面
     */
    @GetMapping("/listAgent")
    public String listAgent(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<User> agentlist = agentService.selectAgentsByUserid(user.getId());
        model.addAttribute("agentlist", agentlist);
        return "agent/agent-list";
    }

    /**
     * 查看代理页面
     */
    @GetMapping("/userId/{userId}")
    @ResponseBody
    public List<User> selectAgentByUserid(@PathVariable int userId) {
        return agentService.selectAgentsByUserid(userId);
    }

    /**
     * 手机照片上传
     */
    @GetMapping("/photo")
    public String photo() {
        return "photo_test";
    }

    /**
     * 查询合伙人代理团队
     * @return
     */
    @RequestMapping("/partnerTeamList")
    public String partnerTeamList(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        List<ShareholderTeam> list=agentService.partnerTeamList(user.getId());
        model.addAttribute("list",list);
        return "partner/partnerteam";
    }
}
