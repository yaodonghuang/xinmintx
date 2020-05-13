package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.UserExt;
import com.xinmintx.system.domain.UserInformation;

import java.util.List;

public interface UserExtMapper {
    List<UserExt> selectUserById(UserExt user);

    List<UserExt> selectById(Long id);
}
