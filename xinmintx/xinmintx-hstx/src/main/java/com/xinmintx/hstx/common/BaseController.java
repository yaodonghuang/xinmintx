package com.xinmintx.hstx.common;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/9 0009
 * @time: 上午 11:25
 * @Description:
 */
public class BaseController {

    @Autowired
    private IMemberService memberService;

    protected String token;
    protected Member member;

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
        this.token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            this.member = memberService.findMemberByToken(token);
        }
    }
}
