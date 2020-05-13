package com.xinmintx.hstx.service.impl;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.mapper.MemberUserMiddenMapper;
import com.xinmintx.hstx.mapper.UserMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberUserMidden;
import com.xinmintx.hstx.pojo.po.User;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.service.IMemberTreeService;
import com.xinmintx.hstx.service.MemberCheckInService;
import com.xinmintx.hstx.service.MemberLoginService;
import com.xinmintx.hstx.util.FieldUtils;
import com.xinmintx.hstx.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MemberLoginServiceImpl implements MemberLoginService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private Jedis jedis;
    @Autowired
    private IMemberTreeService memberTreeService;
    @Autowired
    private MemberCheckInService memberCheckInService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberUserMiddenMapper memberUserMiddenMapper;

    @Override
    public MemberVo memberLogin(MemberVo memberVo) {
        memberVo.setCellphone(memberVo.getPhone());
        //按手机号查询用户
        Member memberByPhone = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getCellphone, memberVo.getCellphone())
                .one();
        //按openid查询用户信息
        Member memberByOpenid = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getOpenid, memberVo.getOpenid())
                .one();
        String token = UUID.randomUUID().toString();
        if (memberByPhone != null) {
            if (StringUtils.isNotBlank(memberByPhone.getOpenid())) {
                //当前手机账号有openid
                if (memberByPhone.getOpenid().equals(memberVo.getOpenid())) {
                    //openid相同,登陆自己账号
                    memberByPhone.setName(memberVo.getName());
                    memberByPhone.setGender(memberVo.getGender());
                    memberByPhone.setAvatarUrl(memberVo.getAvatarUrl());
                    //更新token
                    memberByPhone.setToken(token);
                    memberByPhone.updateById();
                    //添加矩阵
                    setTreeNumForMember(memberByPhone.getId());
                    //有token,正常登陆
                    return FieldUtils.fieldTrans(memberByPhone, MemberVo.class);
                } else {
                    //openid不同,登陆他人账号
                    if (memberByOpenid != null) {
                        //该openid有其他账号
                        //删除原本账号的openid
                        memberByOpenid.setOpenid("");
                        memberByOpenid.updateById();
                    }
                    memberByPhone.setName(memberVo.getName());
                    memberByPhone.setGender(memberVo.getGender());
                    memberByPhone.setAvatarUrl(memberVo.getAvatarUrl());
                    //该openid无其他账号
                    memberByPhone.setToken(token);
                    memberByPhone.setOpenid(memberVo.getOpenid());
                    memberByPhone.updateById();
                    //添加矩阵
                    setTreeNumForMember(memberByPhone.getId());
                    //有token,正常登陆
                    return FieldUtils.fieldTrans(memberByPhone, MemberVo.class);
                }
            } else {
                //当前手机账号无openid
                if (memberByOpenid != null) {
                    //该openid有其他账号
                    if (StringUtils.isNotBlank(memberByOpenid.getCellphone())) {
                        //其他账号有手机号
                        //删除原本账号的openid
                        memberByOpenid.setOpenid("");
                        memberByOpenid.updateById();
                    } else {
                        //其他账号无手机号
                        //删除
                        memberByOpenid.deleteById();
                    }
                }
                memberByPhone.setName(memberVo.getName());
                memberByPhone.setGender(memberVo.getGender());
                memberByPhone.setAvatarUrl(memberVo.getAvatarUrl());
                //该openid无其他账号
                memberByPhone.setToken(token);
                memberByPhone.setOpenid(memberVo.getOpenid());
                memberByPhone.updateById();
                //添加矩阵
                setTreeNumForMember(memberByPhone.getId());
                //有token,正常登陆
                return FieldUtils.fieldTrans(memberByPhone, MemberVo.class);
            }
        } else {
            //未获取到当前手机号的账号
            if (memberByOpenid != null) {
                //该openid有其他账号
                memberByOpenid.setCellphone(memberVo.getCellphone());
                memberByOpenid.setName(memberVo.getName());
                memberByOpenid.setGender(memberVo.getGender());
                memberByOpenid.setAvatarUrl(memberVo.getAvatarUrl());
                memberByOpenid.setToken(token);
                memberByOpenid.updateById();
                //添加矩阵
                setTreeNumForMember(memberByOpenid.getId());
                return FieldUtils.fieldTrans(memberByOpenid, MemberVo.class);
            }
            //该openid有其他账号,新用户
            Member member = new Member();
            member.setToken(token);
            member.setIsReview(1);
            member.setMemberType(0);
            member.setCellphone(memberVo.getCellphone());
            member.setOpenid(memberVo.getOpenid());
            member.setName(memberVo.getName());
            member.setAvatarUrl(memberVo.getAvatarUrl());
            member.setGender(memberVo.getGender());
            member.insert();
            setTreeNumForMember(member.getId());
            return FieldUtils.fieldTrans(member, MemberVo.class);
        }
    }

    public void setTreeNumForMember(Integer memberId) {
        memberTreeService.setTreeNumForMember(memberId, "");
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @Override
    public String sendcode(String phone) {

        //1 生成6位随机数
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //存入缓存
        jedis.set(phone, code);
        log.info("phone=" + phone + ";msCode=" + code);
        //30分钟过期
        jedis.expire(phone, 1800);
        try {
            SendSmsResponse response = smsUtil.sendSms(phone, "SMS_176440093", "惠商", "{\"code\":\"" + code + "\"}");
            String responseCode = response.getCode();
            log.info("msUtil=" + JSON.toJSONString(response));
            if ("OK".equals(responseCode)) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        } catch (ClientException e) {
            e.printStackTrace();

        }
        return "FAIL";
    }

    @Override
    public Member shareLogin(String phone, Integer memberCheckInId) {
        Map<String, Object> map = new HashMap<>();
        map.put("cellphone", phone);
        String uuid = UUID.randomUUID().toString();
        List<Member> members = memberMapper.selectByMap(map);
        if (members.size() > 0) {
            Member member = members.get(0);
            if (StringUtils.isBlank(member.getToken())) {
                member.setToken(uuid);
                memberMapper.updateById(member);
                return member;
            }
            return member;
        } else {
            Member member = new Member();
            member.setCellphone(phone);
            member.setIsReview(1);
            member.setMemberType(0);
            member.setToken(uuid);
            memberMapper.insert(member);
            if (member.getId() != null) {
                memberTreeService.setTreeNumForMember(member.getId(), "");
            }
            //获取会员id
            Integer memberId = memberCheckInService.findMemberIdById(memberCheckInId);
            if (memberId != null) {
                String cellphone = memberMapper.selectById(memberId).getCellphone();
                if (StringUtils.isNoneBlank(cellphone)) {
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("cellphone", cellphone);
                    userMap.put("status", 1);
                    userMap.put("is_review", 1);
                    List<User> users = userMapper.selectByMap(userMap);
                    if (users != null && users.size() > 0) {
                        User user = users.get(0);
                        Map<String, Object> memberMap = new HashMap<>();
                        memberMap.put("user_id", user.getId());
                        memberMap.put("member_id", member.getId());
                        List<MemberUserMidden> memberUserMiddens = memberUserMiddenMapper.selectByMap(memberMap);
                        if (memberUserMiddens == null || memberUserMiddens.size() == 0) {
                            MemberUserMidden memberUserMidden = new MemberUserMidden();
                            memberUserMidden.setUserId(user.getId());
                            memberUserMidden.setMemberId(member.getId());
                            memberUserMiddenMapper.insert(memberUserMidden);
                        }
                    }
                }
            }
            return member;
        }
    }


}
