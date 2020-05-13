package com.xinmintx.agent.model;

import java.util.ArrayList;
import java.util.List;

public class DepositSpecificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepositSpecificationExample() {
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

        public Criteria andDepositSumIsNull() {
            addCriterion("deposit_sum is null");
            return (Criteria) this;
        }

        public Criteria andDepositSumIsNotNull() {
            addCriterion("deposit_sum is not null");
            return (Criteria) this;
        }

        public Criteria andDepositSumEqualTo(Long value) {
            addCriterion("deposit_sum =", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumNotEqualTo(Long value) {
            addCriterion("deposit_sum <>", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumGreaterThan(Long value) {
            addCriterion("deposit_sum >", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumGreaterThanOrEqualTo(Long value) {
            addCriterion("deposit_sum >=", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumLessThan(Long value) {
            addCriterion("deposit_sum <", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumLessThanOrEqualTo(Long value) {
            addCriterion("deposit_sum <=", value, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumIn(List<Long> values) {
            addCriterion("deposit_sum in", values, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumNotIn(List<Long> values) {
            addCriterion("deposit_sum not in", values, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumBetween(Long value1, Long value2) {
            addCriterion("deposit_sum between", value1, value2, "depositSum");
            return (Criteria) this;
        }

        public Criteria andDepositSumNotBetween(Long value1, Long value2) {
            addCriterion("deposit_sum not between", value1, value2, "depositSum");
            return (Criteria) this;
        }

        public Criteria andServiceChargeIsNull() {
            addCriterion("service_charge is null");
            return (Criteria) this;
        }

        public Criteria andServiceChargeIsNotNull() {
            addCriterion("service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andServiceChargeEqualTo(Double value) {
            addCriterion("service_charge =", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeNotEqualTo(Double value) {
            addCriterion("service_charge <>", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeGreaterThan(Double value) {
            addCriterion("service_charge >", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeGreaterThanOrEqualTo(Double value) {
            addCriterion("service_charge >=", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeLessThan(Double value) {
            addCriterion("service_charge <", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeLessThanOrEqualTo(Double value) {
            addCriterion("service_charge <=", value, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeIn(List<Double> values) {
            addCriterion("service_charge in", values, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeNotIn(List<Double> values) {
            addCriterion("service_charge not in", values, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeBetween(Double value1, Double value2) {
            addCriterion("service_charge between", value1, value2, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andServiceChargeNotBetween(Double value1, Double value2) {
            addCriterion("service_charge not between", value1, value2, "serviceCharge");
            return (Criteria) this;
        }

        public Criteria andReservedOneIsNull() {
            addCriterion("reserved_one is null");
            return (Criteria) this;
        }

        public Criteria andReservedOneIsNotNull() {
            addCriterion("reserved_one is not null");
            return (Criteria) this;
        }

        public Criteria andReservedOneEqualTo(String value) {
            addCriterion("reserved_one =", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneNotEqualTo(String value) {
            addCriterion("reserved_one <>", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneGreaterThan(String value) {
            addCriterion("reserved_one >", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneGreaterThanOrEqualTo(String value) {
            addCriterion("reserved_one >=", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneLessThan(String value) {
            addCriterion("reserved_one <", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneLessThanOrEqualTo(String value) {
            addCriterion("reserved_one <=", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneLike(String value) {
            addCriterion("reserved_one like", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneNotLike(String value) {
            addCriterion("reserved_one not like", value, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneIn(List<String> values) {
            addCriterion("reserved_one in", values, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneNotIn(List<String> values) {
            addCriterion("reserved_one not in", values, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneBetween(String value1, String value2) {
            addCriterion("reserved_one between", value1, value2, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedOneNotBetween(String value1, String value2) {
            addCriterion("reserved_one not between", value1, value2, "reservedOne");
            return (Criteria) this;
        }

        public Criteria andReservedTwoIsNull() {
            addCriterion("reserved_two is null");
            return (Criteria) this;
        }

        public Criteria andReservedTwoIsNotNull() {
            addCriterion("reserved_two is not null");
            return (Criteria) this;
        }

        public Criteria andReservedTwoEqualTo(String value) {
            addCriterion("reserved_two =", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoNotEqualTo(String value) {
            addCriterion("reserved_two <>", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoGreaterThan(String value) {
            addCriterion("reserved_two >", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoGreaterThanOrEqualTo(String value) {
            addCriterion("reserved_two >=", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoLessThan(String value) {
            addCriterion("reserved_two <", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoLessThanOrEqualTo(String value) {
            addCriterion("reserved_two <=", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoLike(String value) {
            addCriterion("reserved_two like", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoNotLike(String value) {
            addCriterion("reserved_two not like", value, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoIn(List<String> values) {
            addCriterion("reserved_two in", values, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoNotIn(List<String> values) {
            addCriterion("reserved_two not in", values, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoBetween(String value1, String value2) {
            addCriterion("reserved_two between", value1, value2, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedTwoNotBetween(String value1, String value2) {
            addCriterion("reserved_two not between", value1, value2, "reservedTwo");
            return (Criteria) this;
        }

        public Criteria andReservedThreeIsNull() {
            addCriterion("reserved_three is null");
            return (Criteria) this;
        }

        public Criteria andReservedThreeIsNotNull() {
            addCriterion("reserved_three is not null");
            return (Criteria) this;
        }

        public Criteria andReservedThreeEqualTo(String value) {
            addCriterion("reserved_three =", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeNotEqualTo(String value) {
            addCriterion("reserved_three <>", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeGreaterThan(String value) {
            addCriterion("reserved_three >", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeGreaterThanOrEqualTo(String value) {
            addCriterion("reserved_three >=", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeLessThan(String value) {
            addCriterion("reserved_three <", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeLessThanOrEqualTo(String value) {
            addCriterion("reserved_three <=", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeLike(String value) {
            addCriterion("reserved_three like", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeNotLike(String value) {
            addCriterion("reserved_three not like", value, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeIn(List<String> values) {
            addCriterion("reserved_three in", values, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeNotIn(List<String> values) {
            addCriterion("reserved_three not in", values, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeBetween(String value1, String value2) {
            addCriterion("reserved_three between", value1, value2, "reservedThree");
            return (Criteria) this;
        }

        public Criteria andReservedThreeNotBetween(String value1, String value2) {
            addCriterion("reserved_three not between", value1, value2, "reservedThree");
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