package com.xinmintx.factory.api.controller;


import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.mapper.VenderMapper;
import com.xinmintx.factory.api.pojo.Factory;
import com.xinmintx.factory.api.service.VenderLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: com.xinmintx.api.controller.VenderLoginController
 * @Author:Pikachu
 * @Date: 2019/12/3 9:51
 * @Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/vender")
public class VenderLoginController {
    @Resource
    private VenderLoginService venderLoginService;
    @Autowired
    private Jedis jedis;
    @Resource
    private VenderMapper venderMapper;


    /**
     * 登陆
     * @param phone
     * @param code
     * @param request
     * @return
     */

    @RequestMapping("/login")
    public ResultCode login(@RequestParam("phone") String phone, @RequestParam("code") String code, HttpServletRequest request) {
        ResultCode<Factory> resultCode = new ResultCode<Factory>();
        Integer result = venderMapper.selectIphone(phone);
        if(result == null){
            resultCode.setCode(800);
            resultCode.setMsg("手机号未注册");
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        String code1 = jedis.get(phone);
        if (code.equals(code1)) {
            Factory vender = venderLoginService.venderLogin(phone);
            request.getSession().setAttribute("vender", vender);
            //登陆成功删除redis缓存
            jedis.del(phone);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(vender);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("验证码错误");
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }


    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping("/getCode/{phone}")
    public ResultCode send(@PathVariable String phone) {
        ResultCode code = new ResultCode();
        String sendcode = venderLoginService.sendcode(phone);
        if ("SUCCESS".equals(sendcode)) {
            code.setCode(200);
            code.setMsg("SUCCESS");
        } else {
            code.setCode(500);
            code.setMsg("FAIL");
        }

        return code;
    }

    /**
     * 是否存在token
     * @param token
     * @return
     */
    @RequestMapping("/getToken")
    public ResultCode getToken(@RequestHeader("token")String token){
        Factory factory = venderMapper.queryByToken(token);
        ResultCode resultCode = new ResultCode<>();
        if (factory != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(factory);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }

        return resultCode;
    }

}
