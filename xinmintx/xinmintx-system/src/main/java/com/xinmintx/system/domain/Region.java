package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 region
 * 
 * @author xinmintx
 * @date 2020-03-04
 */
public class Region extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域主键 */
    private Long id;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String name;

    /** 区域上级标识 */
    @Excel(name = "区域上级标识")
    private Long pid;

    /** 地名简称 */
    @Excel(name = "地名简称")
    private String sname;

    /** 区域等级 */
    @Excel(name = "区域等级")
    private Long level;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String citycode;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String yzcode;

    /** 组合名称 */
    @Excel(name = "组合名称")
    private String mername;

    /** $column.columnComment */
    @Excel(name = "组合名称")
    private Long lng;

    /** $column.columnComment */
    @Excel(name = "组合名称")
    private Long lat;

    /** $column.columnComment */
    @Excel(name = "组合名称")
    private String pinyin;

    /** 该地区是否开设分公司0 没有 ；1 有 */
    @Excel(name = "该地区是否开设分公司0 没有 ；1 有")
    private Long regionalstate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setSname(String sname) 
    {
        this.sname = sname;
    }

    public String getSname() 
    {
        return sname;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setCitycode(String citycode) 
    {
        this.citycode = citycode;
    }

    public String getCitycode() 
    {
        return citycode;
    }
    public void setYzcode(String yzcode) 
    {
        this.yzcode = yzcode;
    }

    public String getYzcode() 
    {
        return yzcode;
    }
    public void setMername(String mername) 
    {
        this.mername = mername;
    }

    public String getMername() 
    {
        return mername;
    }
    public void setLng(Long lng) 
    {
        this.lng = lng;
    }

    public Long getLng() 
    {
        return lng;
    }
    public void setLat(Long lat) 
    {
        this.lat = lat;
    }

    public Long getLat() 
    {
        return lat;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }
    public void setRegionalstate(Long regionalstate) 
    {
        this.regionalstate = regionalstate;
    }

    public Long getRegionalstate() 
    {
        return regionalstate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pid", getPid())
            .append("sname", getSname())
            .append("level", getLevel())
            .append("citycode", getCitycode())
            .append("yzcode", getYzcode())
            .append("mername", getMername())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("pinyin", getPinyin())
            .append("regionalstate", getRegionalstate())
            .toString();
    }
}
