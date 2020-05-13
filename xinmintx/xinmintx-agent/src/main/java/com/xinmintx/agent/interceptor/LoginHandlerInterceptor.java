package com.xinmintx.agent.interceptor;

import com.xinmintx.agent.annotation.DisableAuth;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/7/15 0015
 * @time: 下午 14:44g
 * @Description: 未登录拦截器
 */
@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            //获取方法上是否存在自定义注解
            DisableAuth disableAuth = method.getMethodAnnotation(DisableAuth.class);
            //存在自定义放行注解,放行接口
            if (disableAuth != null){
                return true;
            }
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/");
            return false;

        }
        return true;
    }
}
