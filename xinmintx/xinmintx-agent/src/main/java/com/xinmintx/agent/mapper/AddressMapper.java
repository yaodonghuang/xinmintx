package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.ShippingAddress;
import com.xinmintx.agent.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("SELECT a.id,a.member_id memberId,a.`name`,a.cellphone,a.region,a.address,a.default_address defaultAddress FROM shipping_address a WHERE a.member_id=#{id} AND a.default_address = 1")
    public ShippingAddress shippingAddress(Integer id);

    @Select("SELECT a.id,a.member_id memberId,a.`name`,a.cellphone,a.region,a.address,a.default_address defaultAddress FROM shipping_address a WHERE a.member_id=#{id}")
    List<ShippingAddress> selectAddres(Integer id);

    @Insert("INSERT INTO `shipping_address` (`member_id`, `name`, `cellphone`, `region`, `address`, `default_address`) VALUES (#{memberId},#{name},#{cellphone},#{region},#{address},#{defaultAddress})")
    int saveAddress(ShippingAddress shippingAddress);

    @Update("update shipping_address\n" +
            "    set member_id = #{memberId},\n" +
            "        name = #{name},\n" +
            "        cellphone = #{cellphone},\n" +
            "        region = #{region},\n" +
            "        address = #{address},\n" +
            "        default_address = #{defaultAddress}\n" +
            "        where id = #{id}")
    int updateAddress(ShippingAddress shippingAddress);

    @Select("SELECT a.id,a.member_id memberId,a.`name`,a.cellphone,a.region,a.address,a.default_address defaultAddress FROM shipping_address a WHERE a.id=#{id}")
    ShippingAddress selectAddress(Integer id);

    //推荐代理超过5个 查询
    @Select("SELECT COUNT(a.id) id FROM user a WHERE a.recommender=#{id} AND a.user_role=5 AND a.status=1 AND a.is_review=1")
    User selectId(Integer id);
}
