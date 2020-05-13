package com.xinmintx.system.service;

import com.xinmintx.system.domain.MerchantPrinter;
import java.util.List;

/**
 * 商户打印机编号Service接口
 * 
 * @author xinmintx
 * @date 2020-03-11
 */
public interface IMerchantPrinterService 
{
    /**
     * 查询商户打印机编号
     * 
     * @param id 商户打印机编号ID
     * @return 商户打印机编号
     */
    public MerchantPrinter selectMerchantPrinterById(Long id);

    /**
     * 查询商户打印机编号列表
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 商户打印机编号集合
     */
    public List<MerchantPrinter> selectMerchantPrinterList(MerchantPrinter merchantPrinter);

    /**
     * 新增商户打印机编号
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 结果
     */
    public int insertMerchantPrinter(MerchantPrinter merchantPrinter);

    /**
     * 修改商户打印机编号
     * 
     * @param merchantPrinter 商户打印机编号
     * @return 结果
     */
    public int updateMerchantPrinter(MerchantPrinter merchantPrinter);

    /**
     * 批量删除商户打印机编号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerchantPrinterByIds(String ids);

    /**
     * 删除商户打印机编号信息
     * 
     * @param id 商户打印机编号ID
     * @return 结果
     */
    public int deleteMerchantPrinterById(Long id);
}
