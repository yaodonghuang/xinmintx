package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 定时器对象 cron
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
public class Cron extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 定时器配置 */
    @Excel(name = "定时器配置")
    private String cron;

    /** 设置具体触发时间 */
    @Excel(name = "设置具体触发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 定时器类型 */
    @Excel(name = "定时器类型")
    private String type;

    private String nowState;

    public String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNowState() {
        return nowState;
    }

    public void setNowState(String nowState) {
        this.nowState = nowState;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCron(String cron) 
    {
        this.cron = cron;
    }

    public String getCron() 
    {
        return cron;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cron", getCron())
            .append("time", getTime())
            .append("type", getType())
            .toString();
    }
}
