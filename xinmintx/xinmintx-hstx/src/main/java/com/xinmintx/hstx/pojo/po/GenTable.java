package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 代码生成业务表
 * </p>
 *
 * @author sw
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GenTable对象", description="代码生成业务表")
public class GenTable extends Model<GenTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "table_id", type = IdType.AUTO)
    private Long tableId;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "表描述")
    private String tableComment;

    @ApiModelProperty(value = "实体类名称")
    private String className;

    @ApiModelProperty(value = "使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;

    @ApiModelProperty(value = "生成包路径")
    private String packageName;

    @ApiModelProperty(value = "生成模块名")
    private String moduleName;

    @ApiModelProperty(value = "生成业务名")
    private String businessName;

    @ApiModelProperty(value = "生成功能名")
    private String functionName;

    @ApiModelProperty(value = "生成功能作者")
    private String functionAuthor;

    @ApiModelProperty(value = "其它生成选项")
    private String options;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.tableId;
    }

}
