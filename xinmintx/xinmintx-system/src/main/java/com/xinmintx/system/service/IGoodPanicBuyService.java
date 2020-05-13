package com.xinmintx.system.service;

import com.xinmintx.system.domain.GoodPanicBuy;
import com.xinmintx.system.domain.Member;

import java.util.List;

/**
 * 限时抢购Service接口
 * 
 * @author xinmintx
 * @date 2020-02-18
 */
public interface IGoodPanicBuyService 
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
     * 批量删除限时抢购
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodPanicBuyByIds(String ids);

    /**
     * 删除限时抢购信息
     * 
     * @param id 限时抢购ID
     * @return 结果
     */
    public int deleteGoodPanicBuyById(Long id);

    /**
     * 检查商品是否已经添加过
     * @param goodsId  商品id
     * @return   boolean
     */
    boolean checkGoods (int goodsId);

    /**
     * 查询会员列表
     * @param member
     * @return
     */
    List<Member> selectMemberList(Member member);
}
