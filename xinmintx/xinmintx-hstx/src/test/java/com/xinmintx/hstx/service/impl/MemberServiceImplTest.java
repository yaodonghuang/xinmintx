package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import com.xinmintx.hstx.pojo.po.MemberReferrer;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.service.IMemberService;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/11 0011
 * @time: 上午 10:36
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceImplTest {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private IMemberService memberService;

    @Test
    public void test1() {
        List<Member> members = memberMapper.selectByMap(null);
        //给用户随机生成随机数量的推荐账号
        members.forEach(member -> {
            for (int i = 0; i < getNumber(); i++) {
                Member one = new Member();
                one.setCellphone(getCellPhone());
                one.setGender(1);
                one.setMemberType(1);
                one.setIsReview(1);
                one.setCreateTime(new Date());
                one.setUpdateTime(new Date());
                one.setBirthday(new Date());
                one.setCardId(0);
                one.setCardStatus(1);
                one.setCardIndate(new Date());
                one.insert();
                MemberCardInfo memberCardInfo = new MemberCardInfo();
                memberCardInfo.setMemberId(one.getId());
                memberCardInfo.setOpenid("1");
                memberCardInfo.setCardNumber("1");
                memberCardInfo.setName("1");
                memberCardInfo.setPyCode("1");
                memberCardInfo.setGender(0);
                memberCardInfo.setIdcard("1");
                memberCardInfo.setBirthday("1");
                memberCardInfo.setCellphone("1");
                memberCardInfo.setAddress("1");
                memberCardInfo.setEntityCard(0);
                memberCardInfo.setCardType(1);
                memberCardInfo.setStatus(1);
                memberCardInfo.setCreateTime(new Date());
                memberCardInfo.setUpdateTime(new Date());
                memberCardInfo.insert();
                one.setCardId(memberCardInfo.getId());
                one.updateById();
                MemberReferrer referrer = new MemberReferrer();
                referrer.setReferrerId(member.getId());
                referrer.setMemberId(one.getId());
                referrer.setStatus(1);
                referrer.insert();
            }
        });
    }

    /**
     * 随机生成手机号
     *
     * @return
     */
    public String getCellPhone() {
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 0; i < 10; i++) {
            builder.append((int) (Math.random() * 9 + 1));
        }
        return builder.toString();
    }

    /**
     * 随机生成一个数字
     *
     * @return
     */
    public int getNumber() {
        return (int) (Math.random() * 9 + 1);
    }

    @Test
    public void getMembersByECard() throws ParseException {
        List<MemberVo> membersByECard = memberService.getMembersByECard(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-17 17:28:17"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-19 00:00:00"));
        System.out.println(JSON.toJSONStringWithDateFormat(membersByECard, "yyyy-MM-dd HH:mm:ss",
                SerializerFeature.DisableCircularReferenceDetect));
    }

    @Test
    public void getMemberByECard() throws ParseException {
        Member member = memberMapper.selectById(46);
        MemberVo memberByECard = memberService.getMemberByECard(member,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-17 17:28:17"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-19 00:00:00"));
        System.out.println(JSON.toJSONStringWithDateFormat(memberByECard, "yyyy-MM-dd HH:mm:ss",
                SerializerFeature.DisableCircularReferenceDetect));
    }

}
