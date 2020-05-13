package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.mchpay.MchPayNotify;
import com.xinmintx.agent.mchpay.WechatResult;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.ShareService;
import com.xinmintx.agent.service.UOrderService;
import com.xinmintx.agent.service.WechatNotifyService;
import com.xinmintx.agent.util.MapUtil;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xinmintx.agent.util.MapUtil.generateSign;
import static javax.swing.UIManager.get;


/**
 * Created by qiuyue on 7/5/16.
 */
@Service(value = "wechatNotifyService")
public class WechatNotifyServiceImpl implements WechatNotifyService {

    Logger log = LoggerFactory.getLogger(WechatNotifyServiceImpl.class);

    @Autowired
    private UOrderMapper uOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private CommodityTypologyMapper typologyMapper;

    @Autowired
    private UOrderService uOrderService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private GoodCommodityMapper goodCommodityMapper;

    @Autowired
    private ProfitMapper profitMapper;

    @Value("${wechat.key}")
    private String wechatKey;

    @Transactional
    @Override
    public WechatResult wechatNotify(MchPayNotify mchPayNotify) {
        if (mchPayNotify.getResult_code().equals(WechatResult.SUCCESS)) {
            UOrderExample example = new UOrderExample();
            UOrder order = new UOrder();
            UOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOutTradeNoEqualTo(mchPayNotify.getOut_trade_no());
            List<UOrder> orderList = uOrderMapper.selectByExample(example);
            if (orderList != null && orderList.size() > 0) {
                order = orderList.get(0);
                if (!"0".equals(order.getPayStatus())) {
                    return new WechatResult(WechatResult.SUCCESS);
                } else {
                    Map<String, String> map = MapUtil.objectToMap(mchPayNotify);
                    String sign = generateSign(map, wechatKey);
                    if (sign != null && mchPayNotify.getSign() != null && sign.equals(mchPayNotify.getSign())) {
                        if (mchPayNotify.getResult_code().equals(WechatResult.SUCCESS)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                            try {
                                order.setPayTime(sdf.parse(mchPayNotify.getTime_end()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            order.setPayStatus("1");
                            order.setTransactionId(mchPayNotify.getTransaction_id());
                            order.setPayTime(new Date());
                            order.setPayType("1");
                            try {
                                //回调自定义
                                uOrderService.updateUorder(order);
                                int id = order.getId();
                                String goodsDesc = order.getGoodsDesc();
                                ProfitExample profitExample = new ProfitExample();
                                ProfitExample.Criteria profitExampleCriteria = profitExample.createCriteria();
                                profitExampleCriteria.andDescriptionEqualTo(goodsDesc);
                                Profit profit = profitMapper.selectByExample(profitExample).get(0);
                                String role = profit.getRole();
                                int userId = 0;
                                if ("1".equals(role) || "2".equals(role) || "3".equals(role)) {
                                    UserExample userExample = new UserExample();
                                    UserExample.Criteria userExampleCriteria = userExample.createCriteria();
                                    userExampleCriteria.andOrderIdEqualTo(id);
                                    User user = userMapper.selectByExample(userExample).get(0);
                                    user.setStatus(1);
                                    user.setIsReview(1);
                                    userMapper.updateByPrimaryKeySelective(user);
                                    userId = user.getRecommender();//推荐人
                                   //查询member表是否有代理
                                    Member member1 = selectMemberByUserId(id);
                                    if( member1 == null){
                                        member1 = new Member();
                                        member1.setCellphone(user.getCellphone());//手机号
                                        member1.setUserId(user.getId());//代理表id
                                        member1.setIsReview(1);
                                        addMember(member1);
                                    }
                                } else if ("4".equals(role) || "5".equals(role)) {
                                    MerchantExample merchantExample = new MerchantExample();
                                    MerchantExample.Criteria merchantExampleCriteria = merchantExample.createCriteria();
                                    merchantExampleCriteria.andOrderIdEqualTo(id);
                                    Merchant merchant = merchantMapper.selectByExample(merchantExample).get(0);
                                    merchant.setIsReview(1);
                                    merchantMapper.updateByPrimaryKeySelective(merchant);
                                    userId = merchant.getRecommender();

                                } else {
                                    MemberExample memberExample = new MemberExample();
                                    MemberExample.Criteria memberExampleCriteria = memberExample.createCriteria();
                                    memberExampleCriteria.andOrderIdEqualTo(id);
                                    Member member = memberMapper.selectByExample(memberExample).get(0);
                                    member.setIsReview(1);
                                    memberMapper.updateByPrimaryKeySelective(member);
                                    userId = member.getRecommender();
                                }
                                User user = userMapper.selectByPrimaryKey(userId);
                                shareService.shareBenefit(user, role, order);

                                //开户

                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException();
                            }
                        }
                    } else {
                        log.error("wechatNotify check sign fail ! wechatNotify:{} sign:{} ,mchPayNotify.sign:{} ",
                                mchPayNotify, sign, mchPayNotify.getSign());
                        return new WechatResult(WechatResult.FAIL);
                    }
                }
            } else {
                return new WechatResult(WechatResult.FAIL);
            }
        } else {
            return new WechatResult(WechatResult.FAIL);
        }
        return new WechatResult(WechatResult.SUCCESS);
    };
    /**
     * 商品微信支付回调
     * @param mchPayNotify
     * @return
     */
    @Transactional
    @Override
    public WechatResult wechatMerchantPayNotify(MchPayNotify mchPayNotify) {
        if (mchPayNotify.getResult_code().equals(WechatResult.SUCCESS)) {
            UOrderExample example = new UOrderExample();
            UOrder uOrder = new UOrder();
            UOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOutTradeNoEqualTo(mchPayNotify.getOut_trade_no());
            List<UOrder> orderList = uOrderMapper.selectByExample(example);
            if (orderList != null && orderList.size() > 0) {
                uOrder = orderList.get(0);
                if (!"0".equals(uOrder.getPayStatus())) {
                    return new WechatResult(WechatResult.SUCCESS);
                } else {
                    Map<String, String> map = MapUtil.objectToMap(mchPayNotify);
                    String sign = generateSign(map, wechatKey);
                    if (sign != null && mchPayNotify.getSign() != null && sign.equals(mchPayNotify.getSign())) {
                        if (mchPayNotify.getResult_code().equals(WechatResult.SUCCESS)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                            try {
                                uOrder.setPayTime(sdf.parse(mchPayNotify.getTime_end()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            uOrder.setPayStatus("1");
                            uOrder.setTransactionId(mchPayNotify.getTransaction_id());
                            uOrder.setPayTime(new Date());
                            uOrder.setPayType("1");
                            try {
                                uOrderService.updateUorder(uOrder);//修改订单状态为 付款成功
                                //减库存
                                int typologyId = goodCommodityMapper.getCommodityTypology(uOrder.getId());
                                CommodityTypology commodityTypology = typologyMapper.selectByPrimaryKey(typologyId);
                                int repertory = commodityTypology.getKindRepertory()-1;
                                commodityTypology.setKindRepertory(repertory);
                                typologyMapper.updateByPrimaryKey(commodityTypology);
                                //加销量
                                Commodity commodity = goodCommodityMapper.selectGoodCommodity(uOrder.getGoodsDesc());
                                commodity.setSalesVolume(commodity.getSalesVolume()+1);
                                commodityMapper.updateByPrimaryKey(commodity);

                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException();
                            }
                        }
                    } else {
                        log.error("wechatNotify check sign fail ! wechatNotify:{} sign:{} ,mchPayNotify.sign:{} ",
                                mchPayNotify, sign, mchPayNotify.getSign());
                        return new WechatResult(WechatResult.FAIL);
                    }
                }
            } else {
                return new WechatResult(WechatResult.FAIL);
            }
        } else {
            return new WechatResult(WechatResult.FAIL);
        }
        return new WechatResult(WechatResult.SUCCESS);
    }


    /**
     * 查看member是否存在 此代理角色
     * @param cellPhone
     * @return
     */
    @Override
    public Member  selectMember(String cellPhone){
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andCellphoneEqualTo(cellPhone);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(members.size()>0){
            return members.get(0);
        }
        return null;
    }

    public Member  selectMemberByUserId(int id){
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(members.size()>0){
            return members.get(0);
        }
        return null;
    }

    /**
     * 添加member角色
     * @param member
     */
    @Override
    public void addMember(Member member){
        memberMapper.insertSelective(member);
    }
}