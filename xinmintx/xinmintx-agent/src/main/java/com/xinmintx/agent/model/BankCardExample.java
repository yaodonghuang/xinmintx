package com.xinmintx.agent.model;

import java.util.ArrayList;
import java.util.List;

public class BankCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BankCardExample() {
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

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Integer value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Integer value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Integer value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Integer value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Integer value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Integer> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Integer> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Integer value1, Integer value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Integer value1, Integer value2) {
            addCriterion("start not between", value1, value2, "start");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNull() {
            addCriterion("bank_type is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNotNull() {
            addCriterion("bank_type is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeEqualTo(String value) {
            addCriterion("bank_type =", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotEqualTo(String value) {
            addCriterion("bank_type <>", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThan(String value) {
            addCriterion("bank_type >", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_type >=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThan(String value) {
            addCriterion("bank_type <", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThanOrEqualTo(String value) {
            addCriterion("bank_type <=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLike(String value) {
            addCriterion("bank_type like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotLike(String value) {
            addCriterion("bank_type not like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeIn(List<String> values) {
            addCriterion("bank_type in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotIn(List<String> values) {
            addCriterion("bank_type not in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeBetween(String value1, String value2) {
            addCriterion("bank_type between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotBetween(String value1, String value2) {
            addCriterion("bank_type not between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andCardholderNameIsNull() {
            addCriterion("cardholder_name is null");
            return (Criteria) this;
        }

        public Criteria andCardholderNameIsNotNull() {
            addCriterion("cardholder_name is not null");
            return (Criteria) this;
        }

        public Criteria andCardholderNameEqualTo(String value) {
            addCriterion("cardholder_name =", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameNotEqualTo(String value) {
            addCriterion("cardholder_name <>", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameGreaterThan(String value) {
            addCriterion("cardholder_name >", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameGreaterThanOrEqualTo(String value) {
            addCriterion("cardholder_name >=", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameLessThan(String value) {
            addCriterion("cardholder_name <", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameLessThanOrEqualTo(String value) {
            addCriterion("cardholder_name <=", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameLike(String value) {
            addCriterion("cardholder_name like", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameNotLike(String value) {
            addCriterion("cardholder_name not like", value, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameIn(List<String> values) {
            addCriterion("cardholder_name in", values, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameNotIn(List<String> values) {
            addCriterion("cardholder_name not in", values, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameBetween(String value1, String value2) {
            addCriterion("cardholder_name between", value1, value2, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCardholderNameNotBetween(String value1, String value2) {
            addCriterion("cardholder_name not between", value1, value2, "cardholderName");
            return (Criteria) this;
        }

        public Criteria andCommonOneIsNull() {
            addCriterion("common_one is null");
            return (Criteria) this;
        }

        public Criteria andCommonOneIsNotNull() {
            addCriterion("common_one is not null");
            return (Criteria) this;
        }

        public Criteria andCommonOneEqualTo(String value) {
            addCriterion("common_one =", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneNotEqualTo(String value) {
            addCriterion("common_one <>", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneGreaterThan(String value) {
            addCriterion("common_one >", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneGreaterThanOrEqualTo(String value) {
            addCriterion("common_one >=", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneLessThan(String value) {
            addCriterion("common_one <", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneLessThanOrEqualTo(String value) {
            addCriterion("common_one <=", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneLike(String value) {
            addCriterion("common_one like", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneNotLike(String value) {
            addCriterion("common_one not like", value, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneIn(List<String> values) {
            addCriterion("common_one in", values, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneNotIn(List<String> values) {
            addCriterion("common_one not in", values, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneBetween(String value1, String value2) {
            addCriterion("common_one between", value1, value2, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonOneNotBetween(String value1, String value2) {
            addCriterion("common_one not between", value1, value2, "commonOne");
            return (Criteria) this;
        }

        public Criteria andCommonTwoIsNull() {
            addCriterion("common_two is null");
            return (Criteria) this;
        }

        public Criteria andCommonTwoIsNotNull() {
            addCriterion("common_two is not null");
            return (Criteria) this;
        }

        public Criteria andCommonTwoEqualTo(String value) {
            addCriterion("common_two =", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoNotEqualTo(String value) {
            addCriterion("common_two <>", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoGreaterThan(String value) {
            addCriterion("common_two >", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoGreaterThanOrEqualTo(String value) {
            addCriterion("common_two >=", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoLessThan(String value) {
            addCriterion("common_two <", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoLessThanOrEqualTo(String value) {
            addCriterion("common_two <=", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoLike(String value) {
            addCriterion("common_two like", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoNotLike(String value) {
            addCriterion("common_two not like", value, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoIn(List<String> values) {
            addCriterion("common_two in", values, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoNotIn(List<String> values) {
            addCriterion("common_two not in", values, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoBetween(String value1, String value2) {
            addCriterion("common_two between", value1, value2, "commonTwo");
            return (Criteria) this;
        }

        public Criteria andCommonTwoNotBetween(String value1, String value2) {
            addCriterion("common_two not between", value1, value2, "commonTwo");
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