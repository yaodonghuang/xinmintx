package com.xinmintx.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.domain.MemberTree;
import com.xinmintx.system.service.IMemberTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberMapper;
import com.xinmintx.system.domain.Member;
import com.xinmintx.system.service.IMemberService;
import com.xinmintx.common.core.text.Convert;

/**
 * 会员卡Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-20
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private IMemberTreeService memberTreeService;

    /**
     * 查询会员卡
     *
     * @param id 会员卡ID
     * @return 会员卡
     */
    @Override
    public Member selectMemberById(Long id) {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员卡列表
     *
     * @param member 会员卡
     * @return 会员卡
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员卡
     *
     * @param member 会员卡
     * @return 结果
     */
    @Override
    public int insertMember(Member member) {
        member.setCreateTime(DateUtils.getNowDate());
        String treeCode = member.getTreeCode();
        member.setTreeCode(null);
        int result = memberMapper.insertMember(member);
        memberTreeService.setTreeNumForMember(member.getId().intValue(), treeCode);
        return result;
    }

    /**
     * 修改会员卡
     *
     * @param member 会员卡
     * @return 结果
     */
    @Override
    public int updateMember(Member member) {
        member.setUpdateTime(DateUtils.getNowDate());
        String treeCode = member.getTreeCode();
        member.setTreeCode(null);
        Member mb = memberMapper.selectMemberById(member.getId());
        if (mb != null) {// 判断会员原本是否有矩阵编码
            if (StringUtils.isEmpty(mb.getTreeCode())) {
                member.setNewCurrency(BigDecimal.ZERO);// 新民币清零
            }
        }
        memberTreeService.setTreeNumForMember(member.getId().intValue(), treeCode);
        int result = memberMapper.updateMember(member);
        return result;
    }

    /**
     * 删除会员卡对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(String ids) {
        memberMapper.updateTreeCodeToNullByIds(Convert.toStrArray(ids));
        return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员卡信息
     *
     * @param id 会员卡ID
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public List<MemberTree> selectTreeCode() {
        return memberMapper.selectTreeCode();
    }
}
