package com.xinmintx.hstx.mapper;

import com.xinmintx.hstx.pojo.po.MemberCheckIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 打卡签到表 Mapper 接口
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
public interface MemberCheckInMapper extends BaseMapper<MemberCheckIn> {

}
