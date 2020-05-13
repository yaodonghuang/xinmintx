package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.CheckInCommentMapper;
import com.xinmintx.system.domain.CheckInComment;
import com.xinmintx.system.service.ICheckInCommentService;
import com.xinmintx.common.core.text.Convert;

/**
 * 打卡签到记录评论Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Service
public class CheckInCommentServiceImpl implements ICheckInCommentService 
{
    @Autowired
    private CheckInCommentMapper checkInCommentMapper;

    /**
     * 查询打卡签到记录评论
     * 
     * @param id 打卡签到记录评论ID
     * @return 打卡签到记录评论
     */
    @Override
    public CheckInComment selectCheckInCommentById(Long id)
    {
        return checkInCommentMapper.selectCheckInCommentById(id);
    }

    /**
     * 查询打卡签到记录评论列表
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 打卡签到记录评论
     */
    @Override
    public List<CheckInComment> selectCheckInCommentList(CheckInComment checkInComment)
    {
        return checkInCommentMapper.selectCheckInCommentList(checkInComment);
    }

    /**
     * 新增打卡签到记录评论
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 结果
     */
    @Override
    public int insertCheckInComment(CheckInComment checkInComment)
    {
        checkInComment.setCreateTime(DateUtils.getNowDate());
        return checkInCommentMapper.insertCheckInComment(checkInComment);
    }

    /**
     * 修改打卡签到记录评论
     * 
     * @param checkInComment 打卡签到记录评论
     * @return 结果
     */
    @Override
    public int updateCheckInComment(CheckInComment checkInComment)
    {
        checkInComment.setUpdateTime(DateUtils.getNowDate());
        return checkInCommentMapper.updateCheckInComment(checkInComment);
    }

    /**
     * 删除打卡签到记录评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCheckInCommentByIds(String ids)
    {
        return checkInCommentMapper.deleteCheckInCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除打卡签到记录评论信息
     * 
     * @param id 打卡签到记录评论ID
     * @return 结果
     */
    @Override
    public int deleteCheckInCommentById(Long id)
    {
        return checkInCommentMapper.deleteCheckInCommentById(id);
    }
}
