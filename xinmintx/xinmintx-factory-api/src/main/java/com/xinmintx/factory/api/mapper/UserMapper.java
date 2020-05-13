package com.xinmintx.factory.api.mapper;

import com.xinmintx.factory.api.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> query();
    void upUserId(@Param("id") String id, @Param("userId") long userId);
}
