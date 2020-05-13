package com.xinmintx.system.service.impl;

import com.xinmintx.common.core.text.Convert;
import com.xinmintx.system.domain.*;
import com.xinmintx.system.mapper.SysCardMapper;
import com.xinmintx.system.service.ISysCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台打卡Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-13
 */
@Service
public class SysCardServiceImpl implements ISysCardService 
{
    @Autowired
    private SysCardMapper sysCardMapper;


    /**
     * 查询后台打卡
     * 
     * @param id 后台打卡ID
     * @return 后台打卡
     */
    @Override
    public SysCard selectSysCardById(Long id)
    {
        return sysCardMapper.selectSysCardById(id);
    }

    /**
     * 查询后台打卡列表
     * 
     * @param sysCard 后台打卡
     * @return 后台打卡
     */
    @Override
    public List<SysCard> selectSysCardList(SysCard sysCard)
    {
        return sysCardMapper.selectSysCardList(sysCard);
    }

    /**
     * 新增后台打卡
     * 
     * @param sysCard 后台打卡
     * @return 结果
     */
    @Override
    public int insertSysCard(SysCard sysCard)
    {
        //sysCard.setCreateTime(DateUtils.getNowDate());
        return sysCardMapper.insertSysCard(sysCard);
    }

    /**
     * 修改后台打卡
     * 
     * @param sysCard 后台打卡
     * @return 结果
     */
    @Override
    public int updateSysCard(SysCard sysCard)
    {
        //sysCard.setUpdateTime(DateUtils.getNowDate());
        return sysCardMapper.updateSysCard(sysCard);
    }

    /**
     * 删除后台打卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCardByIds(String ids)
    {
        return sysCardMapper.deleteSysCardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除后台打卡信息
     * 
     * @param id 后台打卡ID
     * @return 结果
     */
    @Override
    public int deleteSysCardById(Long id)
    {
        return sysCardMapper.deleteSysCardById(id);
    }

    /**
     * 查询打卡人和对应的评论和点赞
     * @param id 打卡表id
     * @return 结果
     */
    @Override
    public List<CheckInUser> selectCheckInById(Integer id) {
        List<CheckInUser> users = new ArrayList<>();
        List<MemberCheckInBean> memberCheckInBeans = sysCardMapper.selectMemberCheckIn(id);
        for (MemberCheckInBean memberCheckInBean : memberCheckInBeans) {
            CheckInUser user = new CheckInUser();
            user.setId(memberCheckInBean.getId());
            user.setUsername(memberCheckInBean.getName());
            user.setTime(memberCheckInBean.getCreate_time());
            int id1 = memberCheckInBean.getId();
            List<CheckInComment> checkInComments = sysCardMapper.selectCheckInComment(id1);
            List<LikenumList> likenumLists = sysCardMapper.selectLikenumList(id1);
            user.setComments(checkInComments);
            user.setLikenumLists(likenumLists);
            users.add(user);
        }
        return users;
    }

    /**
     * 根据会员id删除打卡记录
     * @param id 会员id
     */
    @Override
    public void deleteMemberCheckIn(Integer id) {
        sysCardMapper.deleteMemberCheckIn(id);
    }


    /**
     * 根据id删除评论
     * @param id 评论id
     */
    @Override
    public void deleteComment(Integer id) {
        sysCardMapper.deleteComment(id);
    }

    /**
     * 根据id删除点赞
     * @param id 点赞表id
     */
    @Override
    public void deleteLike(Integer id) {
        sysCardMapper.deleteLike(id);
    }


}
