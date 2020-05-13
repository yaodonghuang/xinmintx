package com.xinmintx.hstx.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer id;

    private Integer checkInId;

    private String commentName;

    private String comment;

    private Date createTime;

    private String avatarUrl;
}
