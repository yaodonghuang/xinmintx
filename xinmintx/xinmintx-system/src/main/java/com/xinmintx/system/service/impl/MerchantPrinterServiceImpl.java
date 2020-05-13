package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.MerchantPrinterMapper;
import com.xinmintx.system.domain.MerchantPrinter;
import com.xinmintx.system.service.IMerchantPrinterService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商户打印机编号Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-03-11
 */
@Service
public class MerchantPrinterServiceImpl implements IMerchantPrinterService 
{
    @Autowired
    private MerchantPrinterMapper merchantPrinterMapper;

    /**
     * 查询商户打印机编号
     * 
     * @param id 商户打印机编号ID
     * @return 商户打印机编号
     */
    @Override
    public MerchantPrinter selectMerchantPrinterById(Long id)
    {
        return merchantPrinterMapper.selectMerchantPrinterById(id);
    }

    /**
     * 查询商户打印机编号列表
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 商户打印机编号
     */
    @Override
    public List<MerchantPrinter> selectMerchantPrinterList(MerchantPrinter merchantPrinter)
    {
        return merchantPrinterMapper.selectMerchantPrinterList(merchantPrinter);
    }

    /**
     * 新增商户打印机编号
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 结果
     */
    @Override
    public int insertMerchantPrinter(MerchantPrinter merchantPrinter)
    {
        return merchantPrinterMapper.insertMerchantPrinter(merchantPrinter);
    }

    /**
     * 修改商户打印机编号
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 结果
     */
    @Override
    public int updateMerchantPrinter(MerchantPrinter merchantPrinter)
    {
        return merchantPrinterMapper.updateMerchantPrinter(merchantPrinter);
    }

    /**
     * 删除商户打印机编号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerchantPrinterByIds(String ids)
    {
        return merchantPrinterMapper.deleteMerchantPrinterByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户打印机编号信息
     * 
     * @param id 商户打印机编号ID
     * @return 结果
     */
    @Override
    public int deleteMerchantPrinterById(Long id)
    {
        return merchantPrinterMapper.deleteMerchantPrinterById(id);
    }
}
