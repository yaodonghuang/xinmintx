package com.xinmintx.hstx.aspect;

import com.alibaba.fastjson.JSON;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.util.EmojiUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/7/10 0010
 * @time: 下午 13:34
 * @Description: 日志管理
 */
@Aspect
@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static long startTime;
    private static long endTime;

    @Pointcut("execution(public * com.xinmintx.hstx.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("========================================== Start ==========================================");
        logger.info("   URL:              {}", request.getRequestURL().toString());
        logger.info("   HttpMethod:       {}", request.getMethod());
        logger.info("   token:            {}", request.getHeader("token"));
        Object[] args = joinPoint.getArgs();
        try {
            if (args == null || args.length < 1 || args[0] instanceof MultipartFile) {
                logger.info("   Param:            {}", args);
            } else {
                logger.info("   Param:            {}", JSON.toJSONString(args));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(value = "webLog()", returning = "object")
    public void doAfter(Object object) {
        endTime = System.currentTimeMillis() - startTime;
        try {
            logger.info("   Response:         {}", JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("   Time:             {}mm", endTime);
        logger.info("=========================================== End ===========================================");
    }

}

