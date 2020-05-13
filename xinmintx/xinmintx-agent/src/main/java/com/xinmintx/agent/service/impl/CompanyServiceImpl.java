package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.MerchantMapper;
import com.xinmintx.agent.mapper.UnitPhotoMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UnitPhotoMapper unitPhotoMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private com.xinmintx.agent.service.WechatNotifyService wechatNotifyService;//同步Member表角色

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public int addCompany(User user, Integer[] photo) {
        user.setUserRole(3);
        if (userMapper.selectByPrimaryKey(user.getRecommender()).getUserRole() == 1) {
            Member member1 = wechatNotifyService.selectMember(user.getCellphone());
            if (member1 == null) {
                member1 = new Member();
                member1.setCellphone(user.getCellphone());//手机号
                member1.setUserId(user.getId());//代理表id
                member1.setIsReview(1);
                member1.setMemberType(2);
                member1.setGiftStart(1);
                wechatNotifyService.addMember(member1);
            } else {
                //卡等级判断
                if (member1.getMemberType() < 2) {
                    member1.setMemberType(2);
                }
                member1.setGiftStart(1);
                memberMapper.updateByPrimaryKeySelective(member1);
            }
            user.setStatus(1);
            user.setIsReview(1);
        } else {
            user.setStatus(2);
            user.setIsReview(0);
        }
        //截取区域代码
        String regionCode = user.getRegionCode();
        String code = regionCode.split(",")[2];
        user.setRegionCode(code);
        //查询改区域代码下是否存在分公司
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andStatusEqualTo(1);
        userExampleCriteria.andIsReviewEqualTo(1);
        userExampleCriteria.andRegionCodeEqualTo(user.getRegionCode());
        List<User> userList = userMapper.selectByExample(userExample);
        //已存在
        if (userList.size() > 0) {
            return -1;
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(user.getCellphone());
        criteria.andStatusNotEqualTo(1);
        criteria.andIsReviewNotEqualTo(1);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            int id = users.get(0).getId();
            //删除分公司之前的图片
            UnitPhotoExample unitPhotoExample = new UnitPhotoExample();
            UnitPhotoExample.Criteria unitPhotoExampleCriteria = unitPhotoExample.createCriteria();
            unitPhotoExampleCriteria.andCompanyIdEqualTo(id);
            unitPhotoMapper.deleteByExample(unitPhotoExample);
            user.setId(id);
            userMapper.updateByPrimaryKeySelective(user);
            //保存图片
            savePhoto(photo, id);
            //获取该区域所有的商户
            Merchant merchant = new Merchant();
            merchant.setMerchantBranchOfficeId(id);
            MerchantExample merchantExample = new MerchantExample();
            MerchantExample.Criteria merchantExampleCriteria = merchantExample.createCriteria();
            merchantExampleCriteria.andRegionCodeEqualTo(user.getRegionCode());
            merchantMapper.updateByExampleSelective(merchant, merchantExample);
            return id;
        } else {
            try {
                userMapper.insertSelective(user);
                int id = user.getId();
                savePhoto(photo, id);
                //获取该区域所有的商户
                Merchant merchant = new Merchant();
                merchant.setMerchantBranchOfficeId(id);
                MerchantExample merchantExample = new MerchantExample();
                MerchantExample.Criteria merchantExampleCriteria = merchantExample.createCriteria();
                merchantExampleCriteria.andRegionCodeEqualTo(user.getRegionCode());
                merchantMapper.updateByExampleSelective(merchant, merchantExample);
                return id;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * 保存图片
     *
     * @param photo
     * @param id
     */
    private void savePhoto(Integer[] photo, Integer id) {
        List<Integer> ids = Arrays.asList(photo);
        UnitPhotoExample unitPhotoExample = new UnitPhotoExample();
        UnitPhotoExample.Criteria unitPhotoExampleCriteria = unitPhotoExample.createCriteria();
        unitPhotoExampleCriteria.andIdIn(ids);
        UnitPhoto unitPhoto = new UnitPhoto();
        unitPhoto.setCompanyId(id);
        unitPhotoMapper.updateByExampleSelective(unitPhoto, unitPhotoExample);
    }

    @Override
    public List<User> selectCompanyByUserid(int userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(3);
        criteria.andRecommenderEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return userMapper.selectByExample(example);
    }
}
