package com.xinmintx.merchant.mapper;


import com.xinmintx.merchant.model.MerchantPrinter;
import com.xinmintx.merchant.model.MerchantPrinterExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MerchantPrinterMapper {
    long countByExample(MerchantPrinterExample example);

    int deleteByExample(MerchantPrinterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantPrinter record);

    int insertSelective(MerchantPrinter record);

    List<MerchantPrinter> selectByExampleWithRowbounds(MerchantPrinterExample example, RowBounds rowBounds);

    List<MerchantPrinter> selectByExample(MerchantPrinterExample example);

    MerchantPrinter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MerchantPrinter record, @Param("example") MerchantPrinterExample example);

    int updateByExample(@Param("record") MerchantPrinter record, @Param("example") MerchantPrinterExample example);

    int updateByPrimaryKeySelective(MerchantPrinter record);

    int updateByPrimaryKey(MerchantPrinter record);

    int upPrintingStatus(@Param("merchantId") Integer merchantId, @Param("ifAuto") Integer ifAuto);
}