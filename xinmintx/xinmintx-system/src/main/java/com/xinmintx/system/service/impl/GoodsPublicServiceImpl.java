package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodsPublicMapper;
import com.xinmintx.system.domain.GoodsPublic;
import com.xinmintx.system.service.IGoodsPublicService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商品详情公共部分配置Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-24
 */
@Service
public class GoodsPublicServiceImpl implements IGoodsPublicService 
{
    @Autowired
    private GoodsPublicMapper goodsPublicMapper;

    /**
     * 查询商品详情公共部分配置
     * 
     * @param id 商品详情公共部分配置ID
     * @return 商品详情公共部分配置
     */
    @Override
    public GoodsPublic selectGoodsPublicById(Long id)
    {
        return goodsPublicMapper.selectGoodsPublicById(id);
    }

    /**
     * 查询商品详情公共部分配置列表
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 商品详情公共部分配置
     */
    @Override
    public List<GoodsPublic> selectGoodsPublicList(GoodsPublic goodsPublic)
    {
        return goodsPublicMapper.selectGoodsPublicList(goodsPublic);
    }

    /**
     * 新增商品详情公共部分配置
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 结果
     */
    @Override
    public int insertGoodsPublic(GoodsPublic goodsPublic)
    {
        goodsPublic.setCreateTime(DateUtils.getNowDate());
        return goodsPublicMapper.insertGoodsPublic(goodsPublic);
    }

    /**
     * 修改商品详情公共部分配置
     * 
     * @param goodsPublic 商品详情公共部分配置
     * @return 结果
     */
    @Override
    public int updateGoodsPublic(GoodsPublic goodsPublic)
    {
        goodsPublic.setUpdateTime(DateUtils.getNowDate());
        return goodsPublicMapper.updateGoodsPublic(goodsPublic);
    }

    /**
     * 删除商品详情公共部分配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPublicByIds(String ids)
    {
        return goodsPublicMapper.deleteGoodsPublicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品详情公共部分配置信息
     * 
     * @param id 商品详情公共部分配置ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPublicById(Long id)
    {
        return goodsPublicMapper.deleteGoodsPublicById(id);
    }
}
