package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.MemberCardOrderDetail;
import com.xinmintx.system.domain.MemberCardOrderDetailExample;
import com.xinmintx.system.mapper.MemberCardOrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MemberCardOrderMapper;
import com.xinmintx.system.domain.MemberCardOrder;
import com.xinmintx.system.service.IMemberCardOrderService;
import com.xinmintx.common.core.text.Convert;

/**
 * 会员卡订单Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-15
 */
@Service
public class MemberCardOrderServiceImpl implements IMemberCardOrderService 
{
    @Autowired
    private MemberCardOrderMapper memberCardOrderMapper;

    @Autowired
    private MemberCardOrderDetailMapper memberCardOrderDetailMapper;

    /**
     * 查询会员卡订单
     * 
     * @param id 会员卡订单ID
     * @return 会员卡订单
     */
    @Override
    public MemberCardOrder selectMemberCardOrderById(Long id)
    {
        return memberCardOrderMapper.selectMemberCardOrderById(id);
    }

    /**
     * 查询会员卡订单列表
     * 
     * @param memberCardOrder 会员卡订单
     * @return 会员卡订单
     */
    @Override
    public List<MemberCardOrder> selectMemberCardOrderList(MemberCardOrder memberCardOrder)
    {
        return memberCardOrderMapper.selectMemberCardOrderList(memberCardOrder);
    }

    /**
     * 新增会员卡订单
     * 
     * @param memberCardOrder 会员卡订单
     * @return 结果
     */
    @Override
    public int insertMemberCardOrder(MemberCardOrder memberCardOrder)
    {
        memberCardOrder.setCreateTime(DateUtils.getNowDate());
        return memberCardOrderMapper.insertMemberCardOrder(memberCardOrder);
    }

    /**
     * 修改会员卡订单
     * 
     * @param memberCardOrder 会员卡订单
     * @return 结果
     */
    @Override
    public int updateMemberCardOrder(MemberCardOrder memberCardOrder)
    {
        memberCardOrder.setUpdateTime(DateUtils.getNowDate());
        return memberCardOrderMapper.updateMemberCardOrder(memberCardOrder);
    }

    /**
     * 删除会员卡订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberCardOrderByIds(String ids)
    {
        return memberCardOrderMapper.deleteMemberCardOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员卡订单信息
     * 
     * @param id 会员卡订单ID
     * @return 结果
     */
    @Override
    public int deleteMemberCardOrderById(Long id)
    {
        return memberCardOrderMapper.deleteMemberCardOrderById(id);
    }

    /**
     * 查询会员卡订单详情
     *
     * @param id  会员卡订单ID
     * @return 结果
     */
    @Override
    public List<MemberCardOrderDetail> selectMemberCardOrderDetail(int id) {
        MemberCardOrderDetailExample example = new MemberCardOrderDetailExample();
        MemberCardOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andCardOrderIdEqualTo(id);
        return memberCardOrderDetailMapper.selectByExample(example);
    }

    /**
     * 添加或修改快递单号
     *
     * @param id 主键id
     * @param courierNumber  快递号
     */
    @Override
    public int insertCourierNumber(long id, String courierNumber) {
        MemberCardOrder memberCardOrder = memberCardOrderMapper.selectMemberCardOrderById(id);
        memberCardOrder.setCourierNumber(courierNumber);
        int i = memberCardOrderMapper.updateMemberCardOrder(memberCardOrder);
        return i;
    }
}
