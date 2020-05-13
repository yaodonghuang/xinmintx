package com.xinmintx.system.service.impl;

import java.util.List;

import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.UserMapper;
import com.xinmintx.system.domain.User;
import com.xinmintx.system.service.IUserService;
import com.xinmintx.common.core.text.Convert;

/**
 * 用户信息Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息ID
     * @return 用户信息
     */
    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 根据手机号查寻用户
     *
     * @param phone
     * @return
     */
    @Override
    public int selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(User user) {
        user.setRecommender(1L);
        user.setCreateTime(DateUtils.getNowDate());
        String code = null;
        if (user.getUserRole() == 3) {
            String regionCode = user.getRegionCode();
            code = regionCode.split(",")[2];
            user.setRegionCode(code);
            User user1 = userMapper.selectUserByRegionCode(code);
            if (user1 != null) {
                return -1;
            }
        }
        try {
            //获取该区域下的商户
            int i = userMapper.insertUser(user);
            if (user.getUserRole() == 3) {
                Long id = user.getId();
                merchantMapper.updateMerchantByRegionCode(code, id);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user) {
        user.setUpdateTime(DateUtils.getNowDate());
        String code = null;
        if (user.getUserRole() == 3) {
            String regionCode = user.getRegionCode();
            if (StringUtils.isNotBlank(regionCode)) {
                code = regionCode.split(",")[2];
                user.setRegionCode(code);
                User user1 = userMapper.selectUserByRegionCode(code);
                if (user1 != null && !user.getId().equals(user1.getId())) {
                    return -1;
                }
            }
        }
        if (user.getUserRole() == 3) {
            Long id = user.getId();
            //移除原本地区下的商户
            merchantMapper.updateMerchantById(id);
            //获取该区域下的商户
            merchantMapper.updateMerchantByRegionCode(code, id);
        }
        return userMapper.updateUser(user);
    }

    /**
     * 删除用户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) {
        return userMapper.deleteUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

}
