package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNull() {
            addCriterion("recommender is null");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNotNull() {
            addCriterion("recommender is not null");
            return (Criteria) this;
        }

        public Criteria andRecommenderEqualTo(Integer value) {
            addCriterion("recommender =", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotEqualTo(Integer value) {
            addCriterion("recommender <>", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThan(Integer value) {
            addCriterion("recommender >", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommender >=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThan(Integer value) {
            addCriterion("recommender <", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThanOrEqualTo(Integer value) {
            addCriterion("recommender <=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderIn(List<Integer> values) {
            addCriterion("recommender in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotIn(List<Integer> values) {
            addCriterion("recommender not in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderBetween(Integer value1, Integer value2) {
            addCriterion("recommender between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotBetween(Integer value1, Integer value2) {
            addCriterion("recommender not between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("merchant_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("merchant_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("merchant_name =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("merchant_name <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("merchant_name >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_name >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("merchant_name <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("merchant_name <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("merchant_name like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("merchant_name not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("merchant_name in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("merchant_name not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("merchant_name between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("merchant_name not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andCellphoneIsNull() {
            addCriterion("cellphone is null");
            return (Criteria) this;
        }

        public Criteria andCellphoneIsNotNull() {
            addCriterion("cellphone is not null");
            return (Criteria) this;
        }

        public Criteria andCellphoneEqualTo(String value) {
            addCriterion("cellphone =", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotEqualTo(String value) {
            addCriterion("cellphone <>", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneGreaterThan(String value) {
            addCriterion("cellphone >", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneGreaterThanOrEqualTo(String value) {
            addCriterion("cellphone >=", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLessThan(String value) {
            addCriterion("cellphone <", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLessThanOrEqualTo(String value) {
            addCriterion("cellphone <=", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLike(String value) {
            addCriterion("cellphone like", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotLike(String value) {
            addCriterion("cellphone not like", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneIn(List<String> values) {
            addCriterion("cellphone in", values, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotIn(List<String> values) {
            addCriterion("cellphone not in", values, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneBetween(String value1, String value2) {
            addCriterion("cellphone between", value1, value2, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotBetween(String value1, String value2) {
            addCriterion("cellphone not between", value1, value2, "cellphone");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNull() {
            addCriterion("bank_card is null");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNotNull() {
            addCriterion("bank_card is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardEqualTo(String value) {
            addCriterion("bank_card =", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotEqualTo(String value) {
            addCriterion("bank_card <>", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThan(String value) {
            addCriterion("bank_card >", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card >=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThan(String value) {
            addCriterion("bank_card <", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThanOrEqualTo(String value) {
            addCriterion("bank_card <=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLike(String value) {
            addCriterion("bank_card like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotLike(String value) {
            addCriterion("bank_card not like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardIn(List<String> values) {
            addCriterion("bank_card in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotIn(List<String> values) {
            addCriterion("bank_card not in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardBetween(String value1, String value2) {
            addCriterion("bank_card between", value1, value2, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotBetween(String value1, String value2) {
            addCriterion("bank_card not between", value1, value2, "bankCard");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNull() {
            addCriterion("merchant_type is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNotNull() {
            addCriterion("merchant_type is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeEqualTo(Integer value) {
            addCriterion("merchant_type =", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotEqualTo(Integer value) {
            addCriterion("merchant_type <>", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThan(Integer value) {
            addCriterion("merchant_type >", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_type >=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThan(Integer value) {
            addCriterion("merchant_type <", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_type <=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIn(List<Integer> values) {
            addCriterion("merchant_type in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotIn(List<Integer> values) {
            addCriterion("merchant_type not in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeBetween(Integer value1, Integer value2) {
            addCriterion("merchant_type between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_type not between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTableIsNull() {
            addCriterion("merchant_table is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTableIsNotNull() {
            addCriterion("merchant_table is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTableEqualTo(Integer value) {
            addCriterion("merchant_table =", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableNotEqualTo(Integer value) {
            addCriterion("merchant_table <>", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableGreaterThan(Integer value) {
            addCriterion("merchant_table >", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_table >=", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableLessThan(Integer value) {
            addCriterion("merchant_table <", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_table <=", value, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableIn(List<Integer> values) {
            addCriterion("merchant_table in", values, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableNotIn(List<Integer> values) {
            addCriterion("merchant_table not in", values, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableBetween(Integer value1, Integer value2) {
            addCriterion("merchant_table between", value1, value2, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andMerchantTableNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_table not between", value1, value2, "merchantTable");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNull() {
            addCriterion("region_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNotNull() {
            addCriterion("region_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionNameEqualTo(String value) {
            addCriterion("region_name =", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotEqualTo(String value) {
            addCriterion("region_name <>", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThan(String value) {
            addCriterion("region_name >", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_name >=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThan(String value) {
            addCriterion("region_name <", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThanOrEqualTo(String value) {
            addCriterion("region_name <=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLike(String value) {
            addCriterion("region_name like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotLike(String value) {
            addCriterion("region_name not like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameIn(List<String> values) {
            addCriterion("region_name in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotIn(List<String> values) {
            addCriterion("region_name not in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameBetween(String value1, String value2) {
            addCriterion("region_name between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotBetween(String value1, String value2) {
            addCriterion("region_name not between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNull() {
            addCriterion("region_code is null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNotNull() {
            addCriterion("region_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeEqualTo(String value) {
            addCriterion("region_code =", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotEqualTo(String value) {
            addCriterion("region_code <>", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThan(String value) {
            addCriterion("region_code >", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("region_code >=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThan(String value) {
            addCriterion("region_code <", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThanOrEqualTo(String value) {
            addCriterion("region_code <=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLike(String value) {
            addCriterion("region_code like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotLike(String value) {
            addCriterion("region_code not like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIn(List<String> values) {
            addCriterion("region_code in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotIn(List<String> values) {
            addCriterion("region_code not in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeBetween(String value1, String value2) {
            addCriterion("region_code between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotBetween(String value1, String value2) {
            addCriterion("region_code not between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdIsNull() {
            addCriterion("merchant_branch_office_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdIsNotNull() {
            addCriterion("merchant_branch_office_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdEqualTo(Integer value) {
            addCriterion("merchant_branch_office_id =", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdNotEqualTo(Integer value) {
            addCriterion("merchant_branch_office_id <>", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdGreaterThan(Integer value) {
            addCriterion("merchant_branch_office_id >", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_branch_office_id >=", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdLessThan(Integer value) {
            addCriterion("merchant_branch_office_id <", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_branch_office_id <=", value, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdIn(List<Integer> values) {
            addCriterion("merchant_branch_office_id in", values, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdNotIn(List<Integer> values) {
            addCriterion("merchant_branch_office_id not in", values, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_branch_office_id between", value1, value2, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andMerchantBranchOfficeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_branch_office_id not between", value1, value2, "merchantBranchOfficeId");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNull() {
            addCriterion("turnover is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNotNull() {
            addCriterion("turnover is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverEqualTo(BigDecimal value) {
            addCriterion("turnover =", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotEqualTo(BigDecimal value) {
            addCriterion("turnover <>", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThan(BigDecimal value) {
            addCriterion("turnover >", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("turnover >=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThan(BigDecimal value) {
            addCriterion("turnover <", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThanOrEqualTo(BigDecimal value) {
            addCriterion("turnover <=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverIn(List<BigDecimal> values) {
            addCriterion("turnover in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotIn(List<BigDecimal> values) {
            addCriterion("turnover not in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("turnover between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("turnover not between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andIsReviewIsNull() {
            addCriterion("is_review is null");
            return (Criteria) this;
        }

        public Criteria andIsReviewIsNotNull() {
            addCriterion("is_review is not null");
            return (Criteria) this;
        }

        public Criteria andIsReviewEqualTo(Integer value) {
            addCriterion("is_review =", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotEqualTo(Integer value) {
            addCriterion("is_review <>", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewGreaterThan(Integer value) {
            addCriterion("is_review >", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_review >=", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewLessThan(Integer value) {
            addCriterion("is_review <", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewLessThanOrEqualTo(Integer value) {
            addCriterion("is_review <=", value, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewIn(List<Integer> values) {
            addCriterion("is_review in", values, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotIn(List<Integer> values) {
            addCriterion("is_review not in", values, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewBetween(Integer value1, Integer value2) {
            addCriterion("is_review between", value1, value2, "isReview");
            return (Criteria) this;
        }

        public Criteria andIsReviewNotBetween(Integer value1, Integer value2) {
            addCriterion("is_review not between", value1, value2, "isReview");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryIsNull() {
            addCriterion("merchant_category is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryIsNotNull() {
            addCriterion("merchant_category is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryEqualTo(Integer value) {
            addCriterion("merchant_category =", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryNotEqualTo(Integer value) {
            addCriterion("merchant_category <>", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryGreaterThan(Integer value) {
            addCriterion("merchant_category >", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_category >=", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryLessThan(Integer value) {
            addCriterion("merchant_category <", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_category <=", value, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryIn(List<Integer> values) {
            addCriterion("merchant_category in", values, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryNotIn(List<Integer> values) {
            addCriterion("merchant_category not in", values, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryBetween(Integer value1, Integer value2) {
            addCriterion("merchant_category between", value1, value2, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_category not between", value1, value2, "merchantCategory");
            return (Criteria) this;
        }

        public Criteria andPerCapitaIsNull() {
            addCriterion("per_capita is null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaIsNotNull() {
            addCriterion("per_capita is not null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaEqualTo(BigDecimal value) {
            addCriterion("per_capita =", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNotEqualTo(BigDecimal value) {
            addCriterion("per_capita <>", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaGreaterThan(BigDecimal value) {
            addCriterion("per_capita >", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita >=", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaLessThan(BigDecimal value) {
            addCriterion("per_capita <", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita <=", value, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaIn(List<BigDecimal> values) {
            addCriterion("per_capita in", values, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNotIn(List<BigDecimal> values) {
            addCriterion("per_capita not in", values, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita between", value1, value2, "perCapita");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita not between", value1, value2, "perCapita");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountIsNull() {
            addCriterion("freezing_amount is null");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountIsNotNull() {
            addCriterion("freezing_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountEqualTo(BigDecimal value) {
            addCriterion("freezing_amount =", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountNotEqualTo(BigDecimal value) {
            addCriterion("freezing_amount <>", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountGreaterThan(BigDecimal value) {
            addCriterion("freezing_amount >", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freezing_amount >=", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountLessThan(BigDecimal value) {
            addCriterion("freezing_amount <", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freezing_amount <=", value, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountIn(List<BigDecimal> values) {
            addCriterion("freezing_amount in", values, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountNotIn(List<BigDecimal> values) {
            addCriterion("freezing_amount not in", values, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezing_amount between", value1, value2, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andFreezingAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezing_amount not between", value1, value2, "freezingAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNull() {
            addCriterion("cash_amount is null");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNotNull() {
            addCriterion("cash_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCashAmountEqualTo(BigDecimal value) {
            addCriterion("cash_amount =", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotEqualTo(BigDecimal value) {
            addCriterion("cash_amount <>", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThan(BigDecimal value) {
            addCriterion("cash_amount >", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_amount >=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThan(BigDecimal value) {
            addCriterion("cash_amount <", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_amount <=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountIn(List<BigDecimal> values) {
            addCriterion("cash_amount in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotIn(List<BigDecimal> values) {
            addCriterion("cash_amount not in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_amount between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_amount not between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNull() {
            addCriterion("shop_address is null");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNotNull() {
            addCriterion("shop_address is not null");
            return (Criteria) this;
        }

        public Criteria andShopAddressEqualTo(String value) {
            addCriterion("shop_address =", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotEqualTo(String value) {
            addCriterion("shop_address <>", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThan(String value) {
            addCriterion("shop_address >", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("shop_address >=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThan(String value) {
            addCriterion("shop_address <", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThanOrEqualTo(String value) {
            addCriterion("shop_address <=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLike(String value) {
            addCriterion("shop_address like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotLike(String value) {
            addCriterion("shop_address not like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressIn(List<String> values) {
            addCriterion("shop_address in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotIn(List<String> values) {
            addCriterion("shop_address not in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressBetween(String value1, String value2) {
            addCriterion("shop_address between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotBetween(String value1, String value2) {
            addCriterion("shop_address not between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAnnouncementIsNull() {
            addCriterion("announcement is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementIsNotNull() {
            addCriterion("announcement is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementEqualTo(String value) {
            addCriterion("announcement =", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementNotEqualTo(String value) {
            addCriterion("announcement <>", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementGreaterThan(String value) {
            addCriterion("announcement >", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementGreaterThanOrEqualTo(String value) {
            addCriterion("announcement >=", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementLessThan(String value) {
            addCriterion("announcement <", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementLessThanOrEqualTo(String value) {
            addCriterion("announcement <=", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementLike(String value) {
            addCriterion("announcement like", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementNotLike(String value) {
            addCriterion("announcement not like", value, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementIn(List<String> values) {
            addCriterion("announcement in", values, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementNotIn(List<String> values) {
            addCriterion("announcement not in", values, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementBetween(String value1, String value2) {
            addCriterion("announcement between", value1, value2, "announcement");
            return (Criteria) this;
        }

        public Criteria andAnnouncementNotBetween(String value1, String value2) {
            addCriterion("announcement not between", value1, value2, "announcement");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIsNull() {
            addCriterion("idcard_front is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIsNotNull() {
            addCriterion("idcard_front is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontEqualTo(String value) {
            addCriterion("idcard_front =", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotEqualTo(String value) {
            addCriterion("idcard_front <>", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontGreaterThan(String value) {
            addCriterion("idcard_front >", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_front >=", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLessThan(String value) {
            addCriterion("idcard_front <", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLessThanOrEqualTo(String value) {
            addCriterion("idcard_front <=", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLike(String value) {
            addCriterion("idcard_front like", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotLike(String value) {
            addCriterion("idcard_front not like", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIn(List<String> values) {
            addCriterion("idcard_front in", values, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotIn(List<String> values) {
            addCriterion("idcard_front not in", values, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontBetween(String value1, String value2) {
            addCriterion("idcard_front between", value1, value2, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotBetween(String value1, String value2) {
            addCriterion("idcard_front not between", value1, value2, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIsNull() {
            addCriterion("idcard_back is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIsNotNull() {
            addCriterion("idcard_back is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackEqualTo(String value) {
            addCriterion("idcard_back =", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotEqualTo(String value) {
            addCriterion("idcard_back <>", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackGreaterThan(String value) {
            addCriterion("idcard_back >", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_back >=", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLessThan(String value) {
            addCriterion("idcard_back <", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLessThanOrEqualTo(String value) {
            addCriterion("idcard_back <=", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLike(String value) {
            addCriterion("idcard_back like", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotLike(String value) {
            addCriterion("idcard_back not like", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIn(List<String> values) {
            addCriterion("idcard_back in", values, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotIn(List<String> values) {
            addCriterion("idcard_back not in", values, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackBetween(String value1, String value2) {
            addCriterion("idcard_back between", value1, value2, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotBetween(String value1, String value2) {
            addCriterion("idcard_back not between", value1, value2, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontIsNull() {
            addCriterion("bank_card_front is null");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontIsNotNull() {
            addCriterion("bank_card_front is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontEqualTo(String value) {
            addCriterion("bank_card_front =", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontNotEqualTo(String value) {
            addCriterion("bank_card_front <>", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontGreaterThan(String value) {
            addCriterion("bank_card_front >", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_front >=", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontLessThan(String value) {
            addCriterion("bank_card_front <", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontLessThanOrEqualTo(String value) {
            addCriterion("bank_card_front <=", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontLike(String value) {
            addCriterion("bank_card_front like", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontNotLike(String value) {
            addCriterion("bank_card_front not like", value, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontIn(List<String> values) {
            addCriterion("bank_card_front in", values, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontNotIn(List<String> values) {
            addCriterion("bank_card_front not in", values, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontBetween(String value1, String value2) {
            addCriterion("bank_card_front between", value1, value2, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardFrontNotBetween(String value1, String value2) {
            addCriterion("bank_card_front not between", value1, value2, "bankCardFront");
            return (Criteria) this;
        }

        public Criteria andBankCardBackIsNull() {
            addCriterion("bank_card_back is null");
            return (Criteria) this;
        }

        public Criteria andBankCardBackIsNotNull() {
            addCriterion("bank_card_back is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardBackEqualTo(String value) {
            addCriterion("bank_card_back =", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackNotEqualTo(String value) {
            addCriterion("bank_card_back <>", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackGreaterThan(String value) {
            addCriterion("bank_card_back >", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_back >=", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackLessThan(String value) {
            addCriterion("bank_card_back <", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackLessThanOrEqualTo(String value) {
            addCriterion("bank_card_back <=", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackLike(String value) {
            addCriterion("bank_card_back like", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackNotLike(String value) {
            addCriterion("bank_card_back not like", value, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackIn(List<String> values) {
            addCriterion("bank_card_back in", values, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackNotIn(List<String> values) {
            addCriterion("bank_card_back not in", values, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackBetween(String value1, String value2) {
            addCriterion("bank_card_back between", value1, value2, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andBankCardBackNotBetween(String value1, String value2) {
            addCriterion("bank_card_back not between", value1, value2, "bankCardBack");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoIsNull() {
            addCriterion("door_head_photo is null");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoIsNotNull() {
            addCriterion("door_head_photo is not null");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoEqualTo(String value) {
            addCriterion("door_head_photo =", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoNotEqualTo(String value) {
            addCriterion("door_head_photo <>", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoGreaterThan(String value) {
            addCriterion("door_head_photo >", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("door_head_photo >=", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoLessThan(String value) {
            addCriterion("door_head_photo <", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoLessThanOrEqualTo(String value) {
            addCriterion("door_head_photo <=", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoLike(String value) {
            addCriterion("door_head_photo like", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoNotLike(String value) {
            addCriterion("door_head_photo not like", value, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoIn(List<String> values) {
            addCriterion("door_head_photo in", values, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoNotIn(List<String> values) {
            addCriterion("door_head_photo not in", values, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoBetween(String value1, String value2) {
            addCriterion("door_head_photo between", value1, value2, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andDoorHeadPhotoNotBetween(String value1, String value2) {
            addCriterion("door_head_photo not between", value1, value2, "doorHeadPhoto");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneIsNull() {
            addCriterion("store_photo_one is null");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneIsNotNull() {
            addCriterion("store_photo_one is not null");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneEqualTo(String value) {
            addCriterion("store_photo_one =", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneNotEqualTo(String value) {
            addCriterion("store_photo_one <>", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneGreaterThan(String value) {
            addCriterion("store_photo_one >", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneGreaterThanOrEqualTo(String value) {
            addCriterion("store_photo_one >=", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneLessThan(String value) {
            addCriterion("store_photo_one <", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneLessThanOrEqualTo(String value) {
            addCriterion("store_photo_one <=", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneLike(String value) {
            addCriterion("store_photo_one like", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneNotLike(String value) {
            addCriterion("store_photo_one not like", value, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneIn(List<String> values) {
            addCriterion("store_photo_one in", values, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneNotIn(List<String> values) {
            addCriterion("store_photo_one not in", values, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneBetween(String value1, String value2) {
            addCriterion("store_photo_one between", value1, value2, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoOneNotBetween(String value1, String value2) {
            addCriterion("store_photo_one not between", value1, value2, "storePhotoOne");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoIsNull() {
            addCriterion("store_photo_two is null");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoIsNotNull() {
            addCriterion("store_photo_two is not null");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoEqualTo(String value) {
            addCriterion("store_photo_two =", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoNotEqualTo(String value) {
            addCriterion("store_photo_two <>", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoGreaterThan(String value) {
            addCriterion("store_photo_two >", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoGreaterThanOrEqualTo(String value) {
            addCriterion("store_photo_two >=", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoLessThan(String value) {
            addCriterion("store_photo_two <", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoLessThanOrEqualTo(String value) {
            addCriterion("store_photo_two <=", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoLike(String value) {
            addCriterion("store_photo_two like", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoNotLike(String value) {
            addCriterion("store_photo_two not like", value, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoIn(List<String> values) {
            addCriterion("store_photo_two in", values, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoNotIn(List<String> values) {
            addCriterion("store_photo_two not in", values, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoBetween(String value1, String value2) {
            addCriterion("store_photo_two between", value1, value2, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andStorePhotoTwoNotBetween(String value1, String value2) {
            addCriterion("store_photo_two not between", value1, value2, "storePhotoTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNull() {
            addCriterion("business_license is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNotNull() {
            addCriterion("business_license is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseEqualTo(String value) {
            addCriterion("business_license =", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotEqualTo(String value) {
            addCriterion("business_license <>", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThan(String value) {
            addCriterion("business_license >", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("business_license >=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThan(String value) {
            addCriterion("business_license <", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("business_license <=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLike(String value) {
            addCriterion("business_license like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotLike(String value) {
            addCriterion("business_license not like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIn(List<String> values) {
            addCriterion("business_license in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotIn(List<String> values) {
            addCriterion("business_license not in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseBetween(String value1, String value2) {
            addCriterion("business_license between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("business_license not between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoIsNull() {
            addCriterion("other_photo is null");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoIsNotNull() {
            addCriterion("other_photo is not null");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoEqualTo(String value) {
            addCriterion("other_photo =", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoNotEqualTo(String value) {
            addCriterion("other_photo <>", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoGreaterThan(String value) {
            addCriterion("other_photo >", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("other_photo >=", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoLessThan(String value) {
            addCriterion("other_photo <", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoLessThanOrEqualTo(String value) {
            addCriterion("other_photo <=", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoLike(String value) {
            addCriterion("other_photo like", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoNotLike(String value) {
            addCriterion("other_photo not like", value, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoIn(List<String> values) {
            addCriterion("other_photo in", values, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoNotIn(List<String> values) {
            addCriterion("other_photo not in", values, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoBetween(String value1, String value2) {
            addCriterion("other_photo between", value1, value2, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andOtherPhotoNotBetween(String value1, String value2) {
            addCriterion("other_photo not between", value1, value2, "otherPhoto");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Long value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Long value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Long value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Long value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Long value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Long value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Long> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Long> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Long value1, Long value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Long value1, Long value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationIsNull() {
            addCriterion("shop_specification is null");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationIsNotNull() {
            addCriterion("shop_specification is not null");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationEqualTo(String value) {
            addCriterion("shop_specification =", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationNotEqualTo(String value) {
            addCriterion("shop_specification <>", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationGreaterThan(String value) {
            addCriterion("shop_specification >", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("shop_specification >=", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationLessThan(String value) {
            addCriterion("shop_specification <", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationLessThanOrEqualTo(String value) {
            addCriterion("shop_specification <=", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationLike(String value) {
            addCriterion("shop_specification like", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationNotLike(String value) {
            addCriterion("shop_specification not like", value, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationIn(List<String> values) {
            addCriterion("shop_specification in", values, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationNotIn(List<String> values) {
            addCriterion("shop_specification not in", values, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationBetween(String value1, String value2) {
            addCriterion("shop_specification between", value1, value2, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andShopSpecificationNotBetween(String value1, String value2) {
            addCriterion("shop_specification not between", value1, value2, "shopSpecification");
            return (Criteria) this;
        }

        public Criteria andBusinessModelIsNull() {
            addCriterion("business_model is null");
            return (Criteria) this;
        }

        public Criteria andBusinessModelIsNotNull() {
            addCriterion("business_model is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessModelEqualTo(String value) {
            addCriterion("business_model =", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelNotEqualTo(String value) {
            addCriterion("business_model <>", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelGreaterThan(String value) {
            addCriterion("business_model >", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelGreaterThanOrEqualTo(String value) {
            addCriterion("business_model >=", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelLessThan(String value) {
            addCriterion("business_model <", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelLessThanOrEqualTo(String value) {
            addCriterion("business_model <=", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelLike(String value) {
            addCriterion("business_model like", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelNotLike(String value) {
            addCriterion("business_model not like", value, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelIn(List<String> values) {
            addCriterion("business_model in", values, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelNotIn(List<String> values) {
            addCriterion("business_model not in", values, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelBetween(String value1, String value2) {
            addCriterion("business_model between", value1, value2, "businessModel");
            return (Criteria) this;
        }

        public Criteria andBusinessModelNotBetween(String value1, String value2) {
            addCriterion("business_model not between", value1, value2, "businessModel");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedIsNull() {
            addCriterion("amount_raised is null");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedIsNotNull() {
            addCriterion("amount_raised is not null");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedEqualTo(BigDecimal value) {
            addCriterion("amount_raised =", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedNotEqualTo(BigDecimal value) {
            addCriterion("amount_raised <>", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedGreaterThan(BigDecimal value) {
            addCriterion("amount_raised >", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_raised >=", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedLessThan(BigDecimal value) {
            addCriterion("amount_raised <", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_raised <=", value, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedIn(List<BigDecimal> values) {
            addCriterion("amount_raised in", values, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedNotIn(List<BigDecimal> values) {
            addCriterion("amount_raised not in", values, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_raised between", value1, value2, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andAmountRaisedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_raised not between", value1, value2, "amountRaised");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailIsNull() {
            addCriterion("merchant_category_detail is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailIsNotNull() {
            addCriterion("merchant_category_detail is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailEqualTo(String value) {
            addCriterion("merchant_category_detail =", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailNotEqualTo(String value) {
            addCriterion("merchant_category_detail <>", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailGreaterThan(String value) {
            addCriterion("merchant_category_detail >", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_category_detail >=", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailLessThan(String value) {
            addCriterion("merchant_category_detail <", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailLessThanOrEqualTo(String value) {
            addCriterion("merchant_category_detail <=", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailLike(String value) {
            addCriterion("merchant_category_detail like", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailNotLike(String value) {
            addCriterion("merchant_category_detail not like", value, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailIn(List<String> values) {
            addCriterion("merchant_category_detail in", values, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailNotIn(List<String> values) {
            addCriterion("merchant_category_detail not in", values, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailBetween(String value1, String value2) {
            addCriterion("merchant_category_detail between", value1, value2, "merchantCategoryDetail");
            return (Criteria) this;
        }

        public Criteria andMerchantCategoryDetailNotBetween(String value1, String value2) {
            addCriterion("merchant_category_detail not between", value1, value2, "merchantCategoryDetail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}