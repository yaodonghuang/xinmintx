package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会员奖金池对象 member_bonus
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
public class MemberBonus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 0.未开启，1.开启 */
    @Excel(name = "0.未开启，1.开启")
    private Integer status;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 奖金池总金额 */
    @Excel(name = "奖金池总金额")
    private Double totalAmount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setTotalAmount(Double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() 
    {
        return totalAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("totalAmount", getTotalAmount())
            .toString();
    }
}
