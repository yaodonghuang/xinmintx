package com.xinmintx.hstx.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xinmintx.hstx.pojo.po.GoodsType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
@Data
public class GoodsTypeVo extends GoodsType {

    private List<GoodsTypeVo> types;
}
