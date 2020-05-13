package com.xinmintx.agent.controller;

import com.xinmintx.agent.annotation.DisableAuth;
import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.modelDTO.NumberMedol;
import com.xinmintx.agent.service.LoginService;
import com.xinmintx.agent.util.OpenIdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/agent/user")
public class UserLoginController {

    @Autowired
    public LoginService service;

    @Autowired
    private Jedis jedis;

    @Autowired
    private OpenIdUtils openIdUtils;

    /**
     * 登陆页面
     *
     * @return
     */
    @DisableAuth
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @DisableAuth
    @PostMapping("/getCode")
    @ResponseBody
    public String sendCode(String phone) {
        return service.sendcode(phone);
    }

    /**
     * 用户登录判断
     *
     * @param phone
     * @param code
     * @return
     */
    @DisableAuth
    @PostMapping("/login")
    @ResponseBody
    public ResultCode userLogin(@RequestParam("phone") String phone, @RequestParam("code") String code, HttpServletRequest request) {
        ResultCode<User> result = new ResultCode<>();
        String smsCode = jedis.get(phone);
        if (code.equals(smsCode)) {
            User user = service.selectByPhone(phone);
            request.getSession().setAttribute("user", user);
            //登陆成功删除redis缓存
            //TODO 正式环境取消注释
            jedis.del(phone);
            result.setCode(200);
            result.setData(user);
            return result;
        } else {
            result.setCode(500);
            result.setMsg("验证码错误！");
            return result;
        }
    }

    /**
     * 登陆成功跳转页面
     *
     * @return
     */
    @GetMapping("/index")
    public String getAgent(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        NumberMedol numberMedol = selectNumberByUserid(user.getId());
        double balance = selectBalanceByUserid(user.getId());
        numberMedol.setId(user.getId());
        numberMedol.setBalance(balance);
        numberMedol.setUsername(user.getName());
        numberMedol.setAvatar(user.getAvatar());
        if (user.getEndTime() != null) {
            numberMedol.setEndTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(user.getEndTime()));
        }
        model.addAttribute("numberMedol", numberMedol);
        return getIndexUrl(user);
    }

    private String getIndexUrl(User user) {

        //如果是代理
        if (user.getUserRole() == 5) {
            service.promotedPartner(user);
        }
        String path = "";
        int userRole = user.getUserRole();
        //老板
        if (userRole == 1) {
            path = "boss/boss-index";
            //合伙人
        } else if (userRole == 2) {
            path = "partner/partner-index";
        } else if (userRole == 3) {
            path = "company/company-index";
        } else if (userRole == 4) {
            path = "founder-index";
        } else if (userRole == 5) {
            path = "agent/agent-index";
        } else if (userRole == 7) {
            path = "stockholder/stockholder";
        } else if (userRole == 6) {
            path = "staff/staff-index";
        }else {
            path = "pickup/pickup";
        }
        return path;
    }

    /**
     * 页面加载时获取openid
     *
     * @return
     */
    @DisableAuth
    @GetMapping("/getOpenid")
    public String getOpenid(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        if (StringUtils.isNotBlank(code)) {
            Map tokenMap = openIdUtils.getAccessToken(code);
            String openid = tokenMap.get("openid").toString();
            User user = service.selectUserByOpenid(openid);
            //该用户已经登陆过
            if (user != null) {
                //用户有token
                if (StringUtils.isNotBlank(user.getRefreshToken())) {
                    Map userInfo = openIdUtils.getUserInfo(openid, tokenMap.get("access_token").toString());
                    if (userInfo != null) {
                        String headimgurl = userInfo.get("headimgurl").toString();
                        //用户修改过头像
                        if (!headimgurl.equals(user.getAvatar())) {
                            //更新用户头像信息
                            user.setAvatar(headimgurl);
                            service.updateUser(user);
                        }
                        request.getSession().setAttribute("user", user);
                        return "redirect:/agent/user/index";
                    } else {
                        //账号存在,但是token超时,需要用户重新授权
                        model.addAttribute("openid", openid);
                        model.addAttribute("userId", user.getId());
                        request.getSession().setAttribute("user", user);
                        return "login/login";
                    }
                } else {
                    //账号存在,但是token不存在,需要用户重新授权
                    model.addAttribute("openid", openid);
                    model.addAttribute("userId", user.getId());
                    request.getSession().setAttribute("user", user);
                    return "login/login";
                }
            } else {
                //账号不存在
                model.addAttribute("openid", openid);
                return "login/login";
            }
        }
        return "login/login";
    }

    /**
     * 微信用户授权获取用户信息
     *
     * @param request
     * @return
     */
    @DisableAuth
    @GetMapping("/getUserInfo")
    public String getUserInfo(HttpServletRequest request) {
        String code = request.getParameter("code");
        User user = (User) request.getSession().getAttribute("user");
        if (StringUtils.isNotBlank(code)) {
            //获取用户信息
            Map userInfo = openIdUtils.getUserInfo(code);
            if (userInfo != null) {
                user.setAvatar(userInfo.get("headimgurl").toString());
                user.setRefreshToken(userInfo.get("refresh_token").toString());
                user.setOpenid(userInfo.get("openid").toString());
                request.getSession().setAttribute("user", user);
                service.updateUser(user);
                return "redirect:/agent/user/index";
            }
        }
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("openid", user.getOpenid());
        user.setOpenid(null);
        service.updateUser(user);
        session.removeAttribute("user");
        return "login/login";
    }

    /**
     * 查询数据量
     */
    private NumberMedol selectNumberByUserid(Integer userId) {
        return service.selectNumberByUserid(userId);
    }

    /**
     * 查询余额
     */
    private double selectBalanceByUserid(Integer userId) {
        return service.selectBalanceByUserid(userId);
    }
}
