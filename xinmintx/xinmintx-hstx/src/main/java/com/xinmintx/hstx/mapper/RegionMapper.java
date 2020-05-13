package com.xinmintx.hstx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.pojo.po.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinmintx.hstx.pojo.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
public interface RegionMapper extends BaseMapper<Region> {

    @Select("SELECT DISTINCT a.region_name regionName FROM `user` a WHERE a.region_name is not null")
    List<User> selectHotCity();

    List<Region> selectList(QueryWrapper<Object> objectQueryWrapper);
}
