package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodPtgood;
import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
public interface GoodPtgoodMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param ptgoodsId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public GoodPtgood selectGoodPtgoodById(Long ptgoodsId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GoodPtgood> selectGoodPtgoodList(GoodPtgood goodPtgood);

    /**
     * 新增【请填写功能名称】
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 结果
     */
    public int insertGoodPtgood(GoodPtgood goodPtgood);

    /**
     * 修改【请填写功能名称】
     * 
     * @param goodPtgood 【请填写功能名称】
     * @return 结果
     */
    public int updateGoodPtgood(GoodPtgood goodPtgood);

    /**
     * 删除【请填写功能名称】
     * 
     * @param ptgoodsId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteGoodPtgoodById(Long ptgoodsId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ptgoodsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodPtgoodByIds(String[] ptgoodsIds);

    List<GoodPtgood> selectList();

    GoodPtgood select(GoodPtgood goodPtgood);
}
