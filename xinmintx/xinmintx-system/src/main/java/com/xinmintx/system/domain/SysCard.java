package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 后台打卡对象 sys_card
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
public class SysCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 录音 */
    @Excel(name = "录音")
    private String recordUrl;

    /** 美文 */
    @Excel(name = "美文")
    private String characters;

    /** 图片 */
    @Excel(name = "图片")
    private String pictureUrl;

    /** 1.早  2.晚 */
    @Excel(name = "1.早  2.晚")
    private Long timeFrame;

    /**创建时间*/
    @Excel(name="创建时间")
    private Date createTime;

    /**备注*/
    @Excel(name="备注")
    private String remark;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRecordUrl(String recordUrl) 
    {
        this.recordUrl = recordUrl;
    }

    public String getRecordUrl() 
    {
        return recordUrl;
    }
    public void setCharacters(String characters) 
    {
        this.characters = characters;
    }

    public String getCharacters() 
    {
        return characters;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() 
    {
        return pictureUrl;
    }
    public void setTimeFrame(Long timeFrame) 
    {
        this.timeFrame = timeFrame;
    }

    public Long getTimeFrame() 
    {
        return timeFrame;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordUrl", getRecordUrl())
            .append("characters", getCharacters())
            .append("pictureUrl", getPictureUrl())
            .append("createTime", getCreateTime())
            .append("timeFrame", getTimeFrame())
            .append("remark", getRemark())
            .toString();
    }
}
