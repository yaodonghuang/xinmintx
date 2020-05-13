package com.xinmintx.agent.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/7/15 0015
 * @time: 下午 14:42
 * @Description:
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor interceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //放行静态文件
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
        //放行请求
        interceptorRegistration.excludePathPatterns("/swagger-ui.html","/login.html", "/css/**", "/fonts/**", "/images/**", "/js/**", "/error", "/api/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        interceptorRegistration.addPathPatterns("/**");
    }

}
