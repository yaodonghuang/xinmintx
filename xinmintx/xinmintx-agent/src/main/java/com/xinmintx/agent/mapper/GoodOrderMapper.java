package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodsOrder;
import com.xinmintx.agent.model.GoodsOrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodOrderMapper {


    @Insert("INSERT INTO `goods_order` ( `user_id`, `order_num`, `order_state`, `create_time`, `update_time`, `if_pay`, `receive_address`, `receive_name`, `receive_phone`, `receive_message`, `total_amount`, `u_order_id`) VALUES ( #{userId}, #{orderNum}, #{orderState}, #{createTime}, #{updateTime}, #{ifPay}, #{receiveAddress}, #{receiveName}, #{receivePhone}, #{receiveMessage}, #{totalAmount}, #{uOrderId})")
    public void insertGoodOrder(GoodsOrder goodsOrder);
    @Insert("INSERT INTO `goods_order_detail` (`order_id`, `commodity_id`, `commodity_typology_id`, `kind_id`, `factory_id`, `price`, `quantity`, `send_state`, `commodity_name`, `commodity_pic`, `evaluate`, `create_time`, `update_time`, `courier_number`) VALUES (#{orderId}, #{commodityId}, #{commodityTypologyId}, #{kindId}, #{factoryId}, #{price}, #{quantity}, #{sendState}, #{commodityName}, #{commodityPic}, #{evaluate}, #{createTime}, #{updateTime}, #{courierNumber})")
    void insertDetail(GoodsOrderDetail detail);
}
