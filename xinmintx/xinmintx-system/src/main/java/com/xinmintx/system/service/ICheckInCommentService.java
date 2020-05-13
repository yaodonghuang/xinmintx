package com.xinmintx.system.service;

import com.xinmintx.system.domain.CheckInComment;
import java.util.List;

/**
 * 打卡签到记录评论Service接口
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
public interface ICheckInCommentService 
{
    /**
     * 查询打卡签到记录评论
     * 
     * @param id 打卡签到记录评论ID
     * @return 打卡签到记录评论
     */
    public CheckInComment selectCheckInCommentById(Long id);

    /**
     * 查询打卡签到记录评论列表
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 打卡签到记录评论集合
     */
    public List<CheckInComment> selectCheckInCommentList(CheckInComment checkInComment);

    /**
     * 新增打卡签到记录评论
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 结果
     */
    public int insertCheckInComment(CheckInComment checkInComment);

    /**
     * 修改打卡签到记录评论
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 结果
     */
    public int updateCheckInComment(CheckInComment checkInComment);

    /**
     * 批量删除打卡签到记录评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckInCommentByIds(String ids);

    /**
     * 删除打卡签到记录评论信息
     * 
     * @param id 打卡签到记录评论ID
     * @return 结果
     */
    public int deleteCheckInCommentById(Long id);
}
