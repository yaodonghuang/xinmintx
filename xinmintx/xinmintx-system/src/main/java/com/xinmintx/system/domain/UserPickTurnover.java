package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 提货点营业额百分比设置对象 user_pick_turnover
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
public class UserPickTurnover extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userIdPickup;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long turnover;

    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserIdPickup(Long userIdPickup) 
    {
        this.userIdPickup = userIdPickup;
    }

    public Long getUserIdPickup() 
    {
        return userIdPickup;
    }
    public void setTurnover(Long turnover) 
    {
        this.turnover = turnover;
    }

    public Long getTurnover() 
    {
        return turnover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userIdPickup", getUserIdPickup())
            .append("turnover", getTurnover())
                .append("name", getName())
            .toString();
    }
}
