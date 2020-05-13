package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.SysCard;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.MemberCheckInService;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.SysCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sw
 * @date 2019-11-22
 * @Description: 用户打卡
 */

@RestController
@RequestMapping("/api/memberCheckIn")
public class MemberCheckInController extends BaseController {

    @Autowired
    private MemberCheckInService memberCheckInService;

    @Autowired
    private SysCardService sysCardService;

    @Autowired
    private IMemberService IMemberService;

    /**
     * 获取当日时间段美文和音乐路径和图片路径
     *
     * @return 当日文案的集合
     */
    @DisableAuth
    @RequestMapping("/getch")
    public List<SysCard> getch() {
        return sysCardService.findCharactersByDate();
    }

    /**
     * 查询用户和文案
     *
     * @param id 打卡记录表id
     * @return 用户和文案
     */
    @DisableAuth
    @RequestMapping("/getchs")
    public ResultCode getchs(Integer id, Integer sysCardId) {
        ResultCode<Object> resultCode = new ResultCode<>();
        List<Object> list = memberCheckInService.findDate(id, sysCardId);
        if (list == null || list.size() == 0) {
            resultCode.setCode(500);
            resultCode.setMsg("查询失败");
            resultCode.setData(null);
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("查询成功");
        resultCode.setData(list);
        return resultCode;
    }


    /**
     * 用户打卡并加积分关联文案
     *
     * @param sysCardId 文案表id
     * @return 打卡表id
     */
    @RequestMapping("/userPunch")
    public ResultCode userPunch(Integer sysCardId) {
        ResultCode resultCode = new ResultCode();
        //获取会员
        Member member = IMemberService.findMemberByToken(token);
        //检查今日是否已经打过卡,如果没打过卡,就添加数据
        int id = memberCheckInService.insertCheck(member.getId(), sysCardId);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(id);
        return resultCode;
    }


    /**
     * 获取打卡天数
     *
     * @return 打卡天数
     */
    @DisableAuth
    @RequestMapping("/countCard")
    public int countCard(Integer id) {
        //根据会员id查询打卡天数
        return memberCheckInService.conutCard(id);
    }


}

