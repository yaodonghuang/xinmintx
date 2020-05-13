package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 活动对象 red_envelope_activities
 * 
 * @author xinmintx
 * @date 2020-03-10
 */
public class RedEnvelopeActivities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 活动开始时间 */
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date redStartTime;

    /** 活动结束时间 */
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date redEndTime;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private Long start;

    private Long startTime;

    private Long endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRedStartTime(Date redStartTime) 
    {
        this.redStartTime = redStartTime;
    }

    public Date getRedStartTime() 
    {
        return redStartTime;
    }
    public void setRedEndTime(Date redEndTime) 
    {
        this.redEndTime = redEndTime;
    }

    public Date getRedEndTime() 
    {
        return redEndTime;
    }
    public void setStart(Long start) 
    {
        this.start = start;
    }

    public Long getStart() 
    {
        return start;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("redStartTime", getRedStartTime())
            .append("redEndTime", getRedEndTime())
            .append("start", getStart())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}
