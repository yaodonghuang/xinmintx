package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.WechatPayBo;
import com.xinmintx.agent.configuration.pay.PayConfig;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.MerchantMapper;
import com.xinmintx.agent.mapper.ProfitMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.PayService;
import com.xinmintx.agent.service.UOrderService;
import com.xinmintx.agent.service.WechatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/15 0015
 * @time: 下午 17:29
 * @Description: 获取支付订单
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private WechatPayService wechatPayService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PayConfig payConfig;
    /**
     * 生成订单
     *
     * @param user   创建订单人
     * @param role 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡,7社区商户)
     * @param userId 被创建人的主键
     * @return
     */
    @Override
    public Map<String, Object> createOrder(User user, int role, int userId) {

        ProfitExample example = new ProfitExample();
        ProfitExample.Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(String.valueOf(role));
        Profit profit = profitMapper.selectByExample(example).get(0);
        if (profit.getCost() == null || profit.getCost() == 0){
            return null;
        }
        Double price = profit.getCost() * 100;
        //TODO 切换支付
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setUserId(user.getId());
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(profit.getDescription());
        wechatPayBo.setOrderAmt(price.longValue());
        wechatPayBo.setOpenId(user.getOpenid());
        switch (role) {
            case CreateRole.PARTNER:
                wechatPayBo.setRetUrl(payConfig.getPartner());
                break;
            case CreateRole.COMPANY:
                wechatPayBo.setRetUrl(null);
                break;
            case CreateRole.AGENT:
                wechatPayBo.setRetUrl(payConfig.getAgent());
                break;
            case CreateRole.GOLD_MERCHANTS:
                wechatPayBo.setRetUrl(payConfig.getGoldMerchant());
            case CreateRole.GENERAL_MERCHANTS:
                wechatPayBo.setRetUrl(payConfig.getGeneralMerchant());
                break;
            case CreateRole.SILVER_MEMBER:
                wechatPayBo.setRetUrl(payConfig.getSilverMember());
                break;
            case CreateRole.COMMUNITY_MERCHANTS:
                wechatPayBo.setRetUrl(payConfig.getCommunityMerchant());
                break;
            default:
                break;
        }
        Map<String, Object> unifiedorder = wechatPayService.unifiedorder(wechatPayBo);
        int orderId = (int) unifiedorder.get("order_id");
        setOrderId(orderId, userId, role);
        return unifiedorder;
    }

    public void setOrderId(Integer orderId, Integer userId, Integer role) {
        switch (role) {
            case CreateRole.PARTNER:
            case CreateRole.COMPANY:
            case CreateRole.AGENT:
                User user = new User();
                user.setOrderId(orderId);
                user.setId(userId);
                userMapper.updateByPrimaryKeySelective(user);
                break;
            case CreateRole.GOLD_MERCHANTS:
            case CreateRole.GENERAL_MERCHANTS:
                Merchant merchant = new Merchant();
                merchant.setOrderId(orderId);
                merchant.setId(userId);
                merchantMapper.updateByPrimaryKeySelective(merchant);
                break;
            case CreateRole.SILVER_MEMBER:
                Member member = new Member();
                member.setOrderId(orderId);
                member.setId(userId);
                memberMapper.updateByPrimaryKeySelective(member);
                break;
            default:
                break;
        }
    }
}
