package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.CommentVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.CheckInCommentService;
import com.xinmintx.hstx.service.IntegralAccessService;
import com.xinmintx.hstx.service.MemberIntegralService;
import com.xinmintx.hstx.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author sw
 * @date 2019-11-22
 * @Description: 用户评论
 */

@RestController
@RequestMapping("/api/userComment")
public class UserCommentController extends BaseController {


    @Autowired
    private IMemberService IMemberService;

    @Autowired
    private CheckInCommentService checkInCommentService;

    @Autowired
    private IntegralAccessService integralAccessService;

    @Autowired
    private MemberIntegralService memberIntegralService;


    /**
     * 用户添加评论
     *
     * @param id          打卡记录表id
     * @param comment     评论
     * @return 状态
     */
    @RequestMapping("/comment")
    public ResultCode comment(Integer id, String comment) {
        ResultCode resultCode = new ResultCode();

        //获取会员对象
        Member commentMember = IMemberService.findMemberByToken(token);

        //加入评论
        checkInCommentService.insertComment(id, commentMember.getId(), comment);
        //获取评论积分
        double integeral = integralAccessService.getIntegeral(2);
        //加入积分
        memberIntegralService.insertIntegral(commentMember.getId(),integeral);
        resultCode.setCode(200);
        resultCode.setMsg("评论成功");
        return resultCode;
    }


    /**
     * 获取评论列表
     *
     * @param id     打卡记录表id
     * @return       评论集合
     */
    @DisableAuth
    @RequestMapping("/getComment")
    public List<CommentVo> getComment(Integer id) {
        //查询评论
        return checkInCommentService.listComment(id);
    }
}
