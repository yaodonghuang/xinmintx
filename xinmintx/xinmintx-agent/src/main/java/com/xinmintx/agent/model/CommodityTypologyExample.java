package com.xinmintx.agent.model;

import java.util.ArrayList;
import java.util.List;

public class CommodityTypologyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommodityTypologyExample() {
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

        public Criteria andKindIdIsNull() {
            addCriterion("kind_id is null");
            return (Criteria) this;
        }

        public Criteria andKindIdIsNotNull() {
            addCriterion("kind_id is not null");
            return (Criteria) this;
        }

        public Criteria andKindIdEqualTo(Integer value) {
            addCriterion("kind_id =", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotEqualTo(Integer value) {
            addCriterion("kind_id <>", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdGreaterThan(Integer value) {
            addCriterion("kind_id >", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kind_id >=", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdLessThan(Integer value) {
            addCriterion("kind_id <", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdLessThanOrEqualTo(Integer value) {
            addCriterion("kind_id <=", value, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdIn(List<Integer> values) {
            addCriterion("kind_id in", values, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotIn(List<Integer> values) {
            addCriterion("kind_id not in", values, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdBetween(Integer value1, Integer value2) {
            addCriterion("kind_id between", value1, value2, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindIdNotBetween(Integer value1, Integer value2) {
            addCriterion("kind_id not between", value1, value2, "kindId");
            return (Criteria) this;
        }

        public Criteria andKindTypeIsNull() {
            addCriterion("kind_type is null");
            return (Criteria) this;
        }

        public Criteria andKindTypeIsNotNull() {
            addCriterion("kind_type is not null");
            return (Criteria) this;
        }

        public Criteria andKindTypeEqualTo(String value) {
            addCriterion("kind_type =", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeNotEqualTo(String value) {
            addCriterion("kind_type <>", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeGreaterThan(String value) {
            addCriterion("kind_type >", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeGreaterThanOrEqualTo(String value) {
            addCriterion("kind_type >=", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeLessThan(String value) {
            addCriterion("kind_type <", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeLessThanOrEqualTo(String value) {
            addCriterion("kind_type <=", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeLike(String value) {
            addCriterion("kind_type like", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeNotLike(String value) {
            addCriterion("kind_type not like", value, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeIn(List<String> values) {
            addCriterion("kind_type in", values, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeNotIn(List<String> values) {
            addCriterion("kind_type not in", values, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeBetween(String value1, String value2) {
            addCriterion("kind_type between", value1, value2, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindTypeNotBetween(String value1, String value2) {
            addCriterion("kind_type not between", value1, value2, "kindType");
            return (Criteria) this;
        }

        public Criteria andKindPriceIsNull() {
            addCriterion("kind_price is null");
            return (Criteria) this;
        }

        public Criteria andKindPriceIsNotNull() {
            addCriterion("kind_price is not null");
            return (Criteria) this;
        }

        public Criteria andKindPriceEqualTo(Double value) {
            addCriterion("kind_price =", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceNotEqualTo(Double value) {
            addCriterion("kind_price <>", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceGreaterThan(Double value) {
            addCriterion("kind_price >", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("kind_price >=", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceLessThan(Double value) {
            addCriterion("kind_price <", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceLessThanOrEqualTo(Double value) {
            addCriterion("kind_price <=", value, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceIn(List<Double> values) {
            addCriterion("kind_price in", values, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceNotIn(List<Double> values) {
            addCriterion("kind_price not in", values, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceBetween(Double value1, Double value2) {
            addCriterion("kind_price between", value1, value2, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindPriceNotBetween(Double value1, Double value2) {
            addCriterion("kind_price not between", value1, value2, "kindPrice");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryIsNull() {
            addCriterion("kind_repertory is null");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryIsNotNull() {
            addCriterion("kind_repertory is not null");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryEqualTo(Integer value) {
            addCriterion("kind_repertory =", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryNotEqualTo(Integer value) {
            addCriterion("kind_repertory <>", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryGreaterThan(Integer value) {
            addCriterion("kind_repertory >", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("kind_repertory >=", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryLessThan(Integer value) {
            addCriterion("kind_repertory <", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryLessThanOrEqualTo(Integer value) {
            addCriterion("kind_repertory <=", value, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryIn(List<Integer> values) {
            addCriterion("kind_repertory in", values, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryNotIn(List<Integer> values) {
            addCriterion("kind_repertory not in", values, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryBetween(Integer value1, Integer value2) {
            addCriterion("kind_repertory between", value1, value2, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindRepertoryNotBetween(Integer value1, Integer value2) {
            addCriterion("kind_repertory not between", value1, value2, "kindRepertory");
            return (Criteria) this;
        }

        public Criteria andKindPictureIsNull() {
            addCriterion("kind_picture is null");
            return (Criteria) this;
        }

        public Criteria andKindPictureIsNotNull() {
            addCriterion("kind_picture is not null");
            return (Criteria) this;
        }

        public Criteria andKindPictureEqualTo(String value) {
            addCriterion("kind_picture =", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureNotEqualTo(String value) {
            addCriterion("kind_picture <>", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureGreaterThan(String value) {
            addCriterion("kind_picture >", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureGreaterThanOrEqualTo(String value) {
            addCriterion("kind_picture >=", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureLessThan(String value) {
            addCriterion("kind_picture <", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureLessThanOrEqualTo(String value) {
            addCriterion("kind_picture <=", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureLike(String value) {
            addCriterion("kind_picture like", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureNotLike(String value) {
            addCriterion("kind_picture not like", value, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureIn(List<String> values) {
            addCriterion("kind_picture in", values, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureNotIn(List<String> values) {
            addCriterion("kind_picture not in", values, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureBetween(String value1, String value2) {
            addCriterion("kind_picture between", value1, value2, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andKindPictureNotBetween(String value1, String value2) {
            addCriterion("kind_picture not between", value1, value2, "kindPicture");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIsNull() {
            addCriterion("seckill_price is null");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIsNotNull() {
            addCriterion("seckill_price is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceEqualTo(Double value) {
            addCriterion("seckill_price =", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotEqualTo(Double value) {
            addCriterion("seckill_price <>", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceGreaterThan(Double value) {
            addCriterion("seckill_price >", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("seckill_price >=", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceLessThan(Double value) {
            addCriterion("seckill_price <", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceLessThanOrEqualTo(Double value) {
            addCriterion("seckill_price <=", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIn(List<Double> values) {
            addCriterion("seckill_price in", values, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotIn(List<Double> values) {
            addCriterion("seckill_price not in", values, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceBetween(Double value1, Double value2) {
            addCriterion("seckill_price between", value1, value2, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotBetween(Double value1, Double value2) {
            addCriterion("seckill_price not between", value1, value2, "seckillPrice");
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