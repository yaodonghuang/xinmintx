package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodPtgoodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodPtgoodExample() {
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

        public Criteria andPtgoodsIdIsNull() {
            addCriterion("ptgoods_id is null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdIsNotNull() {
            addCriterion("ptgoods_id is not null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdEqualTo(Integer value) {
            addCriterion("ptgoods_id =", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdNotEqualTo(Integer value) {
            addCriterion("ptgoods_id <>", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdGreaterThan(Integer value) {
            addCriterion("ptgoods_id >", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ptgoods_id >=", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdLessThan(Integer value) {
            addCriterion("ptgoods_id <", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ptgoods_id <=", value, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdIn(List<Integer> values) {
            addCriterion("ptgoods_id in", values, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdNotIn(List<Integer> values) {
            addCriterion("ptgoods_id not in", values, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("ptgoods_id between", value1, value2, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ptgoods_id not between", value1, value2, "ptgoodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameIsNull() {
            addCriterion("ptgoods_name is null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameIsNotNull() {
            addCriterion("ptgoods_name is not null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameEqualTo(String value) {
            addCriterion("ptgoods_name =", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameNotEqualTo(String value) {
            addCriterion("ptgoods_name <>", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameGreaterThan(String value) {
            addCriterion("ptgoods_name >", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ptgoods_name >=", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameLessThan(String value) {
            addCriterion("ptgoods_name <", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameLessThanOrEqualTo(String value) {
            addCriterion("ptgoods_name <=", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameLike(String value) {
            addCriterion("ptgoods_name like", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameNotLike(String value) {
            addCriterion("ptgoods_name not like", value, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameIn(List<String> values) {
            addCriterion("ptgoods_name in", values, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameNotIn(List<String> values) {
            addCriterion("ptgoods_name not in", values, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameBetween(String value1, String value2) {
            addCriterion("ptgoods_name between", value1, value2, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNameNotBetween(String value1, String value2) {
            addCriterion("ptgoods_name not between", value1, value2, "ptgoodsName");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPtPriceIsNull() {
            addCriterion("pt_price is null");
            return (Criteria) this;
        }

        public Criteria andPtPriceIsNotNull() {
            addCriterion("pt_price is not null");
            return (Criteria) this;
        }

        public Criteria andPtPriceEqualTo(BigDecimal value) {
            addCriterion("pt_price =", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceNotEqualTo(BigDecimal value) {
            addCriterion("pt_price <>", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceGreaterThan(BigDecimal value) {
            addCriterion("pt_price >", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pt_price >=", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceLessThan(BigDecimal value) {
            addCriterion("pt_price <", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pt_price <=", value, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceIn(List<BigDecimal> values) {
            addCriterion("pt_price in", values, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceNotIn(List<BigDecimal> values) {
            addCriterion("pt_price not in", values, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pt_price between", value1, value2, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pt_price not between", value1, value2, "ptPrice");
            return (Criteria) this;
        }

        public Criteria andPtSizeIsNull() {
            addCriterion("pt_size is null");
            return (Criteria) this;
        }

        public Criteria andPtSizeIsNotNull() {
            addCriterion("pt_size is not null");
            return (Criteria) this;
        }

        public Criteria andPtSizeEqualTo(Integer value) {
            addCriterion("pt_size =", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeNotEqualTo(Integer value) {
            addCriterion("pt_size <>", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeGreaterThan(Integer value) {
            addCriterion("pt_size >", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pt_size >=", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeLessThan(Integer value) {
            addCriterion("pt_size <", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeLessThanOrEqualTo(Integer value) {
            addCriterion("pt_size <=", value, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeIn(List<Integer> values) {
            addCriterion("pt_size in", values, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeNotIn(List<Integer> values) {
            addCriterion("pt_size not in", values, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeBetween(Integer value1, Integer value2) {
            addCriterion("pt_size between", value1, value2, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("pt_size not between", value1, value2, "ptSize");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursIsNull() {
            addCriterion("pt_validhours is null");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursIsNotNull() {
            addCriterion("pt_validhours is not null");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursEqualTo(Double value) {
            addCriterion("pt_validhours =", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursNotEqualTo(Double value) {
            addCriterion("pt_validhours <>", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursGreaterThan(Double value) {
            addCriterion("pt_validhours >", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursGreaterThanOrEqualTo(Double value) {
            addCriterion("pt_validhours >=", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursLessThan(Double value) {
            addCriterion("pt_validhours <", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursLessThanOrEqualTo(Double value) {
            addCriterion("pt_validhours <=", value, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursIn(List<Double> values) {
            addCriterion("pt_validhours in", values, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursNotIn(List<Double> values) {
            addCriterion("pt_validhours not in", values, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursBetween(Double value1, Double value2) {
            addCriterion("pt_validhours between", value1, value2, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andPtValidhoursNotBetween(Double value1, Double value2) {
            addCriterion("pt_validhours not between", value1, value2, "ptValidhours");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberIsNull() {
            addCriterion("ptgoods_number is null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberIsNotNull() {
            addCriterion("ptgoods_number is not null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberEqualTo(Integer value) {
            addCriterion("ptgoods_number =", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberNotEqualTo(Integer value) {
            addCriterion("ptgoods_number <>", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberGreaterThan(Integer value) {
            addCriterion("ptgoods_number >", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("ptgoods_number >=", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberLessThan(Integer value) {
            addCriterion("ptgoods_number <", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberLessThanOrEqualTo(Integer value) {
            addCriterion("ptgoods_number <=", value, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberIn(List<Integer> values) {
            addCriterion("ptgoods_number in", values, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberNotIn(List<Integer> values) {
            addCriterion("ptgoods_number not in", values, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberBetween(Integer value1, Integer value2) {
            addCriterion("ptgoods_number between", value1, value2, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andPtgoodsNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("ptgoods_number not between", value1, value2, "ptgoodsNumber");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbIsNull() {
            addCriterion("ptgoods_thumb is null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbIsNotNull() {
            addCriterion("ptgoods_thumb is not null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbEqualTo(String value) {
            addCriterion("ptgoods_thumb =", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbNotEqualTo(String value) {
            addCriterion("ptgoods_thumb <>", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbGreaterThan(String value) {
            addCriterion("ptgoods_thumb >", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbGreaterThanOrEqualTo(String value) {
            addCriterion("ptgoods_thumb >=", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbLessThan(String value) {
            addCriterion("ptgoods_thumb <", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbLessThanOrEqualTo(String value) {
            addCriterion("ptgoods_thumb <=", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbLike(String value) {
            addCriterion("ptgoods_thumb like", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbNotLike(String value) {
            addCriterion("ptgoods_thumb not like", value, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbIn(List<String> values) {
            addCriterion("ptgoods_thumb in", values, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbNotIn(List<String> values) {
            addCriterion("ptgoods_thumb not in", values, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbBetween(String value1, String value2) {
            addCriterion("ptgoods_thumb between", value1, value2, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsThumbNotBetween(String value1, String value2) {
            addCriterion("ptgoods_thumb not between", value1, value2, "ptgoodsThumb");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsIsNull() {
            addCriterion("ptgoods_imgs is null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsIsNotNull() {
            addCriterion("ptgoods_imgs is not null");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsEqualTo(String value) {
            addCriterion("ptgoods_imgs =", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsNotEqualTo(String value) {
            addCriterion("ptgoods_imgs <>", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsGreaterThan(String value) {
            addCriterion("ptgoods_imgs >", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsGreaterThanOrEqualTo(String value) {
            addCriterion("ptgoods_imgs >=", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsLessThan(String value) {
            addCriterion("ptgoods_imgs <", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsLessThanOrEqualTo(String value) {
            addCriterion("ptgoods_imgs <=", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsLike(String value) {
            addCriterion("ptgoods_imgs like", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsNotLike(String value) {
            addCriterion("ptgoods_imgs not like", value, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsIn(List<String> values) {
            addCriterion("ptgoods_imgs in", values, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsNotIn(List<String> values) {
            addCriterion("ptgoods_imgs not in", values, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsBetween(String value1, String value2) {
            addCriterion("ptgoods_imgs between", value1, value2, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andPtgoodsImgsNotBetween(String value1, String value2) {
            addCriterion("ptgoods_imgs not between", value1, value2, "ptgoodsImgs");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(Date value) {
            addCriterion("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(Date value) {
            addCriterion("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(Date value) {
            addCriterion("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(Date value) {
            addCriterion("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(Date value) {
            addCriterion("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<Date> values) {
            addCriterion("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<Date> values) {
            addCriterion("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(Date value1, Date value2) {
            addCriterion("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(Date value1, Date value2) {
            addCriterion("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNull() {
            addCriterion("is_sale is null");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNotNull() {
            addCriterion("is_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaleEqualTo(Integer value) {
            addCriterion("is_sale =", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotEqualTo(Integer value) {
            addCriterion("is_sale <>", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThan(Integer value) {
            addCriterion("is_sale >", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sale >=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThan(Integer value) {
            addCriterion("is_sale <", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThanOrEqualTo(Integer value) {
            addCriterion("is_sale <=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleIn(List<Integer> values) {
            addCriterion("is_sale in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotIn(List<Integer> values) {
            addCriterion("is_sale not in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleBetween(Integer value1, Integer value2) {
            addCriterion("is_sale between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sale not between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andPtTimesIsNull() {
            addCriterion("pt_times is null");
            return (Criteria) this;
        }

        public Criteria andPtTimesIsNotNull() {
            addCriterion("pt_times is not null");
            return (Criteria) this;
        }

        public Criteria andPtTimesEqualTo(Integer value) {
            addCriterion("pt_times =", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesNotEqualTo(Integer value) {
            addCriterion("pt_times <>", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesGreaterThan(Integer value) {
            addCriterion("pt_times >", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("pt_times >=", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesLessThan(Integer value) {
            addCriterion("pt_times <", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesLessThanOrEqualTo(Integer value) {
            addCriterion("pt_times <=", value, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesIn(List<Integer> values) {
            addCriterion("pt_times in", values, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesNotIn(List<Integer> values) {
            addCriterion("pt_times not in", values, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesBetween(Integer value1, Integer value2) {
            addCriterion("pt_times between", value1, value2, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andPtTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("pt_times not between", value1, value2, "ptTimes");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(Integer value) {
            addCriterion("group_type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(Integer value) {
            addCriterion("group_type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(Integer value) {
            addCriterion("group_type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(Integer value) {
            addCriterion("group_type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<Integer> values) {
            addCriterion("group_type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<Integer> values) {
            addCriterion("group_type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_type not between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andCommonIsNull() {
            addCriterion("common is null");
            return (Criteria) this;
        }

        public Criteria andCommonIsNotNull() {
            addCriterion("common is not null");
            return (Criteria) this;
        }

        public Criteria andCommonEqualTo(String value) {
            addCriterion("common =", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotEqualTo(String value) {
            addCriterion("common <>", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonGreaterThan(String value) {
            addCriterion("common >", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonGreaterThanOrEqualTo(String value) {
            addCriterion("common >=", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonLessThan(String value) {
            addCriterion("common <", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonLessThanOrEqualTo(String value) {
            addCriterion("common <=", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonLike(String value) {
            addCriterion("common like", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotLike(String value) {
            addCriterion("common not like", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonIn(List<String> values) {
            addCriterion("common in", values, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotIn(List<String> values) {
            addCriterion("common not in", values, "common");
            return (Criteria) this;
        }

        public Criteria andCommonBetween(String value1, String value2) {
            addCriterion("common between", value1, value2, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotBetween(String value1, String value2) {
            addCriterion("common not between", value1, value2, "common");
            return (Criteria) this;
        }

        public Criteria andNameActivityIsNull() {
            addCriterion("name_activity is null");
            return (Criteria) this;
        }

        public Criteria andNameActivityIsNotNull() {
            addCriterion("name_activity is not null");
            return (Criteria) this;
        }

        public Criteria andNameActivityEqualTo(String value) {
            addCriterion("name_activity =", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityNotEqualTo(String value) {
            addCriterion("name_activity <>", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityGreaterThan(String value) {
            addCriterion("name_activity >", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityGreaterThanOrEqualTo(String value) {
            addCriterion("name_activity >=", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityLessThan(String value) {
            addCriterion("name_activity <", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityLessThanOrEqualTo(String value) {
            addCriterion("name_activity <=", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityLike(String value) {
            addCriterion("name_activity like", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityNotLike(String value) {
            addCriterion("name_activity not like", value, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityIn(List<String> values) {
            addCriterion("name_activity in", values, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityNotIn(List<String> values) {
            addCriterion("name_activity not in", values, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityBetween(String value1, String value2) {
            addCriterion("name_activity between", value1, value2, "nameActivity");
            return (Criteria) this;
        }

        public Criteria andNameActivityNotBetween(String value1, String value2) {
            addCriterion("name_activity not between", value1, value2, "nameActivity");
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