package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 member_tree
 * 
 * @author xinmintx
 * @date 2020-01-13
 */
public class MemberTree extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员树id */
    private Long treeId;

    /** 会员树code */
    @Excel(name = "会员树code")
    private String treeCode;

    /** 会员表主键id */
    @Excel(name = "会员表主键id")
    private Long memberId;

    /** 上层树id */
    @Excel(name = "上层树id")
    private Long parentTreeId;

    public void setTreeId(Long treeId) 
    {
        this.treeId = treeId;
    }

    public Long getTreeId() 
    {
        return treeId;
    }
    public void setTreeCode(String treeCode) 
    {
        this.treeCode = treeCode;
    }

    public String getTreeCode() 
    {
        return treeCode;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setParentTreeId(Long parentTreeId) 
    {
        this.parentTreeId = parentTreeId;
    }

    public Long getParentTreeId() 
    {
        return parentTreeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("treeId", getTreeId())
            .append("treeCode", getTreeCode())
            .append("memberId", getMemberId())
            .append("createTime", getCreateTime())
            .append("parentTreeId", getParentTreeId())
            .toString();
    }
}
