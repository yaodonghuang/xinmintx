package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.MemberCheckInMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberCheckIn;
import com.xinmintx.hstx.pojo.po.SysCard;
import com.xinmintx.hstx.service.IntegralAccessService;
import com.xinmintx.hstx.service.MemberCheckInService;
import com.xinmintx.hstx.service.MemberIntegralService;
import com.xinmintx.hstx.service.SysCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 加入打卡签到表
 *
 * @author sw 2019/11/20
 */
@Service
public class MemberCheckInServiceImpl implements MemberCheckInService {


    @Autowired
    private MemberCheckInMapper memberCheckInMapper;

    @Autowired
    private IntegralAccessService integralAccessService;

    @Autowired
    private MemberIntegralService memberIntegralService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SysCardService sysCardService;

    /**
     * 根据会员id和文案id获取打卡签到表id
     *
     * @param memberId  会员id
     * @param sysCardId 文案id
     * @return 打卡表id
     */
    @Override
    public int findMemberCheckInId(int memberId, int sysCardId) {
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", memberId);
        map.put("syscard_id", sysCardId);
        List<MemberCheckIn> memberCheckIns = memberCheckInMapper.selectByMap(map);
        if (memberCheckIns == null || memberCheckIns.size() == 0) {
            return 0;
        } else {
            return memberCheckIns.get(0).getId();
        }

    }

    /**
     * 添加打卡
     *
     * @param memberId  会员id
     * @param sysCardId 文案表id
     * @return 打卡表id
     */
    @Override
    public int insertCheck(int memberId, int sysCardId) {
        //查询当天是否已经打过卡
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("DATE_FORMAT(create_time,'%Y-%m-%d')",format);
        hashMap.put("member_id",memberId);
        List<MemberCheckIn> memberCheckIns = memberCheckInMapper.selectByMap(hashMap);

        //走这里说明当天没有打过卡
        if (memberCheckIns == null || memberCheckIns.size() == 0) {
            //插入打卡签到表
            MemberCheckIn memberCheckIn = new MemberCheckIn();
            memberCheckIn.setMemberId(memberId);
            memberCheckIn.setSyscardId(sysCardId);
            memberCheckInMapper.insert(memberCheckIn);
            //获取打卡积分
            double integeral = integralAccessService.getIntegeral(1);
            //插入该会员的积分
            memberIntegralService.insertIntegral(memberId, integeral);
            return memberCheckIn.getId();
            //走这里说明打过卡了
        } else {
            return memberCheckIns.get(0).getId();
        }

    }

    /**
     * 获取打卡天数
     *
     * @param id 打卡记录表id
     * @return 打卡的天数
     */
    @Override
    public int conutCard(int id) {
        //获取打卡记录
        MemberCheckIn memberCheckIn = memberCheckInMapper.selectById(id);
        //获取会员id
        Integer memberId = memberCheckIn.getMemberId();

        QueryWrapper<MemberCheckIn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        //返回打卡天数
        long count = memberCheckInMapper.selectCount(queryWrapper);
        return Integer.parseInt(String.valueOf(count));
    }

    /**
     * 获取打卡人信息 文案 打卡时间
     *
     * @param id 打卡记录表id
     * @return 时间
     */
    @Override
    public List<Object> findDate(Integer id, Integer sysCardId) {
        List<Object> list = new ArrayList<>();
        //查询打卡记录
        MemberCheckIn memberCheckIn = memberCheckInMapper.selectById(id);
        if (memberCheckIn == null) {
            return null;
        }
        //获取打卡时间
        Date createTime = memberCheckIn.getCreateTime();

        list.add(createTime);
        //获取打卡人id
        Integer memberId = memberCheckIn.getMemberId();
        //获取会员
        Member member = memberMapper.selectById(memberId);

        list.add(member);

        //获取文案
        SysCard sysCard = sysCardService.findSysCardById(sysCardId);
        list.add(sysCard);
        return list;
    }

    /**
     * 根据主键id查询会员id
     *
     * @param id 打卡表id
     * @return
     */
    @Override
    public Integer findMemberIdById(Integer id) {
        MemberCheckIn memberCheckIn = memberCheckInMapper.selectById(id);
        if (memberCheckIn != null) {
            return memberCheckIn.getMemberId();
        }
        return null;
    }

    @Override
    public Member findMemberIdByCheckInId(Integer dakaId) {
        MemberCheckIn memberCheckIn = memberCheckInMapper.selectById(dakaId);
        return memberMapper.selectById(memberCheckIn.getMemberId());
    }
}
