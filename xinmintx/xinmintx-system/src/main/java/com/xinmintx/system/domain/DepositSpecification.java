package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 提现规格对象 deposit_specification
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
public class DepositSpecification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 起提金额 */
    @Excel(name = "起提金额")
    private Long depositSum;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double serviceCharge;

    /** 预留一 */
    @Excel(name = "预留一")
    private String reservedOne;

    /** 预留二 */
    @Excel(name = "预留二")
    private String reservedTwo;

    /** 预留三 */
    @Excel(name = "预留三")
    private String reservedThree;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDepositSum(Long depositSum) 
    {
        this.depositSum = depositSum;
    }

    public Long getDepositSum() 
    {
        return depositSum;
    }
    public void setServiceCharge(Double serviceCharge)
    {
        this.serviceCharge = serviceCharge;
    }

    public Double getServiceCharge()
    {
        return serviceCharge;
    }
    public void setReservedOne(String reservedOne) 
    {
        this.reservedOne = reservedOne;
    }

    public String getReservedOne() 
    {
        return reservedOne;
    }
    public void setReservedTwo(String reservedTwo) 
    {
        this.reservedTwo = reservedTwo;
    }

    public String getReservedTwo() 
    {
        return reservedTwo;
    }
    public void setReservedThree(String reservedThree) 
    {
        this.reservedThree = reservedThree;
    }

    public String getReservedThree() 
    {
        return reservedThree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("depositSum", getDepositSum())
            .append("serviceCharge", getServiceCharge())
            .append("reservedOne", getReservedOne())
            .append("reservedTwo", getReservedTwo())
            .append("reservedThree", getReservedThree())
            .toString();
    }
}
