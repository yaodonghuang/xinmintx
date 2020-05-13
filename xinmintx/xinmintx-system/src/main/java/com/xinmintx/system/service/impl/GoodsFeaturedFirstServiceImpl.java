package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.BaseFeatredFirst;
import com.xinmintx.system.mapper.BaseFeatredFirstMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodsFeaturedFirstMapper;
import com.xinmintx.system.domain.GoodsFeaturedFirst;
import com.xinmintx.system.service.IGoodsFeaturedFirstService;
import com.xinmintx.common.core.text.Convert;

/**
 * 首页推荐位置商品Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
@Service
public class GoodsFeaturedFirstServiceImpl implements IGoodsFeaturedFirstService 
{
    @Autowired
    private GoodsFeaturedFirstMapper goodsFeaturedFirstMapper;

    @Autowired
    private BaseFeatredFirstMapper featredFirstMapper;

    /**
     * 查询首页推荐位置商品
     * 
     * @param id 首页推荐位置商品ID
     * @return 首页推荐位置商品
     */
    @Override
    public BaseFeatredFirst selectGoodsFeaturedFirstById(Long id)
    {
        return featredFirstMapper.selectFeatredFirstById(id);
    }

    /**
     * 查询首页推荐位置商品列表
     * 
     * @param baseFeatredFirst 首页推荐位置商品
     * @return 首页推荐位置商品
     */
    @Override
    public List<BaseFeatredFirst> selectGoodsFeaturedFirstList(BaseFeatredFirst baseFeatredFirst)
    {
        return featredFirstMapper.selectFeatredFirsts(baseFeatredFirst);
    }

    /**
     * 新增首页推荐位置商品
     * 
     * @param goodsFeaturedFirst 首页推荐位置商品
     * @return 结果
     */
    @Override
    public int insertGoodsFeaturedFirst(GoodsFeaturedFirst goodsFeaturedFirst)
    {
        goodsFeaturedFirst.setCreateTime(DateUtils.getNowDate());
        return goodsFeaturedFirstMapper.insertGoodsFeaturedFirst(goodsFeaturedFirst);
    }

    /**
     * 修改首页推荐位置商品
     * 
     * @param goodsFeaturedFirst 首页推荐位置商品
     * @return 结果
     */
    @Override
    public int updateGoodsFeaturedFirst(GoodsFeaturedFirst goodsFeaturedFirst)
    {
        goodsFeaturedFirst.setUpdateTime(DateUtils.getNowDate());
        return goodsFeaturedFirstMapper.updateGoodsFeaturedFirst(goodsFeaturedFirst);
    }

    /**
     * 删除首页推荐位置商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsFeaturedFirstByIds(String ids)
    {
        return goodsFeaturedFirstMapper.deleteGoodsFeaturedFirstByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除首页推荐位置商品信息
     * 
     * @param id 首页推荐位置商品ID
     * @return 结果
     */
    @Override
    public int deleteGoodsFeaturedFirstById(Long id)
    {
        return goodsFeaturedFirstMapper.deleteGoodsFeaturedFirstById(id);
    }
}
