package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.ProcurementCommodities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingMapper {
    @Select("SELECT a.id,a.sample_name sampleName,a.procurement_price procurementPrice  FROM procurement_commodities a WHERE a.is_review=1 AND a.merchant_id=#{id}")
    List<ProcurementCommodities> selectProcurementCommodities(int id );
    @Select("SELECT a.sample_name sampleName,a.sample_type sampleType,a.tracking_number trackingNumber,a.supplier,a.cellphone,a.detailed_address detailedAddress,a.market_value marketValue,a.procurement_price procurementPrice FROM procurement_commodities a WHERE a.id=#{id}")
    ProcurementCommodities selectByIdProcurementCommodities(@Param("id") int id);
}
