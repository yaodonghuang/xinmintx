package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsSku;
import com.xinmintx.system.domain.SpecSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsTypeExtMapper {

    @Select("SELECT distinct b.name,b.code nameCode,b.id,GROUP_CONCAT(c.value) textCode,GROUP_CONCAT(c.code) code FROM (SELECT *, count(distinct a.name)FROM goods_spec a \n" +
            "WHERE a.goods_id=#{id} group by a.name) b LEFT JOIN goods_spec_value c ON b.id=c.spec_id group by b.name")
    List<SpecSelect> selectSpec(@Param("id") Integer id);

    @Select("SELECT * FROM goods_sku a WHERE a.goods_id=#{goodsId} AND a.spec_value_id=#{specValueId}")
    GoodsSku selectSku(GoodsSku goodsSku);

    @Select("SELECT MAX(code) id FROM goods_spec a WHERE a.goods_id=#{id}")
    GoodsSku selectSkuMaxSpec(Integer id);

    @Select("SELECT MAX(code) id FROM goods_spec_value a WHERE a.goods_id=#{id}")
    GoodsSku selectSkuMaxSpecValue(Integer id);
}
