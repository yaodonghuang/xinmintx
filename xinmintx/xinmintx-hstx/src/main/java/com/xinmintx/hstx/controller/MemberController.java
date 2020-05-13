package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.annotation.DisableAuth;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import com.xinmintx.hstx.pojo.vo.MemberCardOrderVo;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.IMemberTreeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */

@RestController
@RequestMapping("/api/member")
public class MemberController extends BaseController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberTreeService iMemberTreeService;

    /**
     * 根据token查询用户信息
     *
     * @return
     */
    @DisableAuth
    @PostMapping("/getMember")
    public ResultCode selectMemberByToken() {
        ResultCode resultCode = new ResultCode<>();
        if (token == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        MemberVo memberVo = memberService.getMemberByToken(token);
        if (memberVo == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberVo);
        return resultCode;
    }

    /**
     * 消费者支付金额升级为银卡
     *
     * @return 微信订单
     */
    @Deprecated
    @PostMapping("/upgradeSilver")
    public ResultCode upgradeSilver(Integer memberId) {
        ResultCode resultCode = new ResultCode();
        if (member.getMemberType() != 1) {
            resultCode.setMsg("当前用户不是普通卡,不能升级");
            resultCode.setCode(500);
        }
        Map map = memberService.upgradeSilverByUserid(member, memberId);
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(map);
        } else {
            resultCode.setMsg("FAIL");
            resultCode.setCode(500);
        }
        return resultCode;
    }

    /**
     * 保存卡信息,生成订单
     *
     * @param memberCardInfo
     * @return
     */
    @PostMapping("/saveMemberCardInfo")
    public ResultCode saveMemberCardInfo(@RequestBody MemberCardInfo memberCardInfo) {
        return memberService.saveMemberCardInfo(memberCardInfo, member);
    }

    /**
     * 会员卡续费
     *
     * @return
     */
    @PostMapping("memberCardRenew")
    public ResultCode<MemberCardOrderVo> memberCardRenew() {
        ResultCode<MemberCardOrderVo> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        MemberCardOrderVo memberCardOrderVo = memberService.memberCardRenew(member);
        if (memberCardOrderVo != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(memberCardOrderVo);
        }
        return resultCode;
    }

    /**
     * 会员卡制卡
     *
     * @return
     */
    @PostMapping("makeMemberCard")
    public ResultCode<MemberCardOrderVo> makeMemberCard(@RequestBody MemberCardInfo memberCardInfo) {
        return memberService.makeMemberCard(memberCardInfo, member);
    }

    /**
     * 会员卡补卡
     *
     * @return
     */
    @PostMapping("reissueMemberCard")
    public ResultCode<MemberCardOrderVo> reissueMemberCard() {
        return memberService.reissueMemberCard(member);
    }

    /**
     * 获取会员卡订单信息
     *
     * @return
     */
    @DisableAuth
    @PostMapping("getMemberCardOrder/{id}")
    public ResultCode<MemberCardOrderVo> getMemberCardOrder(@PathVariable("id") Integer id) {
        return memberService.getMemberCardOrder(id);
    }


    /**
     * 创建会员卡支付信息
     *
     * @param memberCardOrderVo
     * @return
     */
    @PostMapping("/createMemberCardOrder")
    public ResultCode createMemberCardOrder(@RequestBody MemberCardOrderVo memberCardOrderVo) {
        return memberService.createMemberCardOrder(memberCardOrderVo, member);
    }

    /**
     * 获取下游账号总数
     *
     * @return
     */
    @RequestMapping("/accountNum")
    public ResultCode getNextMemberQty() {
        ResultCode code = new ResultCode();
        Integer totalCount = iMemberTreeService.getNextMemberQty(member.getId());
        totalCount = totalCount == null ? 0 : totalCount;
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("totalCount", totalCount);
        double dataBonusRecord = memberService.getDataBonusRecord(member.getId());
        hashMap.put("dataBonus", dataBonusRecord);
        code.setCode(200);
        code.setMsg("SUCCESS");
        code.setData(hashMap);
        return code;
    }

    /**
     * 获取新民币
     *
     * @return ResultCode
     */
    @GetMapping("/getNewCurrency")
    public ResultCode getNewCurrency() {
        return memberService.getNewCurrencyByToken(token);
    }


    /**
     * 分配礼包
     *
     * @param code 地区代码
     * @return ResultCode
     */
    @DisableAuth
    @PostMapping("/distribution")
    public ResultCode distribution(String code) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)) {
            return resultCode;
        }
        if (member == null) {
            return resultCode;
        }
        return memberService.distribution(member, code);
    }

    /**
     * 查询资金变动记录
     *
     * @return ResultCode
     */
    @GetMapping("/getMemberAmountLog")
    public ResultCode getMemberAmountLog() {
        return memberService.getMemberAmountLog(member);
    }


    /**
     * 查询新名币变动记录
     *
     * @return ResultCode
     */
    @GetMapping("/getMemberCurrencyRecord")
    public ResultCode getMemberCurrencyRecord() {
        return memberService.getMemberCurrencyRecord(member);
    }

    /**
     * 按月查询新名币收入或支出
     *
     * @return ResultCode
     */
    @PostMapping("/getMemberCurrencyRecordInOrOut")
    public ResultCode getMemberCurrencyRecordInOrOut(String date, Integer flag) {
        return memberService.getMemberCurrencyRecordInOrOut(member, date, flag);
    }

    /**
     * 设置支付密码 修改支付密码 忘记支付密码
     *
     * @param code               验证码
     * @param payPassword        支付密码
     * @param confirmPayPassword 确认密码
     * @return ResultCode
     */
    @PostMapping("/updatePayPassword")
    public ResultCode updatePayPassword(String code, String payPassword, String confirmPayPassword) {
        return memberService.updatePayPassword(member, code, payPassword, confirmPayPassword);
    }

    /**
     * 检查用户是否设置过支付密码
     * @return
     */
    @GetMapping("/checkPayPassword")
    public ResultCode checkPayPassword(){
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        if (StringUtils.isNotBlank(member.getPayPassword())) {
            resultCode.setData(true);
        }else{
            resultCode.setData(false);
        }
        return resultCode;
    }
}

