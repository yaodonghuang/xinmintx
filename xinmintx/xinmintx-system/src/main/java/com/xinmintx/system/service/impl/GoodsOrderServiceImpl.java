package com.xinmintx.system.service.impl;

import java.util.List;

import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.Goods;
import com.xinmintx.system.domain.GoodsOrderDetail;
import com.xinmintx.system.domain.GoodsOrderDetailExample;
import com.xinmintx.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.domain.GoodsOrder;
import com.xinmintx.system.service.IGoodsOrderService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商品订单Service业务层处理
 *
 * @author xinmintx
 * @date 2019-12-13
 */
@Service
public class GoodsOrderServiceImpl implements IGoodsOrderService {
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;

    /**
     * 查询商品订单
     *
     * @param id 商品订单ID
     * @return 商品订单
     */
    @Override
    public GoodsOrder selectGoodsOrderById(Long id) {
        return goodsOrderMapper.selectGoodsOrderById(id);
    }

    /**
     * 查询商品订单列表
     *
     * @param goodsOrder 商品订单
     * @return 商品订单
     */
    @Override
    public List<GoodsOrder> selectGoodsOrderList(GoodsOrder goodsOrder) {
        return goodsOrderMapper.selectGoodsOrderList(goodsOrder);
    }

    /**
     * 新增商品订单
     *
     * @param goodsOrder 商品订单
     * @return 结果
     */
    @Override
    public int insertGoodsOrder(GoodsOrder goodsOrder) {
        goodsOrder.setCreateTime(DateUtils.getNowDate());
        return goodsOrderMapper.insertGoodsOrder(goodsOrder);
    }

    /**
     * 修改商品订单
     *
     * @param goodsOrder 商品订单
     * @return 结果
     */
    @Override
    public int updateGoodsOrder(GoodsOrder goodsOrder) {
        goodsOrder.setUpdateTime(DateUtils.getNowDate());
        return goodsOrderMapper.updateGoodsOrder(goodsOrder);
    }

    /**
     * 删除商品订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsOrderByIds(String ids) {
        return goodsOrderMapper.deleteGoodsOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品订单信息
     *
     * @param id 商品订单ID
     * @return 结果
     */
    @Override
    public int deleteGoodsOrderById(Long id) {
        return goodsOrderMapper.deleteGoodsOrderById(id);
    }

    /**
     * 获取订单详情
     *
     * @param id 订单主表id
     * @return
     */
    @Override
    public List<GoodsOrderDetail> selectGoodsDetail(Integer id) {
        GoodsOrderDetailExample example = new GoodsOrderDetailExample();
        GoodsOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(id);
        List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByExample(example);
        if (goodsOrderDetails != null || goodsOrderDetails.size() > 0) {
            for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
                Integer goodsId = goodsOrderDetail.getGoodsId();
                if (goodsId != null) {
                    Goods goods = goodsMapper.selectGoodsById(goodsId.longValue());
                    if (goods != null) {
                        goodsOrderDetail.setGoodsDetailsName(goods.getGoodsName());
                    }
                }
                Integer skuId = goodsOrderDetail.getSkuId();
                if (skuId != null) {
                    String specValueId = goodsSkuMapper.selectSpecValueId(skuId);
                    String[] s = specValueId.split("_");
                    String flag = "";
                    for (int i = 0; i < s.length; i++) {
                        if (i == s.length - 1) {
                            flag += goodsSpecValueMapper.selectValue(Integer.valueOf(s[i]));
                        } else {
                            flag += goodsSpecValueMapper.selectValue(Integer.valueOf(s[i])) + "-";
                        }
                    }
                    goodsOrderDetail.setSpecValue(flag);
                }
            }
        }
        return goodsOrderDetails;
    }
}
