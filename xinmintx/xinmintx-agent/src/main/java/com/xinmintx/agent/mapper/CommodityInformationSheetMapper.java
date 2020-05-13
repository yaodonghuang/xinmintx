package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityType;
import com.xinmintx.agent.model.GoodsPhoto;
import com.xinmintx.agent.modelDTO.CommodityAndImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommodityInformationSheetMapper {
    @Select("SELECT DISTINCT a.id id,a.activity_title  activityTitle,a.sales_actual salesVolume,a.goods_name commodityName,a.turns_photo ossUrl FROM goods_pt_user b LEFT JOIN goods a ON b.goods_id=a.id WHERE b.user_id=#{userId}")
    List<CommodityAndImage> commodityAndImageList(@Param("userId") Integer userId);

    @Select("SELECT a.id,a.type_name categoryName FROM goods_type a")
    List<CommodityType> selectShoppingType();

    @Select("SELECT  a.id id,a.activity_title  activityTitle,a.sales_actual salesVolume,a.goods_name commodityName,a.turns_photo ossUrl,a.price salesPrice,a.bazaar_price price,a.agency_price agencyPrice,b.group_type type FROM goods a LEFT JOIN good_ptgood b ON a.id=b.goods_id WHERE a.type_id=#{id} AND a.status=1 LIMIT 1,10")
    List<CommodityAndImage> selectByTypeShopping(@Param("id") Integer id);

    @Select("SELECT a.id,a.photo_url photoUrl FROM goods_photo a WHERE a.id=#{s}")
    GoodsPhoto selectOssUrl(int s);

    @Select("SELECT  a.id id,a.activity_title  activityTitle,a.sales_actual salesVolume,a.goods_name commodityName,a.turns_photo ossUrl,a.price salesPrice,a.bazaar_price price,a.agency_price agencyPrice,b.group_type type FROM goods a LEFT JOIN good_ptgood b ON a.id=b.goods_id WHERE a.hot_sale=1 AND a.status=1 LIMIT 1,10")
    List<CommodityAndImage> selectHotShopping(Integer id);
}
