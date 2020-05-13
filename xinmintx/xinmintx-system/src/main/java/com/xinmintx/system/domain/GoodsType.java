package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品分类对象 goods_type
 * 
 * @author xinmintx
 * @date 2019-12-12
 */
public class GoodsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 上级目录id(一级目录时为0,二级目录时填写一级目录id) */
    @Excel(name = "上级目录id(一级目录时为0,二级目录时填写一级目录id)")
    private Long parentId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNum;

    /** 目录等级(1一级目录,2二级目录) */
    @Excel(name = "目录等级(1一级目录,2二级目录)")
    private Long level;

    /** 三级目录图片 */
    @Excel(name = "三级目录图片")
    private String typephoto;

    private String parentName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum()
    {
        return orderNum;
    }
    public Long getLevel()
    {
        return level;
    }
    public void setTypephoto(String typephoto)
    {
        this.typephoto = typephoto;
    }

    public String getTypephoto()
    {
        return typephoto;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("typeName", getTypeName())
            .append("level", getLevel())
            .append("typephoto", getTypephoto())
            .append("parentName", getParentName())
            .toString();
    }
}
