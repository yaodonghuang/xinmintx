package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.ProfitMapper;
import com.xinmintx.system.domain.Profit;
import com.xinmintx.system.service.IProfitService;
import com.xinmintx.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-15
 */
@Service
public class ProfitServiceImpl implements IProfitService 
{
    @Autowired
    private ProfitMapper profitMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Profit selectProfitById(Long id)
    {
        return profitMapper.selectProfitById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param profit 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Profit> selectProfitList(Profit profit)
    {
        return profitMapper.selectProfitList(profit);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param profit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertProfit(Profit profit)
    {
        return profitMapper.insertProfit(profit);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param profit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateProfit(Profit profit)
    {
        return profitMapper.updateProfit(profit);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProfitByIds(String ids)
    {
        return profitMapper.deleteProfitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteProfitById(Long id)
    {
        return profitMapper.deleteProfitById(id);
    }
}
