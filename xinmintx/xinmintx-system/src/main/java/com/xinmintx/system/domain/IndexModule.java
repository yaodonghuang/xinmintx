package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 首页模块配置对象 index_module
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
public class IndexModule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 请求地址 */
    @Excel(name = "请求地址")
    private String url;

    /** 显示多少数据 */
    @Excel(name = "显示多少数据")
    private Long pageSize;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNum;

    /** 模块类型(1预购,2热销,3拼团) */
    @Excel(name = "模块类型(1预购,2热销,3拼团)")
    private Long moduleType;

    /** 是否启用(0否,1是) */
    @Excel(name = "是否启用(0否,1是)")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setPageSize(Long pageSize) 
    {
        this.pageSize = pageSize;
    }

    public Long getPageSize() 
    {
        return pageSize;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setModuleType(Long moduleType) 
    {
        this.moduleType = moduleType;
    }

    public Long getModuleType() 
    {
        return moduleType;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("url", getUrl())
            .append("pageSize", getPageSize())
            .append("orderNum", getOrderNum())
            .append("moduleType", getModuleType())
            .append("status", getStatus())
            .toString();
    }
}
