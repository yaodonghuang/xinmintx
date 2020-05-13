package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.FactoryMapper;
import com.xinmintx.hstx.mapper.MerchantMapper;
import com.xinmintx.hstx.pojo.po.Factory;
import com.xinmintx.hstx.pojo.po.Merchant;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.SettleInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
@Transactional
public class SettleInServiceImpl implements SettleInService {
    @Resource
    private FactoryMapper factoryMapper;
    @Resource
    private MerchantMapper merchantMapper;
    /**
     * 商户入驻接口
     * @param merchant
     * @return
     */
    @Override
    public ResultCode merchantSettleIn(Merchant merchant) {
            ResultCode code = new ResultCode();
            Merchant newMerchant = new Merchant();
            Merchant one = new LambdaQueryChainWrapper<>(merchantMapper)
                    .eq(Merchant::getCellphone,merchant.getCellphone()).one();
            if (one!=null){
                code.setCode(200);
                code.setMsg("商户已存在");
                return code;
            }
            newMerchant.setMerchantName(merchant.getMerchantName());
            newMerchant.setName(merchant.getName());
            newMerchant.setCellphone(merchant.getCellphone());
            newMerchant.setRegionCode(merchant.getRegionCode());
            newMerchant.setLatitude(merchant.getLatitude());
            newMerchant.setLongitude(merchant.getLongitude());
            newMerchant.setAddress(merchant.getAddress());
            newMerchant.setShopSpecification(merchant.getShopSpecification());
            newMerchant.setMerchantCategory(merchant.getMerchantCategory());
            newMerchant.setBusinessModel(merchant.getBusinessModel());
            newMerchant.setMerchantType(merchant.getMerchantType());
            newMerchant.setDoorHeadPhoto(merchant.getDoorHeadPhoto());
            newMerchant.setStorePhotoOne(merchant.getStorePhotoOne());
            newMerchant.setStorePhotoTwo(merchant.getStorePhotoTwo());
            newMerchant.setBusinessLicense(merchant.getBusinessLicense());
            if (null!=merchant.getMerchantCategoryDetail()&&merchant.getMerchantCategoryDetail().length()>0){
                newMerchant.setMerchantCategoryDetail(merchant.getMerchantCategoryDetail());
            }
            boolean result = newMerchant.insert();
        if (result){
            code.setCode(200);
            code.setMsg("入驻成功");
        }else {
            code.setCode(200);
            code.setMsg("入驻失败");
        }
        return code;
    }

    /**
     *  工厂入驻接口
     * @param factory
     * @return
     */
    @Override
    public ResultCode factorySettleIn(Factory factory) {
        ResultCode resultCode = new ResultCode();
        Factory newFactory = new Factory();
        Factory one = new LambdaQueryChainWrapper<>(factoryMapper)
                .eq(Factory::getPhone, factory.getPhone()).one();
        if (one!=null){
            resultCode.setCode(200);
            resultCode.setMsg("工厂已存在");
            return resultCode;
        }

        newFactory.setName(factory.getName());
        newFactory.setAddress(factory.getAddress());
        newFactory.setPersonname(factory.getPersonname());
        newFactory.setPhone(factory.getPhone());
        newFactory.setDoorHeadPhoto(factory.getDoorHeadPhoto());
        newFactory.setFactoryPhoto(factory.getFactoryPhoto());
        newFactory.setWorkPhoto(factory.getWorkPhoto());
        newFactory.setBusinessLicense(factory.getBusinessLicense());
        newFactory.setLongitude(factory.getLongitude());
        newFactory.setLatitude(factory.getLatitude());
        newFactory.setLocationCode(factory.getLocationCode());
        newFactory.setFactoryScale(factory.getFactoryScale());
        newFactory.setFactoryProduct(factory.getFactoryProduct());
        newFactory.setBrand(factory.getBrand());

        boolean result = newFactory.insert();
        if (result){
            resultCode.setCode(200);
            resultCode.setMsg("入驻成功");
        }else {
            resultCode.setCode(200);
            resultCode.setMsg("入驻失败");
        }
        return resultCode;
    }
}
