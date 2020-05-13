package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.MerchantMapper;
import com.xinmintx.hstx.mapper.ProfitMapper;
import com.xinmintx.hstx.mapper.UnitPhotoMapper;
import com.xinmintx.hstx.mapper.UserMapper;
import com.xinmintx.hstx.pojo.vo.MerchantVo;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.service.MerchantService;
import com.xinmintx.hstx.service.WechatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private UnitPhotoMapper unitPhotoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfitMapper profitMapper;
    @Autowired
    private WechatPayService wechatPayService;
    @Autowired
    private PayConfig payConfig;
    /**
     * 添加商户
     *
     * @param merchantVo
     * @return
     */
    @Transactional
    @Override
    public Merchant addMerchant(MerchantVo merchantVo) {
        String regionCode = merchantVo.getRegionCode();
        Map<String,Object> map = new HashMap<>();
        map.put("region_code",regionCode);
        List<User> users = userMapper.selectByMap(map);
        Merchant merchant = new Merchant();
        merchant.setIsReview(0);
        //姓名
        merchant.setName(merchantVo.getName());
        //身份证号
        merchant.setIdcard(merchantVo.getIdcard());
        //银行卡号
        merchant.setBankCard(merchantVo.getBank());
        //手机号
        merchant.setCellphone(merchantVo.getCellphone());
        //地址号
        merchant.setAddress(merchantVo.getAddress());
        //入驻类型号
        merchant.setMerchantType(merchantVo.getSelect1());
        //地区名称
        merchant.setRegionName(merchantVo.getRegionName());
        //商户类型
        merchant.setMerchantCategory(merchantVo.getSelect2());
        //所属分公司
        if (users.size() != 0) {
            Integer id = users.get(0).getId();
            merchant.setMerchantBranchOfficeId(id);
        } else {
            merchant.setMerchantBranchOfficeId(1);
        }
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cellphone", merchantVo.getCellphone()).or().eq("idcard", merchantVo.getIdcard());
        Merchant merchant1 = merchantMapper.selectOne(queryWrapper);
        if (merchant1 != null) {
            if (merchant1.getIsReview() == 1) {
                return null;
            }else{
                merchant.setId(merchant1.getId());
                merchantMapper.updateById(merchant);
            }
        }else {
            try {
                merchantMapper.insert(merchant);
                Integer merchantId = merchant.getId();
                savePhoto(merchantVo, merchantId);
                return merchant;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 保存图片
     *
     * @param merchantVo
     * @param merchantId
     */
    private void savePhoto(MerchantVo merchantVo, int merchantId) {

        UnitPhoto unitPhoto = new UnitPhoto();
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard1());
        unitPhoto.setType("1");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard2());
        unitPhoto.setType("2");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getBank1());
        unitPhoto.setType("7");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getBank2());
        unitPhoto.setType("8");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard6());
        unitPhoto.setType("6");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard3());
        unitPhoto.setType("3");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard4());
        unitPhoto.setType("4");
        unitPhotoMapper.insert(unitPhoto);
        unitPhoto.setCompanyId(merchantId);
        unitPhoto.setPhotoUrl(merchantVo.getCard5());
        unitPhoto.setType("5");
        unitPhotoMapper.insert(unitPhoto);
    }

    /**
     * 创建支付订单
     *
     * @param merchant
     * @return
     */
    @Override
    public Map createOrder(Merchant merchant, Member member) {
        //查询申请成为商户金额
        Map<String,Object> map = new HashMap<>();
        if (merchant.getMerchantType() == 1) {
            map.put("role","5");
        } else {
            map.put("role","4");
        }
        Profit profit = profitMapper.selectByMap(map).get(0);
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setUserId(merchant.getId());
        wechatPayBo.setPrdDesc(profit.getDescription());
        wechatPayBo.setOrderAmt((long) (profit.getCost() * 100));
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setRetUrl(payConfig.getMerchantNotifyUrl());
        wechatPayBo.setType(6);
        return wechatPayService.unifiedorder(wechatPayBo);
    }
}
