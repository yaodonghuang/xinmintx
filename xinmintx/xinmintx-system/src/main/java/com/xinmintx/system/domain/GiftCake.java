package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商户生日蛋糕对象 gift_cake
 * 
 * @author xinmintx
 * @date 2020-01-19
 */
public class GiftCake extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 蛋糕名称 */
    @Excel(name = "蛋糕名称")
    private String name;

    /** 蛋糕尺寸 */
    @Excel(name = "蛋糕尺寸")
    private Integer size;

    /** 蛋糕价格 */
    @Excel(name = "蛋糕价格")
    private Double price;

    /** 蛋糕图片 */
    @Excel(name = "蛋糕图片")
    private String pic;

    /** 蛋糕类型 */
    @Excel(name = "蛋糕类型")
    private String type;

    // 照片
    private String turnsPhoto;

    public String getTurnsPhoto() {
        return turnsPhoto;
    }

    public void setTurnsPhoto(String turnsPhoto) {
        this.turnsPhoto = turnsPhoto;
    }

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
    public void setSize(Integer size) 
    {
        this.size = size;
    }

    public Integer getSize() 
    {
        return size;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setPic(String pic) 
    {
        this.pic = pic;
    }

    public String getPic() 
    {
        return pic;
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
            .append("name", getName())
            .append("size", getSize())
            .append("price", getPrice())
            .append("pic", getPic())
            .append("type", getType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
