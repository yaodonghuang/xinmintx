package com.xinmintx.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinmintx.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 打卡签到记录评论对象 check_in_comment
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
public class CheckInComment extends BaseEntity {

    /** 主键 */
    private Long id;

    /** 会员id */
    private String name;

    /** 评论内容 */
    private String comment;

    /**评论时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
