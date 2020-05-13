package com.xinmintx.factory.api.mapper;

import com.xinmintx.factory.api.pojo.GoodsOrder;
import com.xinmintx.factory.api.pojo.Specs;
import com.xinmintx.factory.api.pojo.VenderOrder;
import com.xinmintx.factory.api.pojo.VenderOrderMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface DeliveryMapper {
    List<VenderOrder> query(@Param("id") long id, @Param("statu") Integer statu, @Param("str") String str);

    List<VenderOrderMain> queryVom(@Param("id") long id, @Param("statu") Integer statu, @Param("str") String str);

    List<VenderOrder> queryEvaluate(long id);

    List<VenderOrder> queryByTime(@Param("id") long id, @Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<GoodsOrder> queryAll(long id);

    /**
     * 根据id查询商品规格组表id和商品规格值
     * @param s1
     * @return
     */
    Specs queryId(String s1);

    /**
     * 根据商品规格组表id查询规格名称
     * @param specId
     * @return
     */
    String queryNum(Integer specId);
}
