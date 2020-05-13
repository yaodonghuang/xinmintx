package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.GoodPanicBuy;
import com.xinmintx.system.domain.Goods;
import com.xinmintx.system.domain.GoodsType;
import com.xinmintx.system.mapper.GoodPanicBuyMapper;
import com.xinmintx.system.mapper.GoodsMapper;
import com.xinmintx.system.mapper.GoodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.AdvertisingMapper;
import com.xinmintx.system.domain.Advertising;
import com.xinmintx.system.service.IAdvertisingService;
import com.xinmintx.common.core.text.Convert;

/**
 * 广告Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
@Service
public class AdvertisingServiceImpl implements IAdvertisingService 
{
    @Autowired
    private AdvertisingMapper advertisingMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;

    /**
     * 查询广告
     * 
     * @param id 广告ID
     * @return 广告
     */
    @Override
    public Advertising selectAdvertisingById(Long id)
    {
        return advertisingMapper.selectAdvertisingById(id);
    }

    /**
     * 查询广告列表
     * 
     * @param advertising 广告
     * @return 广告
     */
    @Override
    public List<Advertising> selectAdvertisingList(Advertising advertising)
    {
        return advertisingMapper.selectAdvertisingList(advertising);
    }

    /**
     * 新增广告
     * 
     * @param advertising 广告
     * @return 结果
     */
    @Override
    public int insertAdvertising(Advertising advertising)
    {
        advertising.setCreateTime(DateUtils.getNowDate());
        return advertisingMapper.insertAdvertising(advertising);
    }

    /**
     * 修改广告
     * 
     * @param advertising 广告
     * @return 结果
     */
    @Override
    public int updateAdvertising(Advertising advertising)
    {
        advertising.setUpdateTime(DateUtils.getNowDate());
        return advertisingMapper.updateAdvertising(advertising);
    }

    /**
     * 删除广告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdvertisingByIds(String ids)
    {
        return advertisingMapper.deleteAdvertisingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告信息
     * 
     * @param id 广告ID
     * @return 结果
     */
    @Override
    public int deleteAdvertisingById(Long id)
    {
        return advertisingMapper.deleteAdvertisingById(id);
    }

    @Override
    public List<GoodsType> selectTypes() {
        return goodsTypeMapper.select();
    }

    @Override
    public Goods selectGood(Long relateId) {
        return goodsMapper.selectGoodsById(relateId);
    }

    @Override
    public GoodPanicBuy selectGoodPanicBuy(Long relateId) {
        return goodPanicBuyMapper.selectGoodPanicBuyById(relateId);
    }
}
