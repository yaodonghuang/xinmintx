package com.xinmintx.system.service;

import com.xinmintx.system.domain.TbSeller;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author xinmintx
 * @date 2019-12-25
 */
public interface ITbSellerService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param sellerId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TbSeller selectTbSellerById(String sellerId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TbSeller> selectTbSellerList(TbSeller tbSeller);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 结果
     */
    public int insertTbSeller(TbSeller tbSeller);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 结果
     */
    public int updateTbSeller(TbSeller tbSeller);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbSellerByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param sellerId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTbSellerById(String sellerId);
}
