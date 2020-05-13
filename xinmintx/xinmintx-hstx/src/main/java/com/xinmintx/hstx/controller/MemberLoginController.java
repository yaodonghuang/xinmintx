package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.MemberCheckInService;
import com.xinmintx.hstx.service.MemberLoginService;
import com.xinmintx.hstx.util.OpenIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Slf4j

@RestController
@RequestMapping("/hs/member")
public class MemberLoginController extends BaseController {
    @Autowired
    private MemberLoginService memberLoginService;

    @Autowired
    private Jedis jedis;
    @Autowired
    private OpenIdUtils openIdUtils;
    @Autowired
    private IMemberService IMemberService;
    @Autowired
    private MemberCheckInService memberCheckInService;

    /**
     * 判断 是否会员 登陆
     *
     * @param
     * @return
     */
    @RequestMapping("/login")
    public ResultCode login(MemberVo memberVo) {
        ResultCode<Member> resultCode = new ResultCode<>();
        if (StringUtils.isBlank(memberVo.getOpenid())) {
            resultCode.setCode(500);
            resultCode.setMsg("未获取到微信授权信息");
            return resultCode;
        }
        String note = jedis.get(memberVo.getPhone());
        if (memberVo.getCode().equals(note)) {
            MemberVo member = memberLoginService.memberLogin(memberVo);
            //登陆成功删除redis缓存
            jedis.del(memberVo.getPhone());
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(member);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("验证码错误");
        }
        return resultCode;
    }

    /**
     * 保存登录之后的区域code
     *
     * @param
     * @return
     */
    @RequestMapping("/saveRegion")
    public ResultCode login(String regionCode) {
        ResultCode rc = new ResultCode();
        if (member != null) {
            member.setRegionCode(regionCode);
            IMemberService.updateMember(member);
            rc.setCode(200);
            rc.setMsg("SUCCESS");
            rc.setData(member);
        } else {
            rc.setCode(500);
            rc.setMsg("FAIL");
            rc.setData("用户不存在!");
        }
        return rc;
    }

    /**
     * 判断点击打卡分享链接当前人是否是会员 并关联分享人
     *
     * @param phone           手机号
     * @param code            验证码
     * @param memberCheckInId 打卡记录表id
     * @return
     */
    @RequestMapping("/shareLogin")
    public ResultCode shareLogin(@RequestParam("phone") String phone, @RequestParam("code") String code, @RequestParam("memberCheckInId") Integer memberCheckInId) {
        ResultCode<Member> resultCode = new ResultCode<>();
        String code1 = jedis.get(phone);
        if (code.equals(code1)) {
            Member member = memberLoginService.shareLogin(phone, memberCheckInId);
            //登陆成功删除redis缓存
            jedis.del(phone);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(member);
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
        String sendcode = memberLoginService.sendcode(phone);
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
     * 微信授权保存用户信息
     *
     * @param code
     * @return
     */
    @PostMapping("/getOpenid")
    public ResultCode selectMemberByCode(@RequestParam("code") String code) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (member != null) {
            Map memberInfo = openIdUtils.getUserInfo(code);
            if (memberInfo != null) {
                member.setOpenid(memberInfo.get("openid").toString());
                member.setAvatarUrl(memberInfo.get("headimgurl").toString());
                if (member.getGender() == null) {
                    member.setGender((Integer) memberInfo.get("sex"));
                }
                if (StringUtils.isBlank(member.getName())) {
                    member.setName(memberInfo.get("nickname").toString());
                }
                IMemberService.updateMember(member);
                resultCode.setData(token);
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
            }
        }
        return resultCode;
    }

    /**
     * 授权保存用户信息,返回openid
     *
     * @param code
     * @return
     */
    @PostMapping("/getOpenidByCode")
    public ResultCode<Object> getOpenidByCode(@RequestParam("code") String code) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(600);
        resultCode.setMsg("获取授权信息失败");
        Map memberInfo = openIdUtils.getUserInfo(code);
        if (memberInfo != null) {
            Member member = IMemberService.selectMemberByOpenid(memberInfo.get("openid").toString());
            if (member != null) {
                member.setOpenid(memberInfo.get("openid").toString());
                member.setAvatarUrl(memberInfo.get("headimgurl").toString());
                member.setGender((Integer) memberInfo.get("sex"));
                member.setName(memberInfo.get("nickname").toString());
                if (StringUtils.isNotBlank(member.getToken())) {
                    resultCode.setCode(201);
                    resultCode.setMsg("登陆成功");
                    resultCode.setData(member);
                } else {
                    resultCode.setCode(200);
                    resultCode.setMsg("授权成功");
                    resultCode.setData(member);
                }
                member.updateById();
            } else {
                member = new Member();
                member.setOpenid(memberInfo.get("openid").toString());
                member.setAvatarUrl(memberInfo.get("headimgurl").toString());
                member.setGender((Integer) memberInfo.get("sex"));
                member.setName(memberInfo.get("nickname").toString());
                resultCode.setCode(200);
                resultCode.setMsg("授权成功");
                resultCode.setData(member);
            }
        }
        return resultCode;
    }

    /**
     * 根据微信授权,保存用户信息,绑定用户推荐关系
     *
     * @param code
     * @return
     */
    @PostMapping("/bindMember")
    public ResultCode bindMember(@RequestParam("code") String code, @RequestParam("memberId") Integer memberId) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        Map memberInfo = openIdUtils.getUserInfo(code);
        if (memberInfo != null) {
            Member member = new Member();
            member.setOpenid(memberInfo.get("openid").toString());
            member.setAvatarUrl(memberInfo.get("headimgurl").toString());
            member.setGender((Integer) memberInfo.get("sex"));
            member.setName(memberInfo.get("nickname").toString());
            Member consumer = IMemberService.insertOrSelect(member, memberId);
            Map<String, String> map = new HashMap<>();
            if (StringUtils.isNotBlank(consumer.getToken())) {
                map.put("token", consumer.getToken());
                resultCode.setCode(202);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(map);
            } else {
                map.put("openid", memberInfo.get("openid").toString());
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(map);
            }
        }
        return resultCode;
    }

    /**
     * 根据code获取用户是否关注公众号,是否注册过
     *
     * @param code
     * @return
     */
    @PostMapping("/getUserInfoByCode")
    public ResultCode getUserInfoByCode(@RequestParam("code") String code, @RequestParam("dakaId") Integer dakaId) {
        ResultCode<Object> resultCode = new ResultCode<>();
        Map resultMap = new HashMap();
        Map map = openIdUtils.getUnionID(code);
        if (map != null) {
            if (map.containsKey("openid")) {
                resultMap.put("subscribe", map.get("subscribe"));
                Member one = new Member();
                one.setOpenid(map.get("openid").toString());
                Member memberIdByCheckInId = memberCheckInService.findMemberIdByCheckInId(dakaId);
                Member member = IMemberService.insertOrSelect(one, memberIdByCheckInId.getId());
                if (StringUtils.isNotBlank(member.getToken())) {
                    resultMap.put("member", true);
                    resultMap.put("token", member.getToken());
                } else {
                    resultMap.put("member", false);
                }
            }
        }
        resultCode.setData(resultMap);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 用户退出
     *
     * @return ResultCode
     */
    @GetMapping("/loginOut")
    public ResultCode loginOut() {
        return IMemberService.loginOut(member);
    }
}
