package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.MemberbonusInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  奖金池controller
 * @author hyd
 */
@RestController
@RequestMapping("/hs/memberbonus")
public class MemberbonusController extends BaseController {
    @Resource
    private MemberbonusInfoService memberbonusInfoService;

    /**
     *  获取实时奖池排名名单
     * @param
     * @return
     */
    @GetMapping("/bonusList")
    public ResultCode bonusList(){
        return memberbonusInfoService.bonusList(member);
    }

    /**
     *  查询截止时间
     * @return
     */
    @GetMapping("/endTime")
    public ResultCode getEndTime(){
        return memberbonusInfoService.getEndTime();
    }

    /**
     * 查询上期信息
     * @return
     */
    @GetMapping("/previousList")
    public ResultCode previousList(){
        return memberbonusInfoService.previousList(member);
    }
}
