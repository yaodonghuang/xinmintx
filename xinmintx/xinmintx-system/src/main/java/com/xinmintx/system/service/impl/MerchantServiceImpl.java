package com.xinmintx.system.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.domain.User;
import com.xinmintx.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MerchantMapper;
import com.xinmintx.system.domain.Merchant;
import com.xinmintx.system.service.IMerchantService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商家信息Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询商家信息
     *
     * @param id 商家信息ID
     * @return 商家信息
     */
    @Override
    public Merchant selectMerchantById(Long id) {
        return merchantMapper.selectMerchantById(id);
    }

    /**
     * 查询商家信息列表
     *
     * @param merchant 商家信息
     * @return 商家信息
     */
    @Override
    public List<Merchant> selectMerchantList(Merchant merchant) {
        return merchantMapper.selectMerchantList(merchant);
    }

    /**
     * 新增商家信息
     *
     * @param merchant 商家信息
     * @return 结果
     */
    @Override
    public int insertMerchant(Merchant merchant) {
        merchant.setRecommender(1L);
        merchant.setCreateTime(DateUtils.getNowDate());
        String regionCode = merchant.getRegionCode();
        //获取该区域下的分公司
        User user = userMapper.selectUserByRegionCode(merchant.getRegionCode());
        if (merchant.getThreeType()!=null && merchant.getThreeType().length>0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < merchant.getThreeType().length; i++) {
                if (i == merchant.getThreeType().length - 1) {
                    stringBuffer.append(merchant.getThreeType()[i]);
                } else {
                    stringBuffer.append(merchant.getThreeType()[i] + ",");
                }
            }
            merchant.setMerchantCategoryDetail(stringBuffer.toString());
        }
        if (user != null) {
            merchant.setMerchantBranchOfficeId(user.getId());
        } else {
            merchant.setMerchantBranchOfficeId(1L);
        }
        try {
            return merchantMapper.insertMerchant(merchant);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改商家信息
     *
     * @param merchant 商家信息
     * @return 结果
     */
    @Override
    public int updateMerchant(Merchant merchant) {
        merchant.setUpdateTime(DateUtils.getNowDate());
        String regionCode = merchant.getRegionCode();
        StringBuffer stringBuffer = null;
        if (merchant.getThreeType()!=null && merchant.getThreeType().length>0) {
            stringBuffer = new StringBuffer();
            for (int i = 0; i < merchant.getThreeType().length; i++) {
                if (i == merchant.getThreeType().length - 1) {
                    stringBuffer.append(merchant.getThreeType()[i]);
                } else {
                    stringBuffer.append(merchant.getThreeType()[i] + ",");
                }
            }
            merchant.setMerchantCategoryDetail(stringBuffer.toString());
        }

        if (StringUtils.isNotBlank(regionCode)) {
            //获取该地区的分公司
            User user = userMapper.selectUserByRegionCode(regionCode);
            if (user != null) {
                merchant.setMerchantBranchOfficeId(user.getId());
            } else {
                merchant.setMerchantBranchOfficeId(1L);
            }
        }
        return merchantMapper.updateMerchant(merchant);
    }

    /**
     * 删除商家信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerchantByIds(String ids) {
        return merchantMapper.deleteMerchantByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商家信息信息
     *
     * @param id 商家信息ID
     * @return 结果
     */
    @Override
    public int deleteMerchantById(Long id) {
        return merchantMapper.deleteMerchantById(id);
    }

    /**
     * 添加打印机查询审核过的商户信息
     *
     * @param merchant
     * @return
     */
    @Override
    public List<Merchant> selectMerchantLists(Merchant merchant) {
        return merchantMapper.selectMerchants(merchant);
    }

    public List<Merchant> selectMerchant() {
        List<Merchant> list = merchantMapper.selectMerchant();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

}
