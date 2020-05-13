package com.xinmintx.system.service.impl;

import java.util.List;

import com.xinmintx.system.domain.GoodPtgoodBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodPtgoodMapper;
import com.xinmintx.system.domain.GoodPtgood;
import com.xinmintx.system.service.IGoodPtgoodService;
import com.xinmintx.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
@Service
public class GoodPtgoodServiceImpl implements IGoodPtgoodService 
{
    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param ptgoodsId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public GoodPtgood selectGoodPtgoodById(Long ptgoodsId)
    {
        return goodPtgoodMapper.selectGoodPtgoodById(ptgoodsId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GoodPtgood> selectGoodPtgoodList(GoodPtgood goodPtgood)
    {
        return goodPtgoodMapper.selectGoodPtgoodList(goodPtgood);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGoodPtgood(GoodPtgoodBean goodPtgood)
    {
        GoodPtgood ptgood = new GoodPtgood();
        GoodPtgood good = new GoodPtgood();
        for (int i = 0; i <goodPtgood.getPtSize().length ; i++) {
            ptgood.setGoodsId(goodPtgood.getGoodsId());
            ptgood.setPtgoodsName(goodPtgood.getPtgoodsName());
            ptgood.setPtPrice(goodPtgood.getPtPrice()[i]);
            ptgood.setPtSize(goodPtgood.getPtSize()[i]);
            ptgood.setPtValidhours(Double.parseDouble(goodPtgood.getPtValidhours()));
            ptgood.setStartTime(goodPtgood.getStartTime());
            ptgood.setEndTime(goodPtgood.getEndTime());
            ptgood.setPtgoodsNumber(goodPtgood.getPtgoodsNumber());
            ptgood.setIsSale(Long.valueOf(goodPtgood.getIsSale()));
            ptgood.setGroupTimes(goodPtgood.getGroupTimes());
            ptgood.setGroupType(goodPtgood.getGroupType());
            ptgood.setNameActivity(goodPtgood.getNameActivity());
            good = goodPtgoodMapper.select(ptgood);
            if (good!=null){
                return 0;
            }
            goodPtgoodMapper.insertGoodPtgood(ptgood);
        }
        return 1;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGoodPtgood(GoodPtgood goodPtgood)
    {
        GoodPtgood ptgood = goodPtgoodMapper.select(goodPtgood);
        if (ptgood!=null){
            if (ptgood.getPtgoodsId().equals(goodPtgood.getPtgoodsId())){
                return goodPtgoodMapper.updateGoodPtgood(goodPtgood);
            }
            return 0;
        }else {
            return goodPtgoodMapper.updateGoodPtgood(goodPtgood);
        }
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodPtgoodByIds(String ids)
    {
        return goodPtgoodMapper.deleteGoodPtgoodByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param ptgoodsId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteGoodPtgoodById(Long ptgoodsId)
    {
        return goodPtgoodMapper.deleteGoodPtgoodById(ptgoodsId);
    }
}
