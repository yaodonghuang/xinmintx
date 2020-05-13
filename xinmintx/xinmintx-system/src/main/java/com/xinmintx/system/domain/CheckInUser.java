package com.xinmintx.system.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/29 0029
 * @time: 下午 17:40
 * @Description: 打卡人,评论,点赞
 */

public class CheckInUser {
    private Integer id;
    private String username;
    private List<CheckInComment> comments;
    private List<LikenumList> likenumLists;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CheckInComment> getComments() {
        return comments;
    }

    public void setComments(List<CheckInComment> comments) {
        this.comments = comments;
    }

    public List<LikenumList> getLikenumLists() {
        return likenumLists;
    }

    public void setLikenumLists(List<LikenumList> likenumLists) {
        this.likenumLists = likenumLists;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
