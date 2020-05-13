package com.xinmintx.community.mapper;

import com.xinmintx.community.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 社区Mapper
 */
public interface CommunityMapper {
    Integer getPresidentByCommunityId(@Param("communityId") Integer communityId) ;

    Member getMemberByToken(@Param("token") String token);

    int insert(Community community);

    Integer ifExistsCommunity(@Param("com") Community community);

    int insertCommunityMemberInfo(@Param("communityId") Long communityId, @Param("memberId") Long memberId);

    Integer ifJoinCommunity(@Param("communityId") Long communityId, @Param("memberId") Long memberId);

    List<Community> queryCommunity(@Param("regionCode") String regionCode, @Param("memberId") Integer memberId);

    List<Community> myCommunity(Integer memberId);

    int upNotice(@Param("notice") String notice, @Param("memberId") Integer memberId, @Param("id") Long id);

    int upIcon(@Param("icon") String icon ,@Param("memberId") Integer memberId, @Param("id") Long id);

    Integer ifPresident(@Param("communityId") Long communityId, @Param("createId") Long createId);

    Long ifMember(@Param("memberId") Integer memberId);

    int updatePredisent(@Param("id") Long id, @Param("createId") Long createId);

    int updateMemberRegion(@Param("id") Long memberId, @Param("regionCode") String regionCode);

    Integer getCountByCommunityId(@Param("communityId")Integer communityId);

    int updateMemberName(@Param("memberName")String memberName, @Param("communityId")Long communityId, @Param("memberId")Integer memberId);

    List<Map<String,Object>> getByCommunityId(Integer communityId);

    Integer getMerchantIdByPhone(String phone);

    List<Long> getMerchantIdsByCommunityId(@Param("communityId")Long communityId);

    Integer queryProprieter(@Param("memberId") Integer memberId,@Param("id") Integer id);

    Community queryCommunityById(Integer memberId, Integer id);

    List<MerchantGoods> getGoodsListByIds(@Param("list") List<Long> merchantIds,@Param("goodsName")String goodsName, @Param("type")Integer type);

    Community queryProprieterName(@Param("createId") Long createId,@Param("id")Integer id);

    int upAssist(@Param("communityId") Integer communityId,@Param("memberId") Integer memberId,@Param("deputyHelp") Integer deputyHelp);

    List<MemberExt> getCommunityMemberList(Integer communityId);

    Member getMemberById(@Param("memberId") Integer memberId);

    int outOfCommunity(@Param("communityId") Integer communityId,@Param("memberId") Integer memberId);

    String queryMember(Integer memberId);

    Member queryMemberInfo(int intValue);

    String getAddressCodeById(@Param("communityId") Long communityId);

    List<Merchant> getMerchantList(@Param("addressCode") String addressCode);

    int insertCommunityProprieter(@Param("communityId") Long communityId, @Param("memberId") Long memberId,@Param("rank") Integer rank);

    int updatePresentToMember(@Param("communityId") Long communityId, @Param("memberId") Long memberId, @Param("rank") Integer rank);

    MerchantGoods queryMerchantGoods(Long goodsId);

    Community queryAddress(Long communityId);

    List<MerchantGoodsPic> queryMerchantGoodsPic(Long goodsId);

    List<PurchaseHistory> queryGoodsOrder(Long goodsId);

    List<Community> fuzzyQueryCommunity(@Param("regionCode")String regionCode,@Param("name") String name);

    List<CommunityMerchant> getMerchantInfoList(@Param("communityId") Long communityId);

    Community queryCommunityInfo(Long communityId);

    int queryCommunityMerchant(@Param("communityId") Long communityId,@Param("type")int type);

    int queryCommunityDeputy(@Param("communityId") Long communityId,@Param("type") int type);

    Integer getUserIdByPhone(@Param("phone") String phone,@Param("type") int type);
}
