package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodPanicBuy;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 限时抢购Mapper接口
 * 
 * @author xinmintx
 * @date 2020-02-18
 */
public interface GoodPanicBuyMapper 
{
    /**
     * 查询限时抢购
     * 
     * @param id 限时抢购ID
     * @return 限时抢购
     */
    public GoodPanicBuy selectGoodPanicBuyById(Long id);

    /**
     * 查询限时抢购列表
     * 
     * @param goodPanicBuy 限时抢购
     * @return 限时抢购集合
     */
    public List<GoodPanicBuy> selectGoodPanicBuyList(GoodPanicBuy goodPanicBuy);

    /**
     * 新增限时抢购
     * 
     * @param goodPanicBuy 限时抢购
     * @return 结果
     */
    public int insertGoodPanicBuy(GoodPanicBuy goodPanicBuy);

    /**
     * 修改限时抢购
     * 
     * @param goodPanicBuy 限时抢购
     * @return 结果
     */
    public int updateGoodPanicBuy(GoodPanicBuy goodPanicBuy);

    /**
     * 删除限时抢购
     * 
     * @param id 限时抢购ID
     * @return 结果
     */
    public int deleteGoodPanicBuyById(Long id);

    /**
     * 批量删除限时抢购
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodPanicBuyByIds(String[] ids);

    /**
     * 检查商品是否已经添加过
     * @param goodsId 商品id
     * @return 结果
     */
    GoodPanicBuy checkGoods (@Param("goodsId")int goodsId);
}
