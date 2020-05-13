package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.BaseFeatredFirst;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/16 0016
 * @time: 下午 16:55
 * @Description:
 */
public interface BaseFeatredFirstMapper {

    List<BaseFeatredFirst> selectFeatredFirsts(BaseFeatredFirst baseFeatredFirst);

    BaseFeatredFirst selectFeatredFirstById(@Param("id") Long id);
}
