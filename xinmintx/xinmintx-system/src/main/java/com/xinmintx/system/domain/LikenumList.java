package com.xinmintx.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.Date;

/**
 * 点赞列对象 likenum_list
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
public class LikenumList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 打卡表主键 */
    @Excel(name = "打卡表主键")
    private Long likenum_int_id;

    /** 点赞人 */
    @Excel(name = "点赞人")
    private String likenum_name;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date create_time;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getLikenum_int_id() {
        return likenum_int_id;
    }

    public void setLikenum_int_id(Long likenum_int_id) {
        this.likenum_int_id = likenum_int_id;
    }

    public String getLikenum_name() {
        return likenum_name;
    }

    public void setLikenum_name(String likenum_name) {
        this.likenum_name = likenum_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    //
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("likenumIntId", getLikenumIntId())
//            .append("likenumName", getLikenumName())
//            .append("createTime", getCreateTime())
//            .toString();
//    }
}
