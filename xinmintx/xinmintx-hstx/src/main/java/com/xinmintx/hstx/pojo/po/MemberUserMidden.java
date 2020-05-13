package com.xinmintx.hstx.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wcj
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberUserMidden extends Model<MemberUserMidden> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * user表id
     */
    private Integer userId;

    /**
     * member表id
     */
    private Integer memberId;

    /**
     * member,user关联0为user,1为memer
     */
    private Integer source;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
