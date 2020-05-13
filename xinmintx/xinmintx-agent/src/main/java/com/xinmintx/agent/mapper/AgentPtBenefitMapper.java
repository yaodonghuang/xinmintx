package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.GoodPtcode;
import com.xinmintx.agent.model.GoodPtcodeInfo;
import com.xinmintx.agent.model.GoodsPtUser;
import com.xinmintx.agent.model.GoodsSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface AgentPtBenefitMapper {

    @Select("SELECT a.id,a.uid,a.sku_id skuId,a.per_price perPrice FROM good_ptcode_info a WHERE a.pid=#{regimentalId}")
    List<GoodPtcodeInfo>  selectgoodPtcodeInfo(@Param("regimentalId")Integer regimentalId);

    @Select("SELECT a.id,a.uid,a.ptgoods_id ptgoodsId,a.ptcode,a.ptnumber,a.addtime_datetime addtimeDatetime,a.endtime_datetime endtimeDatetime,a.common,a.status FROM good_ptcode a WHERE a.id=#{regimentalId} AND a.status=2")
    GoodPtcode selectgoodPtcode(@Param("regimentalId") Integer regimentalId);

    @Select("SELECT a.id, a.agent_price agentPrice FROM goods_sku a WHERE a.id=#{skuId}")
    GoodsSku selectSkuAgentMoney(Integer skuId);

    @Select("SELECT a.id,a.user_id userId,a.good_pt_id goodPtId,a.goods_id goodsId  FROM goods_pt_user a WHERE a.id=#{ptUserId}")
    GoodsPtUser selectGoodsPtUser(Integer ptUserId);
}
