package com.xinmintx.system.service.impl;

import java.util.List;

import com.xinmintx.system.domain.Factory;
import com.xinmintx.system.service.IFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.ProcurementCommoditiesMapper;
import com.xinmintx.system.domain.ProcurementCommodities;
import com.xinmintx.system.service.IProcurementCommoditiesService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商品审核Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Service
public class ProcurementCommoditiesServiceImpl implements IProcurementCommoditiesService 
{
    @Autowired
    private ProcurementCommoditiesMapper procurementCommoditiesMapper;
    @Autowired
    private IFactoryService factoryService;

    /**
     * 查询商品审核
     * 
     * @param id 商品审核ID
     * @return 商品审核
     */
    @Override
    public ProcurementCommodities selectProcurementCommoditiesById(Long id)
    {
        return procurementCommoditiesMapper.selectProcurementCommoditiesById(id);
    }

    /**
     * 查询商品审核列表
     * 
     * @param procurementCommodities 商品审核
     * @return 商品审核
     */
    @Override
    public List<ProcurementCommodities> selectProcurementCommoditiesList(ProcurementCommodities procurementCommodities)
    {
        return procurementCommoditiesMapper.selectProcurementCommoditiesList(procurementCommodities);
    }

    /**
     * 新增商品审核
     * 
     * @param procurementCommodities 商品审核
     * @return 结果
     */
    @Override
    public int insertProcurementCommodities(ProcurementCommodities procurementCommodities)
    {
        return procurementCommoditiesMapper.insertProcurementCommodities(procurementCommodities);
    }

    /**
     * 修改商品审核
     * 
     * @param procurementCommodities 商品审核
     * @return 结果
     */
    @Override
    public int updateProcurementCommodities(ProcurementCommodities procurementCommodities)
    {
        int i = procurementCommoditiesMapper.updateProcurementCommodities(procurementCommodities);
        if(i>0){
            Factory factory = new Factory();
            factory.setName(procurementCommodities.getSupplier());
            factory.setPhone(procurementCommodities.getCellphone());
            factory.setAddress(procurementCommodities.getDetailedAddress());
            factory.setPassword("123456");
            factoryService.insertFactory(factory);
        }
        return i;
    }

    /**
     * 删除商品审核对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProcurementCommoditiesByIds(String ids)
    {
        return procurementCommoditiesMapper.deleteProcurementCommoditiesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品审核信息
     * 
     * @param id 商品审核ID
     * @return 结果
     */
    @Override
    public int deleteProcurementCommoditiesById(Long id)
    {
        return procurementCommoditiesMapper.deleteProcurementCommoditiesById(id);
    }
}
