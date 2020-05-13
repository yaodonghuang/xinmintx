package com.xinmintx.system.service.impl;

import java.util.List;

import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GiftCakeMapper;
import com.xinmintx.system.domain.GiftCake;
import com.xinmintx.system.service.IGiftCakeService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商户生日蛋糕Service业务层处理
 *
 * @author xinmintx
 * @date 2020-01-19
 */
@Service
public class GiftCakeServiceImpl implements IGiftCakeService {
    @Autowired
    private GiftCakeMapper giftCakeMapper;

    /**
     * 查询商户生日蛋糕
     *
     * @param id 商户生日蛋糕ID
     * @return 商户生日蛋糕
     */
    @Override
    public GiftCake selectGiftCakeById(Long id) {
        return giftCakeMapper.selectGiftCakeById(id);
    }

    /**
     * 查询商户生日蛋糕列表
     *
     * @param giftCake 商户生日蛋糕
     * @return 商户生日蛋糕
     */
    @Override
    public List<GiftCake> selectGiftCakeList(GiftCake giftCake) {
        List<GiftCake> giftCakeList = giftCakeMapper.selectGiftCakeList(giftCake);
        // 对返回数据进行处理
        dealResultInfo(giftCakeList);
        return giftCakeList;
    }

    /**
     * 新增商户生日蛋糕
     *
     * @param giftCake 商户生日蛋糕
     * @return 结果
     */
    @Override
    public int insertGiftCake(GiftCake giftCake) {
        giftCake.setCreateTime(DateUtils.getNowDate());
        return giftCakeMapper.insertGiftCake(giftCake);
    }

    /**
     * 修改商户生日蛋糕
     *
     * @param giftCake 商户生日蛋糕
     * @return 结果
     */
    @Override
    public int updateGiftCake(GiftCake giftCake) {
        giftCake.setUpdateTime(DateUtils.getNowDate());
        return giftCakeMapper.updateGiftCake(giftCake);
    }

    /**
     * 删除商户生日蛋糕对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGiftCakeByIds(String ids) {
        return giftCakeMapper.deleteGiftCakeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户生日蛋糕信息
     *
     * @param id 商户生日蛋糕ID
     * @return 结果
     */
    @Override
    public int deleteGiftCakeById(Long id) {
        return giftCakeMapper.deleteGiftCakeById(id);
    }

    // 返回数据处理
    private static void dealResultInfo(List<GiftCake> giftCakeList) {
        if (giftCakeList != null && giftCakeList.size() > 0) {
            giftCakeList.forEach(gc -> {
                if ("artCake".equalsIgnoreCase(gc.getType())) {
                    gc.setType("艺术蛋糕");
                }
            });
        }
    }
}
