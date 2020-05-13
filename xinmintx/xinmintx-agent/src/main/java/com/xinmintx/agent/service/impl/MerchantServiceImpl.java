package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.mapper.MerchantMapper;
import com.xinmintx.agent.mapper.UnitPhotoMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.Merchant;
import com.xinmintx.agent.model.MerchantExample;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.MerchantService;
import com.xinmintx.agent.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private PayService payService;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private UnitPhotoMapper unitPhotoMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加商户
     *
     * @param user
     * @param merchant
     * @return
     */
    @Override
    public ResultCode addMerchant(User user, Merchant merchant) {
        merchant.setRecommender(user.getId());
        ResultCode resultCode = addMerchant(merchant);
        if (resultCode.getCode() == 200 && user.getUserRole() != 1) {
            Map<String, Object> order = null;
            int merchantType = merchant.getMerchantType();
            if (merchantType == 1) {
                //普通商户
                order = payService.createOrder(user, CreateRole.GENERAL_MERCHANTS, user.getId());
            } else if (merchantType == 2) {
                //黄金商户
                order = payService.createOrder(user, CreateRole.GOLD_MERCHANTS, user.getId());
            } else if (merchantType == 3) {
                //黄金商户
                order = payService.createOrder(user, CreateRole.COMMUNITY_MERCHANTS, user.getId());
            }
            if (user.getUserRole() == 1) {
                //boss修改审核状态
                merchant.setIsReview(1);
                merchantMapper.updateByPrimaryKeySelective(merchant);
            }
            resultCode.setData(order);
        }
        return resultCode;
    }

    /**
     * 添加商户
     *
     * @param merchant
     * @return
     */
    private ResultCode addMerchant(Merchant merchant) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (userMapper.selectByPrimaryKey(merchant.getRecommender()).getUserRole() == 1) {
            merchant.setIsReview(1);
        } else {
            merchant.setIsReview(0);
        }
        //获取区域代码
        String regionCode = merchant.getRegionCode();
        String code = regionCode.split(",")[2];
        merchant.setRegionCode(code);
        //按区域代码获取分公司
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andUserRoleEqualTo(3);
        userExampleCriteria.andRegionCodeEqualTo(merchant.getRegionCode());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            merchant.setMerchantBranchOfficeId(users.get(0).getId());
        } else {
            merchant.setMerchantBranchOfficeId(1);
        }
        MerchantExample merchantExample = new MerchantExample();
        MerchantExample.Criteria merchantExampleCriteria = merchantExample.createCriteria();
        merchantExampleCriteria.andCellphoneEqualTo(merchant.getCellphone());
        merchantExampleCriteria.andIdcardEqualTo(merchant.getIdcard());
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        if (merchants.size() > 0) {
            Merchant merchant1 = merchants.get(0);
            if (merchant1.getIsReview() != null && merchant1.getIsReview() == 1) {
                //商户已存在
                resultCode.setMsg("商户已存在");
                return resultCode;
            } else {
                //修改商户信息
                merchant.setId(merchant1.getId());
                merchantMapper.updateByPrimaryKeySelective(merchant);
            }
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            return resultCode;
        } else {
            try {
                //添加商户信息表
                merchantMapper.insertSelective(merchant);
                resultCode.setCode(200);
                return resultCode;
            } catch (Exception e) {
                e.printStackTrace();
                return resultCode;
            }
        }
    }

    @Override
    public List<Merchant> selectMerchantByUserid(int userId) {
        MerchantExample example = new MerchantExample();
        MerchantExample.Criteria criteria = example.createCriteria();
        criteria.andRecommenderEqualTo(userId);
        criteria.andIsReviewEqualTo(1);
        return merchantMapper.selectByExample(example);
    }
}
