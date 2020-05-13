package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.CheckInCommentMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.po.CheckInComment;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.CommentVo;
import com.xinmintx.hstx.service.CheckInCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sw
 * @date 2019\11\22
 */
@Service
public class CheckInCommentServiceImpl implements CheckInCommentService {
    @Autowired
    private CheckInCommentMapper checkInCommentMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 加入评论信息
     *
     * @param memberCheckInId 打卡表id
     * @param commentName     评论名字
     * @param comment         评论语
     */
    @Override
    public void insertComment(int memberCheckInId, int commentName, String comment) {
        CheckInComment checkInComment = new CheckInComment();
        checkInComment.setCheckInId(memberCheckInId);
        checkInComment.setCommentName(commentName);
        checkInComment.setComment(comment);
        checkInComment.setCreateTime(new Date());
        checkInCommentMapper.insert(checkInComment);
    }

    /**
     * 获取评论列表
     *
     * @param memberCheckInId 打卡记录表id
     * @return 评论列表
     */
    @Override
    public List<CommentVo> listComment(int memberCheckInId) {
        //根据打卡表id查询评论
        QueryWrapper<CheckInComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("check_in_id",memberCheckInId);
        //根据倒叙查询
        queryWrapper.orderByDesc("create_time");
        List<CheckInComment> checkInComments = checkInCommentMapper.selectList(queryWrapper);

        List<CommentVo> list = new ArrayList<>();
        if (list == null || checkInComments.size() == 0) {
            return null;
        } else {
            for (CheckInComment checkInComment : checkInComments) {
                //获取会员id
                Integer memberId = checkInComment.getCommentName();
                Member member = memberMapper.selectById(memberId);

                //获取评论表id
                Integer id = checkInComment.getId();
                //获取打卡记录表id
                Integer checkInId = checkInComment.getCheckInId();
                //获取评论人名字
                String name = member.getName();
                //获取评论语
                String comment = checkInComment.getComment();
                //获取评论时间
                Date createTime = checkInComment.getCreateTime();
                //获取微信头像
                String avatarUrl = member.getAvatarUrl();

                CommentVo comments = new CommentVo(id, checkInId, name, comment, createTime, avatarUrl);
                list.add(comments);
            }
            return list;
        }
    }

}
