package com.xinmintx.agent.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FactoryExample() {
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

        public Criteria andFactoryIdIsNull() {
            addCriterion("factory_id is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNotNull() {
            addCriterion("factory_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdEqualTo(Long value) {
            addCriterion("factory_id =", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotEqualTo(Long value) {
            addCriterion("factory_id <>", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThan(Long value) {
            addCriterion("factory_id >", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("factory_id >=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThan(Long value) {
            addCriterion("factory_id <", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThanOrEqualTo(Long value) {
            addCriterion("factory_id <=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIn(List<Long> values) {
            addCriterion("factory_id in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotIn(List<Long> values) {
            addCriterion("factory_id not in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdBetween(Long value1, Long value2) {
            addCriterion("factory_id between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotBetween(Long value1, Long value2) {
            addCriterion("factory_id not between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeIsNull() {
            addCriterion("factory_code is null");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeIsNotNull() {
            addCriterion("factory_code is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeEqualTo(String value) {
            addCriterion("factory_code =", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeNotEqualTo(String value) {
            addCriterion("factory_code <>", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeGreaterThan(String value) {
            addCriterion("factory_code >", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("factory_code >=", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeLessThan(String value) {
            addCriterion("factory_code <", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeLessThanOrEqualTo(String value) {
            addCriterion("factory_code <=", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeLike(String value) {
            addCriterion("factory_code like", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeNotLike(String value) {
            addCriterion("factory_code not like", value, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeIn(List<String> values) {
            addCriterion("factory_code in", values, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeNotIn(List<String> values) {
            addCriterion("factory_code not in", values, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeBetween(String value1, String value2) {
            addCriterion("factory_code between", value1, value2, "factoryCode");
            return (Criteria) this;
        }

        public Criteria andFactoryCodeNotBetween(String value1, String value2) {
            addCriterion("factory_code not between", value1, value2, "factoryCode");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
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

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
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

        public Criteria andPersonnameIsNull() {
            addCriterion("personname is null");
            return (Criteria) this;
        }

        public Criteria andPersonnameIsNotNull() {
            addCriterion("personname is not null");
            return (Criteria) this;
        }

        public Criteria andPersonnameEqualTo(String value) {
            addCriterion("personname =", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameNotEqualTo(String value) {
            addCriterion("personname <>", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameGreaterThan(String value) {
            addCriterion("personname >", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameGreaterThanOrEqualTo(String value) {
            addCriterion("personname >=", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameLessThan(String value) {
            addCriterion("personname <", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameLessThanOrEqualTo(String value) {
            addCriterion("personname <=", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameLike(String value) {
            addCriterion("personname like", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameNotLike(String value) {
            addCriterion("personname not like", value, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameIn(List<String> values) {
            addCriterion("personname in", values, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameNotIn(List<String> values) {
            addCriterion("personname not in", values, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameBetween(String value1, String value2) {
            addCriterion("personname between", value1, value2, "personname");
            return (Criteria) this;
        }

        public Criteria andPersonnameNotBetween(String value1, String value2) {
            addCriterion("personname not between", value1, value2, "personname");
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

        public Criteria andReferreridIsNull() {
            addCriterion("referrerId is null");
            return (Criteria) this;
        }

        public Criteria andReferreridIsNotNull() {
            addCriterion("referrerId is not null");
            return (Criteria) this;
        }

        public Criteria andReferreridEqualTo(Integer value) {
            addCriterion("referrerId =", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridNotEqualTo(Integer value) {
            addCriterion("referrerId <>", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridGreaterThan(Integer value) {
            addCriterion("referrerId >", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridGreaterThanOrEqualTo(Integer value) {
            addCriterion("referrerId >=", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridLessThan(Integer value) {
            addCriterion("referrerId <", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridLessThanOrEqualTo(Integer value) {
            addCriterion("referrerId <=", value, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridIn(List<Integer> values) {
            addCriterion("referrerId in", values, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridNotIn(List<Integer> values) {
            addCriterion("referrerId not in", values, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridBetween(Integer value1, Integer value2) {
            addCriterion("referrerId between", value1, value2, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReferreridNotBetween(Integer value1, Integer value2) {
            addCriterion("referrerId not between", value1, value2, "referrerid");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoIsNull() {
            addCriterion("reserved_photo is null");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoIsNotNull() {
            addCriterion("reserved_photo is not null");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoEqualTo(String value) {
            addCriterion("reserved_photo =", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoNotEqualTo(String value) {
            addCriterion("reserved_photo <>", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoGreaterThan(String value) {
            addCriterion("reserved_photo >", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("reserved_photo >=", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoLessThan(String value) {
            addCriterion("reserved_photo <", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoLessThanOrEqualTo(String value) {
            addCriterion("reserved_photo <=", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoLike(String value) {
            addCriterion("reserved_photo like", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoNotLike(String value) {
            addCriterion("reserved_photo not like", value, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoIn(List<String> values) {
            addCriterion("reserved_photo in", values, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoNotIn(List<String> values) {
            addCriterion("reserved_photo not in", values, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoBetween(String value1, String value2) {
            addCriterion("reserved_photo between", value1, value2, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andReservedPhotoNotBetween(String value1, String value2) {
            addCriterion("reserved_photo not between", value1, value2, "reservedPhoto");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIsNull() {
            addCriterion("phone_id is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIsNotNull() {
            addCriterion("phone_id is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneIdEqualTo(String value) {
            addCriterion("phone_id =", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotEqualTo(String value) {
            addCriterion("phone_id <>", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdGreaterThan(String value) {
            addCriterion("phone_id >", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdGreaterThanOrEqualTo(String value) {
            addCriterion("phone_id >=", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLessThan(String value) {
            addCriterion("phone_id <", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLessThanOrEqualTo(String value) {
            addCriterion("phone_id <=", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdLike(String value) {
            addCriterion("phone_id like", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotLike(String value) {
            addCriterion("phone_id not like", value, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdIn(List<String> values) {
            addCriterion("phone_id in", values, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotIn(List<String> values) {
            addCriterion("phone_id not in", values, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdBetween(String value1, String value2) {
            addCriterion("phone_id between", value1, value2, "phoneId");
            return (Criteria) this;
        }

        public Criteria andPhoneIdNotBetween(String value1, String value2) {
            addCriterion("phone_id not between", value1, value2, "phoneId");
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

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLocationCodeIsNull() {
            addCriterion("location_code is null");
            return (Criteria) this;
        }

        public Criteria andLocationCodeIsNotNull() {
            addCriterion("location_code is not null");
            return (Criteria) this;
        }

        public Criteria andLocationCodeEqualTo(String value) {
            addCriterion("location_code =", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotEqualTo(String value) {
            addCriterion("location_code <>", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeGreaterThan(String value) {
            addCriterion("location_code >", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("location_code >=", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLessThan(String value) {
            addCriterion("location_code <", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLessThanOrEqualTo(String value) {
            addCriterion("location_code <=", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLike(String value) {
            addCriterion("location_code like", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotLike(String value) {
            addCriterion("location_code not like", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeIn(List<String> values) {
            addCriterion("location_code in", values, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotIn(List<String> values) {
            addCriterion("location_code not in", values, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeBetween(String value1, String value2) {
            addCriterion("location_code between", value1, value2, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotBetween(String value1, String value2) {
            addCriterion("location_code not between", value1, value2, "locationCode");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleIsNull() {
            addCriterion("factory_scale is null");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleIsNotNull() {
            addCriterion("factory_scale is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleEqualTo(String value) {
            addCriterion("factory_scale =", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleNotEqualTo(String value) {
            addCriterion("factory_scale <>", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleGreaterThan(String value) {
            addCriterion("factory_scale >", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleGreaterThanOrEqualTo(String value) {
            addCriterion("factory_scale >=", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleLessThan(String value) {
            addCriterion("factory_scale <", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleLessThanOrEqualTo(String value) {
            addCriterion("factory_scale <=", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleLike(String value) {
            addCriterion("factory_scale like", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleNotLike(String value) {
            addCriterion("factory_scale not like", value, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleIn(List<String> values) {
            addCriterion("factory_scale in", values, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleNotIn(List<String> values) {
            addCriterion("factory_scale not in", values, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleBetween(String value1, String value2) {
            addCriterion("factory_scale between", value1, value2, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryScaleNotBetween(String value1, String value2) {
            addCriterion("factory_scale not between", value1, value2, "factoryScale");
            return (Criteria) this;
        }

        public Criteria andFactoryProductIsNull() {
            addCriterion("factory_product is null");
            return (Criteria) this;
        }

        public Criteria andFactoryProductIsNotNull() {
            addCriterion("factory_product is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryProductEqualTo(String value) {
            addCriterion("factory_product =", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductNotEqualTo(String value) {
            addCriterion("factory_product <>", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductGreaterThan(String value) {
            addCriterion("factory_product >", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductGreaterThanOrEqualTo(String value) {
            addCriterion("factory_product >=", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductLessThan(String value) {
            addCriterion("factory_product <", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductLessThanOrEqualTo(String value) {
            addCriterion("factory_product <=", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductLike(String value) {
            addCriterion("factory_product like", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductNotLike(String value) {
            addCriterion("factory_product not like", value, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductIn(List<String> values) {
            addCriterion("factory_product in", values, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductNotIn(List<String> values) {
            addCriterion("factory_product not in", values, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductBetween(String value1, String value2) {
            addCriterion("factory_product between", value1, value2, "factoryProduct");
            return (Criteria) this;
        }

        public Criteria andFactoryProductNotBetween(String value1, String value2) {
            addCriterion("factory_product not between", value1, value2, "factoryProduct");
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

        public Criteria andWorkPhotoIsNull() {
            addCriterion("work_photo is null");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoIsNotNull() {
            addCriterion("work_photo is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoEqualTo(String value) {
            addCriterion("work_photo =", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoNotEqualTo(String value) {
            addCriterion("work_photo <>", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoGreaterThan(String value) {
            addCriterion("work_photo >", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("work_photo >=", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoLessThan(String value) {
            addCriterion("work_photo <", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoLessThanOrEqualTo(String value) {
            addCriterion("work_photo <=", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoLike(String value) {
            addCriterion("work_photo like", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoNotLike(String value) {
            addCriterion("work_photo not like", value, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoIn(List<String> values) {
            addCriterion("work_photo in", values, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoNotIn(List<String> values) {
            addCriterion("work_photo not in", values, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoBetween(String value1, String value2) {
            addCriterion("work_photo between", value1, value2, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andWorkPhotoNotBetween(String value1, String value2) {
            addCriterion("work_photo not between", value1, value2, "workPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoIsNull() {
            addCriterion("factory_photo is null");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoIsNotNull() {
            addCriterion("factory_photo is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoEqualTo(String value) {
            addCriterion("factory_photo =", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoNotEqualTo(String value) {
            addCriterion("factory_photo <>", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoGreaterThan(String value) {
            addCriterion("factory_photo >", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("factory_photo >=", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoLessThan(String value) {
            addCriterion("factory_photo <", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoLessThanOrEqualTo(String value) {
            addCriterion("factory_photo <=", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoLike(String value) {
            addCriterion("factory_photo like", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoNotLike(String value) {
            addCriterion("factory_photo not like", value, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoIn(List<String> values) {
            addCriterion("factory_photo in", values, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoNotIn(List<String> values) {
            addCriterion("factory_photo not in", values, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoBetween(String value1, String value2) {
            addCriterion("factory_photo between", value1, value2, "factoryPhoto");
            return (Criteria) this;
        }

        public Criteria andFactoryPhotoNotBetween(String value1, String value2) {
            addCriterion("factory_photo not between", value1, value2, "factoryPhoto");
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