package com.xinmintx.system.service.impl;

import com.xinmintx.common.core.text.Convert;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.PinYinUtils;
import com.xinmintx.system.domain.Member;
import com.xinmintx.system.domain.MemberCardInfo;
import com.xinmintx.system.mapper.MemberCardInfoMapper;
import com.xinmintx.system.mapper.MemberMapper;
import com.xinmintx.system.service.ISilverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 银卡信息Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class SilverServiceImpl implements ISilverService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberCardInfoMapper memberCardInfoMapper;

    /**
     * 查询银卡信息
     *
     * @param id 银卡信息ID
     * @return 银卡信息
     */
    @Override
    public Member selectSilverById(Long id) {
        return memberMapper.selectMemberById(id);
    }

    @Override
    public Member selectSilverById(Integer id) {
        return null;
    }

    /**
     * 查询银卡信息列表
     *
     * @param member 银卡信息
     * @return 银卡信息
     */
    @Override
    public List<Member> selectSilverList(Member member) {
        member.setMemberType(2L);
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增银卡信息
     *
     * @param member 银卡信息
     * @return 结果
     */
    @Override
    public int insertSilver(Member member) {
        member.setRecommender(1L);
        member.setMemberType(2L);
        Date nowDate = DateUtils.getNowDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.YEAR, 1);
        Date endTime = calendar.getTime();
        member.setCreateTime(nowDate);
        member.setCardIndate(endTime);
        member.setCardStatus(1);
        memberMapper.insertMember(member);
        //添加会员卡信息
        String idcard = member.getIdcard();
        MemberCardInfo info = new MemberCardInfo();
        info.setMemberId(member.getId());
        info.setName(member.getName());
        String hanYuPinyin = PinYinUtils.toHanYuPinyin(member.getName());
        info.setPyCode(hanYuPinyin);
        info.setIdcard(idcard);
        // 截取月份
        String month = idcard.substring(10, 12);
        // 截取天
        String day = idcard.substring(12, 14);
        String birthday = month + "月" + day + "日";
        info.setBirthday(birthday);
        info.setCellphone(member.getCellphone());
        info.setEntityCard(0);
        info.setCardType(2);
        info.setPayStatus(1L);
        info.setStatus(1L);
        info.setGender(member.getGender());
        memberCardInfoMapper.insertMemberCardInfo(info);
        //生成会员卡号(前8位为身份证前6位,7-8,01位男,00位女,9-12为用户生日,13-16为自增/主键)
        StringBuilder builder = new StringBuilder();
        builder.append(idcard, 0, 4);
        builder.append(" ");
        builder.append(idcard, 4, 6);
        if (member.getGender() == 1) {
            builder.append("01");
        }else{
            builder.append("00");
        }
        builder.append(" ");
        builder.append(month);
        builder.append(day);
        builder.append(" ");
        String number = getNumber(String.valueOf(info.getId()));
        builder.append(number);
        info.setCardNumber(builder.toString());
        memberCardInfoMapper.updateMemberCardInfo(info);
        member.setCardId(info.getId());
        return memberMapper.updateMember(member);
    }

    private String getNumber(String id) {
        if (id.length() < 4) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < (4 - id.length()); i++) {
                builder.append(String.valueOf(0));
            }
            builder.append(id);
            return builder.toString();
        } else {
            return id;
        }
    }

    /**
     * 修改银卡信息
     *
     * @param silver 银卡信息
     * @return 结果
     */
    @Override
    public int updateSilver(Member silver) {
        silver.setUpdateTime(DateUtils.getNowDate());
        return memberMapper.updateMember(silver);
    }

    /**
     * 删除银卡信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSilverByIds(String ids) {
        return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银卡信息信息
     *
     * @param id 银卡信息ID
     * @return 结果
     */
    @Override
    public int deleteSilverById(Long id) {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public int deleteSilverByIds(Integer ids) {
        return 0;
    }

    @Override
    public int deleteSilverById(Integer id) {
        return 0;
    }
}
