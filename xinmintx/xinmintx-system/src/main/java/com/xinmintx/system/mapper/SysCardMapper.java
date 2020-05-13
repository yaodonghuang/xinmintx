package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.CheckInComment;
import com.xinmintx.system.domain.LikenumList;
import com.xinmintx.system.domain.MemberCheckInBean;
import com.xinmintx.system.domain.SysCard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.util.Internal;

import java.util.List;

/**
 * 后台打卡Mapper接口
 *
 * @author xinmintx
 * @date 2019-11-13
 */
@Mapper
public interface SysCardMapper {

    /**
     * 查询后台打卡
     *
     * @param id 后台打卡ID
     * @return 后台打卡
     */
    public SysCard selectSysCardById(Long id);

    /**
     * 查询后台打卡列表
     *
     * @param sysCard 后台打卡
     * @return 后台打卡集合
     */
    public List<SysCard> selectSysCardList(SysCard sysCard);

    /**
     * 新增后台打卡
     *
     * @param sysCard 后台打卡
     * @return 结果
     */
    public int insertSysCard(SysCard sysCard);

    /**
     * 修改后台打卡
     *
     * @param sysCard 后台打卡
     * @return 结果
     */
    public int updateSysCard(SysCard sysCard);

    /**
     * 删除后台打卡
     *
     * @param id 后台打卡ID
     * @return 结果
     */
    public int deleteSysCardById(Long id);

    /**
     * 批量删除后台打卡
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCardByIds(String[] ids);

    @Select("select b.id,c.name,b.create_time from sys_card a,member_check_in b,member c where a.id=b.syscard_id and b.member_id=c.id and a.id=#{id}")
    List<MemberCheckInBean> selectMemberCheckIn(@Param("id") Integer id);

    @Select("select a.id,b.name,a.comment,a.create_time from check_in_comment a,member b where a.comment_name=b.id and check_in_id=#{id}")
    List<CheckInComment> selectCheckInComment(@Param("id") Integer id);

    @Select("select * from likenum_list where likenum_int_id=#{id}")
    List<LikenumList> selectLikenumList(@Param("id") Integer id);

    @Delete("DELETE FROM check_in_comment WHERE id = #{id}")
    void deleteComment(@Param("id") Integer id);

    @Delete("DELETE FROM likenum_list WHERE id = #{id}")
    void deleteLike(@Param("id") Integer id);

    @Delete("DELETE FROM member_check_in WHERE id=#{id}")
    void deleteMemberCheckIn(@Param("id") Integer id);
}
