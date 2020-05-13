package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sw
 * @date 2019-11-21
 * @Description: 用户点赞
 */

@RestController
@RequestMapping("/api/userLike")
public class UserLikeController extends BaseController {

    @Autowired
    private IMemberService IMemberService;

    @Autowired
    private MemberCheckInService memberCheckInService;

    @Autowired
    private LikeNumListService likeNumListService;


    /**
     * 用户点赞
     *
     * @param id 打卡记录表id
     * @return 点赞是否成功的状态
     */
    @RequestMapping("/addUserLike")
    public ResultCode addUserLike(Integer id) {

        //获取点赞人对象
        Member member = IMemberService.findMemberByToken(token);

        //添加点赞并返回状态
        ResultCode resultCodes = likeNumListService.putLike(id, member.getName(), member.getId());
        return resultCodes;

    }

    /**
     * 获取点赞数
     *
     * @param id 打卡表id
     * @return 点赞数
     */
    @DisableAuth
    @RequestMapping("/countLike")
    public ResultCode countLike(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("查询成功");
        if (token == null) {
            List<Object> like = likeNumListService.countLike(id, null);
            resultCode.setData(like);
        } else {
            //根据会员名获取会员id
            Member member = IMemberService.findMemberByToken(token);
            if (member == null) {
                List<Object> like = likeNumListService.countLike(id, null);
                resultCode.setData(like);
            } else {
                //获取点赞数
                List<Object> like = likeNumListService.countLike(id, member.getName());
                resultCode.setData(like);
            }
        }
        return resultCode;
    }


    /**
     * 获取最近点赞人名字
     *
     * @param sysCardId 打卡表id
     * @return 点赞人名字
     */
    @RequestMapping("/lookLike")
    public ResultCode lookLike(int sysCardId) {
        ResultCode<Member> resultCode = new ResultCode<>();
        Member member = IMemberService.findMemberByToken(token);

        int memberCheckInId = memberCheckInService.findMemberCheckInId(member.getId(), sysCardId);

        String name = likeNumListService.likeName(memberCheckInId);
        if (name == null) {
            resultCode.setCode(500);
            resultCode.setMsg("暂时无人");
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg(name);
        return resultCode;
    }


}
