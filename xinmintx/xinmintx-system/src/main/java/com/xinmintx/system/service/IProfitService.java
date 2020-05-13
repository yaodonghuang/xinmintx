package com.xinmintx.system.service;

import com.xinmintx.system.domain.Profit;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author xinmintx
 * @date 2019-11-15
 */
public interface IProfitService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Profit selectProfitById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param profit 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Profit> selectProfitList(Profit profit);

    /**
     * 新增【请填写功能名称】
     * 
     * @param profit 【请填写功能名称】
     * @return 结果
     */
    public int insertProfit(Profit profit);

    /**
     * 修改【请填写功能名称】
     * 
     * @param profit 【请填写功能名称】
     * @return 结果
     */
    public int updateProfit(Profit profit);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProfitByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteProfitById(Long id);
}
