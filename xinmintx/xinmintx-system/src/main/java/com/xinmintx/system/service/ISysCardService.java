package com.xinmintx.system.service;

import com.xinmintx.system.domain.CheckInUser;
import com.xinmintx.system.domain.SysCard;

import java.util.List;

/**
 * 后台打卡Service接口
 * 
 * @author xinmintx
 * @date 2019-11-13
 */
public interface ISysCardService 
{
    /**
     * 查询后台打卡
     * 
     * @param id 后台打卡ID
     * @return 后台打卡
     */
    public SysCard selectSysCardById(Long id);

    /**
     * 查询后台打卡列表
     * 
     * @param sysCard 后台打卡
     * @return 后台打卡集合
     */
    public List<SysCard> selectSysCardList(SysCard sysCard);

    /**
     * 新增后台打卡
     * 
     * @param sysCard 后台打卡
     * @return 结果
     */
    public int insertSysCard(SysCard sysCard);

    /**
     * 修改后台打卡
     * 
     * @param sysCard 后台打卡
     * @return 结果
     */
    public int updateSysCard(SysCard sysCard);

    /**
     * 批量删除后台打卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCardByIds(String ids);

    /**
     * 删除后台打卡信息
     * 
     * @param id 后台打卡ID
     * @return 结果
     */
    public int deleteSysCardById(Long id);

    /**
     * 查询打卡记录,评论,点赞
     * @param id
     * @return
     */
    List<CheckInUser> selectCheckInById(Integer id);

    /**
     * 根据会员id删除打卡记录
     * @param id 会员id
     */
    void deleteMemberCheckIn(Integer id);

    /**
     * 根据id删除评论
     * @param id
     */
    void deleteComment (Integer id);

    /**
     * 根据id删除点赞
     * @param id
     */
    void deleteLike (Integer id);
}
