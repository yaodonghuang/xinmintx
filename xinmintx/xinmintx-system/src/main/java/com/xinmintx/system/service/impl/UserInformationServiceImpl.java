package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.UserAccount;
import com.xinmintx.system.domain.UserExt;
import com.xinmintx.system.domain.UserInformation;
import com.xinmintx.system.mapper.*;
import com.xinmintx.system.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.domain.User;
import com.xinmintx.system.service.IUserService;
import com.xinmintx.common.core.text.Convert;

/**
 * @ClassName:.用户信息Service业务层处理
 * @author:chf
 * @Date:2020/1/14：15:58
 * @developerKits： win 10     jdk1.8
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserInformationMapper userMapper;

    @Autowired
    private UserAccountMapper accountMapper;

    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private UserAccountRecordMapper accountRecordMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    @Override
    public List<UserExt> selectUserById(UserExt user)
    {

        return accountRecordMapper.selectUserById(user);
    }

    @Override
    public List<UserExt> selectById(Long id) {
        return userExtMapper.selectById(id);
    }

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Override
    public List<UserInformation> selectUserList(UserInformation user)
    {
        List<UserInformation> userInformations = userMapper.selectUserList(user);
        for (int i = 0; i <userInformations.size() ; i++) {
            int id = userInformations.get(i).getId().intValue();
            UserAccount userAccount = accountMapper.selectByUserId(id);
            if (userAccount != null){
                userInformations.get(i).setMoney(userAccount.getMoney());
                userInformations.get(i).setFreezeMoney(userAccount.getFreezeMoney());

            }
        }
        return userInformations;
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(UserInformation user)
    {
        user.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(UserInformation user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }

    /**
     * 删除用户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids)
    {
        return userMapper.deleteUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }
}
