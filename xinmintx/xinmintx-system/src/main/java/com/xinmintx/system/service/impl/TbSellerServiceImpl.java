package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.TbSellerMapper;
import com.xinmintx.system.domain.TbSeller;
import com.xinmintx.system.service.ITbSellerService;
import com.xinmintx.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-12-25
 */
@Service
public class TbSellerServiceImpl implements ITbSellerService 
{
    @Autowired
    private TbSellerMapper tbSellerMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param sellerId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TbSeller selectTbSellerById(String sellerId)
    {
        return tbSellerMapper.selectTbSellerById(sellerId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TbSeller> selectTbSellerList(TbSeller tbSeller)
    {
        return tbSellerMapper.selectTbSellerList(tbSeller);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTbSeller(TbSeller tbSeller)
    {
        tbSeller.setCreateTime(DateUtils.getNowDate());
        return tbSellerMapper.insertTbSeller(tbSeller);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tbSeller 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTbSeller(TbSeller tbSeller)
    {
        tbSeller.setUpdateTime(DateUtils.getNowDate());
        return tbSellerMapper.updateTbSeller(tbSeller);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbSellerByIds(String ids)
    {
        return tbSellerMapper.deleteTbSellerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param sellerId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTbSellerById(String sellerId)
    {
        return tbSellerMapper.deleteTbSellerById(sellerId);
    }
}
