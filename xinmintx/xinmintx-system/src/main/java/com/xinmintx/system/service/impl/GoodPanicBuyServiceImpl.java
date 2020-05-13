package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.Member;
import com.xinmintx.system.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodPanicBuyMapper;
import com.xinmintx.system.domain.GoodPanicBuy;
import com.xinmintx.system.service.IGoodPanicBuyService;
import com.xinmintx.common.core.text.Convert;

/**
 * 限时抢购Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-18
 */
@Service
public class GoodPanicBuyServiceImpl implements IGoodPanicBuyService 
{
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询限时抢购
     * 
     * @param id 限时抢购ID
     * @return 限时抢购
     */
    @Override
    public GoodPanicBuy selectGoodPanicBuyById(Long id)
    {
        return goodPanicBuyMapper.selectGoodPanicBuyById(id);
    }

    /**
     * 查询限时抢购列表
     * 
     * @param goodPanicBuy 限时抢购
     * @return 限时抢购
     */
    @Override
    public List<GoodPanicBuy> selectGoodPanicBuyList(GoodPanicBuy goodPanicBuy)
    {
        return goodPanicBuyMapper.selectGoodPanicBuyList(goodPanicBuy);
    }

    /**
     * 新增限时抢购
     * 
     * @param goodPanicBuy 限时抢购
     * @return 结果
     */
    @Override
    public int insertGoodPanicBuy(GoodPanicBuy goodPanicBuy)
    {
        goodPanicBuy.setCreateTime(DateUtils.getNowDate());
        return goodPanicBuyMapper.insertGoodPanicBuy(goodPanicBuy);
    }

    /**
     * 修改限时抢购
     * 
     * @param goodPanicBuy 限时抢购
     * @return 结果
     */
    @Override
    public int updateGoodPanicBuy(GoodPanicBuy goodPanicBuy)
    {
        goodPanicBuy.setUpdateTime(DateUtils.getNowDate());
        return goodPanicBuyMapper.updateGoodPanicBuy(goodPanicBuy);
    }

    /**
     * 删除限时抢购对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodPanicBuyByIds(String ids)
    {
        return goodPanicBuyMapper.deleteGoodPanicBuyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除限时抢购信息
     * 
     * @param id 限时抢购ID
     * @return 结果
     */
    @Override
    public int deleteGoodPanicBuyById(Long id)
    {
        return goodPanicBuyMapper.deleteGoodPanicBuyById(id);
    }

    /**
     * 检查商品是否已经添加过
     * @param goodsId  商品id
     * @return  boolean
     */
    @Override
    public boolean checkGoods(int goodsId) {
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.checkGoods(goodsId);
        return goodPanicBuy == null;
    }

    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberListBuy(member);
    }
}
