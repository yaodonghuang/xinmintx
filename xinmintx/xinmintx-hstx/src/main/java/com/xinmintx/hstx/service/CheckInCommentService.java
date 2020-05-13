package com.xinmintx.hstx.service;


import com.xinmintx.hstx.pojo.vo.CommentVo;

import java.util.List;


/**
 * 用户评论
 */
public interface CheckInCommentService {

    /**
     * 加入评论信息
     *
     * @param memberCheckInId
     * @param commentName
     * @param comment
     */
    void insertComment(int memberCheckInId, int commentName, String comment);

    /**
     * 获取评论列表
     *
     * @param memberCheckInId
     * @return
     */
    List<CommentVo> listComment(int memberCheckInId);


}
