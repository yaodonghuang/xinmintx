package com.xinmintx.agent.service.impl;
import java.util.Date;

import com.xinmintx.agent.mapper.AgentPtBenefitMapper;
import com.xinmintx.agent.mapper.UserAccountMapper;
import com.xinmintx.agent.mapper.UserAccountRecordMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.AgentPtBenefitService;
import com.xinmintx.agent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AgentPtBenefitServiceImpl implements AgentPtBenefitService {

    @Autowired
    private AgentPtBenefitMapper agentPtBenefitMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    /**
     *
     * @param regimentalId 团长id
     * @param ptUserId 拼团id
     * @return
     */
    @Transactional
    @Override
    public String AgentGroupDistribution(Integer regimentalId,Integer ptUserId) {
        //1团长 pid 2 拼团中间表
        //获取团中 每位团员购买的代理价  确定每位付款金额good_ptcode_info
        List<GoodPtcodeInfo> goodPtcodeInfo = agentPtBenefitMapper.selectgoodPtcodeInfo(regimentalId);
        //获取每个Sku 商品代理价 计算出这一单 代理的利润
        double sumMonney = getSumMonney(goodPtcodeInfo);
        //创建账户信息 记录 账户信息
        //中间表中获取 代理id
        GoodsPtUser goodsPtUser = agentPtBenefitMapper.selectGoodsPtUser(ptUserId);
        //查询账户表 是否存在账户
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
        criteria.andUserIdEqualTo(goodsPtUser.getUserId());
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        UserAccountRecord userAccountRecord = new UserAccountRecord();
      //有账号直接加 并做记录
        if(userAccounts.size() > 0){
            userAccountRecord.setUserId(goodsPtUser.getUserId());
            userAccountRecord.setUserAccountId(userAccounts.get(0).getId());
            userAccountRecord.setMoneyChange(new BigDecimal(sumMonney));
            userAccountRecord.setCreateTime(DateUtils.getNowDate());
            insertUserAccountRecord(userAccountRecord);
            userAccounts.get(0).setMoney(userAccounts.get(0).getMoney()+sumMonney);
            userAccountMapper.updateByPrimaryKeySelective( userAccounts.get(0));
            return "成功加润";
        }
        //如果没有账户
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(goodsPtUser.getUserId());
        userAccount.setMoney(sumMonney);
        userAccountMapper.insertSelective(userAccount);
        userAccountRecord.setUserId(goodsPtUser.getUserId());
        userAccountRecord.setUserAccountId(userAccount.getId());
        userAccountRecord.setMoneyChange(new BigDecimal(sumMonney));

        userAccountRecord.setCreateTime(DateUtils.getNowDate());
        insertUserAccountRecord(userAccountRecord);
        return "开户并加润";
    };
    /**
     * 记录加利润表信息
     * @param userAccountRecord
     */
    public void insertUserAccountRecord(UserAccountRecord userAccountRecord){
        userAccountRecordMapper.insertSelective(userAccountRecord);
    }

    /**
     * 获取总利润
     * @param goodPtcodeInfo
     * @return
     */
    public double getSumMonney(List<GoodPtcodeInfo> goodPtcodeInfo){
        double sum = 0.0;
        for (GoodPtcodeInfo ptcodeInfo : goodPtcodeInfo) {
            GoodsSku goodsSku = agentPtBenefitMapper.selectSkuAgentMoney(ptcodeInfo.getSkuId());
            //一件商品的利润
            BigDecimal onePrice = ptcodeInfo.getPerPrice().subtract(goodsSku.getAgentPrice());
            sum += onePrice.doubleValue();
        }
        return sum;
    }
    /**
     * 判断订单是否创建成功
     * @param regimentalId
     * @return
     */
    @Override
    public GoodPtcode selectgoodPtcode(Integer regimentalId) {
        return agentPtBenefitMapper.selectgoodPtcode(regimentalId);
    }

}
