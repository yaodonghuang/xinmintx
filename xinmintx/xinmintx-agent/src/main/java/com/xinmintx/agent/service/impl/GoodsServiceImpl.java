package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.CommodityExtMapper;
import com.xinmintx.agent.mapper.CommodityKindMapper;
import com.xinmintx.agent.mapper.CommodityMapper;
import com.xinmintx.agent.mapper.CommodityTypologyMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private CommodityExtMapper commodityExtMapper;

    @Resource
    private CommodityKindMapper kindMapper;

    @Resource
    private CommodityTypologyMapper typologyMapper;

    @Override
    public Commodity showGoods(int id) {
        return commodityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CommodityExt> getGoods(String name) {
        List<CommodityExt> list = commodityExtMapper.selectGoods(name);
        for (CommodityExt commodityExt : list) {
            int inventory = commodityExt.getInventory();
            int salesVolume = commodityExt.getSalesVolume();
            commodityExt.setMarket(inventory / salesVolume);
        }
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public CommodityExt getGoodById(Integer id) {
        CommodityExt commodityExt = commodityExtMapper.getGoodById(id);

        if (commodityExt == null) {
            return null;
        }
        return commodityExt;
    }

    @Override
    public String[] getPictures(Integer id) {
        String[] split = null;
        String ossUrl = commodityExtMapper.selectUrl(id);
        if (ossUrl != null) {
            ossUrl = ossUrl.substring(1);
            split = ossUrl.split("#");
            if (split.length > 0) {
                return split;
            }
        }
        return null;
    }

    @Override
    public String getParameter(Integer id) {
        String parameter = commodityExtMapper.getParameter(id);
        if (parameter == null) {
            return null;
        }
        return parameter;
    }


    @Override
    public List<CommodityExt> getType(Integer id) {
        List<CommodityExt> list = commodityExtMapper.getGoodType(id);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<CommodityKind> getGoodType(Integer id) {
        return kindMapper.selectType(id);
    }

    @Override
    public List<CommodityTypology> getTypeById(Integer id) {
        CommodityKind kind = kindMapper.selectByPrimaryKey(id);
        CommodityKindExample kindExample = new CommodityKindExample();
        CommodityKindExample.Criteria kindExampleCriteria = kindExample.createCriteria();
        kindExampleCriteria.andCommodityIdEqualTo(kind.getCommodityId());
        kindExampleCriteria.andKindNameEqualTo(kind.getKindName());
        List<CommodityKind> kinds = kindMapper.selectByExample(kindExample);
        List<Integer> kindIds = new ArrayList<>();
        for (CommodityKind commodityKind : kinds) {
            kindIds.add(commodityKind.getId());
        }
        CommodityTypologyExample example = new CommodityTypologyExample();
        CommodityTypologyExample.Criteria criteria = example.createCriteria();
        criteria.andKindIdIn(kindIds);
        return typologyMapper.selectByExample(example);
    }

    @Override
    public List<CommodityTypology> showTypeById(Integer id) {
        return typologyMapper.showType(id);
    }
}
