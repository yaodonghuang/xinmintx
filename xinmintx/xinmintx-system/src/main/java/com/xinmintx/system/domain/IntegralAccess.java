package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 积分获取方式对象 integral_access
 * 
 * @author xinmintx
 * @date 2019-12-13
 */
public class IntegralAccess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 积分获取方式代码 */
    @Excel(name = "积分获取方式代码")
    private Long accessType;

    /** 获取方式说明 */
    @Excel(name = "获取方式说明")
    private String description;

    /** 可获得的积分值 */
    @Excel(name = "可获得的积分值")
    private Double integralValue;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAccessType(Long accessType) 
    {
        this.accessType = accessType;
    }

    public Long getAccessType() 
    {
        return accessType;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIntegralValue(Double integralValue) 
    {
        this.integralValue = integralValue;
    }

    public Double getIntegralValue() 
    {
        return integralValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("accessType", getAccessType())
            .append("description", getDescription())
            .append("integralValue", getIntegralValue())
            .toString();
    }
}
