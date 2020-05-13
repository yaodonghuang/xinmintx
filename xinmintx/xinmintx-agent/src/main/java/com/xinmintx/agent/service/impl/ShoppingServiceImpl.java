package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.ProcurementCommoditiesMapper;
import com.xinmintx.agent.mapper.ShoppingMapper;
import com.xinmintx.agent.mapper.UnitPhotoMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    private ProcurementCommoditiesMapper  procurementCommoditiesMapper;
    @Autowired
    private UnitPhotoMapper unitPhotoMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public int byShopping(ProcurementCommodities procurementCommodities, Integer[] photo,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        procurementCommodities.setMerchantId(user.getId());
        int i = procurementCommoditiesMapper.insertSelective(procurementCommodities);
        Integer id = procurementCommodities.getId();
        List<Integer> ids = Arrays.asList(photo);
        UnitPhotoExample unitPhotoExample = new UnitPhotoExample();
        UnitPhotoExample.Criteria unitPhotoExampleCriteria = unitPhotoExample.createCriteria();
        unitPhotoExampleCriteria.andIdIn(ids);
        UnitPhoto unitPhoto = new UnitPhoto();
        unitPhoto.setCompanyId(id);
        return unitPhotoMapper.updateByExampleSelective(unitPhoto, unitPhotoExample);
    }

    /**
     * 查询采购商品列表（ 审核通过 ）
     * @return
     */
    @Override
    public List<ProcurementCommodities> selectProcurementCommodities(int id) {
        return shoppingMapper.selectProcurementCommodities(id);
    }

    @Override
    public ProcurementCommodities selectByIdProcurementCommodities(int id) {
        return shoppingMapper.selectByIdProcurementCommodities(id);
    }
}
