package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.model.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MerchantMapper  {
    Merchant selectByTel(String phone);

    void updateBytel(@Param("merchant") Merchant merchant);

    void addBytel(@Param("merchants") Merchant merchants);

    Integer selectIphone(String phone);

    Merchant queryByToken(String token);

    Long createGoods(MerchantGoods merchantGoods);

    void addPic(@Param("list")List<MerchantGoodsPic> list);

    MerchantGoods queryMerchantGoods(Long goodsId);

    List<MerchantGoodsPic> queryMerchantGoodsPic(Long goodsId);

    void delPic(Long mgoodsId);

    int upMerchantGoods(@Param("merchantGoods") MerchantGoods merchantGoods);

    List<MerchantGoods> queryMerchantGoodsList(Integer id);

    int upGoodsState(@Param("goodsId") Long goodsId,@Param("state") Integer state);

    List<MerchantGoods> queryMerchantGoodsListByState(@Param("id") Integer id, @Param("state") Integer state);

    int delGoods(Long goodsId);

    void delGoodsPic(Long goodsId);

    Merchant selectById(String token);

    int insertFactoryCashInfo(Integer id, String requestSN);

    List<GoodsOrder> queryOrdersList(@Param("communityId") Integer communityId,@Param("merchantId") Integer merchantId,@Param("beginDate") String beginDate,@Param("endDate") String endDate,@Param("orderState")Integer orderState);

    List<GoodsOrder> queryOrderList(@Param("communityId") Integer communityId,@Param("merchantId") Integer merchantId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);

    List<OrderExt> queryOrder(long id);

    Member queryMember(String token);

    List<CommunityOrder> queryCommunityOrder(@Param("merchantId") Integer merchantId, @Param("beginDate") String beginDate,@Param("endDate") String endDate);

    List<GoodsOrderDetail> selectOrder(long id);

    void updateOrderAcceptOrder(@Param("list") List<Long> idList,@Param("acceptOrder") Integer acceptOrder);

    int upOrderStatu(@Param("list")List<Long> idList,@Param("orderStatu") Integer orderStatu);

    void upGoodsOrderStatu(@Param("orderId") Integer orderId,@Param("orderStatu") Integer orderStatu);

    List<CommunityOrder> queryOrderHistory(@Param("merchantId") Integer merchantId, @Param("beginDate") String beginDate,@Param("endDate") String endDate);

    List<GoodsOrder> queryHistoricalOrder(@Param("communityId") Integer communityId,@Param("merchantId") Integer merchantId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);

    List<CommunityOrder> queryCommunityOrderUseForPrinter(@Param("merchantId") Integer merchantId,@Param("communityId") Long communityId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);

    String getOrderNoByIds(@Param("id") Long id);

    int updateOrderAndDetailState(@Param("orderId") String orderId, @Param("orderState") Integer orderState, @Param("ifPay") Integer ifPay, @Param("acceptOrder")Integer acceptOrder);

    int reduceMemberFreezMoney(@Param("memberId") String memberId, @Param("money") String money);

    BigDecimal getFreezingAmount(@Param("memberId") String memberId);

    int insertLogs(MemberAmountLog log);

    int reduceMerchantFreezMoney(@Param("merchantId") Long merchantId, @Param("money") BigDecimal money);

    BigDecimal getFreezingAmountOfMerchant(@Param("merchantId") Long merchantId);

    int insertMerchantLogs(MerchantAmountLog log);

    List<CommunityCashInfo> queryByRequest(Integer id);

    MerchantBankCard getBankCardInfoById(@Param("bankCardId")Integer bankCardId);

    int insertPn(PoboNotify pn);

    int updateMerchantMoney(@Param("money") BigDecimal money, @Param("requestSn") String requestSn);

    List<MerchantAmountLog> queryTransaction(@Param("id") Integer id,@Param("type")Integer type);

    Merchant getMerchantInfo(@Param("requestSN")String requestSN);

    List<PurchaseHistory> queryGoodsOrder(Long goodsId);

    String queryAddress(Long communityId);

    MerchantPrinter queryPrinterSn(Integer id);

    void updatePrintNumber(long id);

    GoodsOrder selectGoodsOrder(Integer orderId);

    UserAccount getUserAccount(@Param("userId") String userId);

    int insertUserAccount(UserAccount userAccount);

    int updateUserAccount(UserAccount userAccount);

    int insertUserAccountRecord(UserAccountRecord userAccountRecord);
}