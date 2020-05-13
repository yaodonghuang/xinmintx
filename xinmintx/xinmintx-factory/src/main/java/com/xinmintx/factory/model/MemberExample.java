package com.xinmintx.factory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberExample() {
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

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
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

        public Criteria andMemberTypeIsNull() {
            addCriterion("member_type is null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIsNotNull() {
            addCriterion("member_type is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTypeEqualTo(Integer value) {
            addCriterion("member_type =", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotEqualTo(Integer value) {
            addCriterion("member_type <>", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThan(Integer value) {
            addCriterion("member_type >", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_type >=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThan(Integer value) {
            addCriterion("member_type <", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeLessThanOrEqualTo(Integer value) {
            addCriterion("member_type <=", value, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeIn(List<Integer> values) {
            addCriterion("member_type in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotIn(List<Integer> values) {
            addCriterion("member_type not in", values, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeBetween(Integer value1, Integer value2) {
            addCriterion("member_type between", value1, value2, "memberType");
            return (Criteria) this;
        }

        public Criteria andMemberTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("member_type not between", value1, value2, "memberType");
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

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
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

        public Criteria andAvatarUrlIsNull() {
            addCriterion("avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNotNull() {
            addCriterion("avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlEqualTo(String value) {
            addCriterion("avatar_url =", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotEqualTo(String value) {
            addCriterion("avatar_url <>", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThan(String value) {
            addCriterion("avatar_url >", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("avatar_url >=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThan(String value) {
            addCriterion("avatar_url <", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("avatar_url <=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLike(String value) {
            addCriterion("avatar_url like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotLike(String value) {
            addCriterion("avatar_url not like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIn(List<String> values) {
            addCriterion("avatar_url in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotIn(List<String> values) {
            addCriterion("avatar_url not in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlBetween(String value1, String value2) {
            addCriterion("avatar_url between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("avatar_url not between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andPlatformCountIsNull() {
            addCriterion("platform_count is null");
            return (Criteria) this;
        }

        public Criteria andPlatformCountIsNotNull() {
            addCriterion("platform_count is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformCountEqualTo(Integer value) {
            addCriterion("platform_count =", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountNotEqualTo(Integer value) {
            addCriterion("platform_count <>", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountGreaterThan(Integer value) {
            addCriterion("platform_count >", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_count >=", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountLessThan(Integer value) {
            addCriterion("platform_count <", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountLessThanOrEqualTo(Integer value) {
            addCriterion("platform_count <=", value, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountIn(List<Integer> values) {
            addCriterion("platform_count in", values, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountNotIn(List<Integer> values) {
            addCriterion("platform_count not in", values, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountBetween(Integer value1, Integer value2) {
            addCriterion("platform_count between", value1, value2, "platformCount");
            return (Criteria) this;
        }

        public Criteria andPlatformCountNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_count not between", value1, value2, "platformCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountIsNull() {
            addCriterion("merchant_count is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCountIsNotNull() {
            addCriterion("merchant_count is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCountEqualTo(Integer value) {
            addCriterion("merchant_count =", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountNotEqualTo(Integer value) {
            addCriterion("merchant_count <>", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountGreaterThan(Integer value) {
            addCriterion("merchant_count >", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_count >=", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountLessThan(Integer value) {
            addCriterion("merchant_count <", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_count <=", value, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountIn(List<Integer> values) {
            addCriterion("merchant_count in", values, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountNotIn(List<Integer> values) {
            addCriterion("merchant_count not in", values, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountBetween(Integer value1, Integer value2) {
            addCriterion("merchant_count between", value1, value2, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andMerchantCountNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_count not between", value1, value2, "merchantCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountIsNull() {
            addCriterion("branch_office_count is null");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountIsNotNull() {
            addCriterion("branch_office_count is not null");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountEqualTo(Integer value) {
            addCriterion("branch_office_count =", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountNotEqualTo(Integer value) {
            addCriterion("branch_office_count <>", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountGreaterThan(Integer value) {
            addCriterion("branch_office_count >", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("branch_office_count >=", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountLessThan(Integer value) {
            addCriterion("branch_office_count <", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountLessThanOrEqualTo(Integer value) {
            addCriterion("branch_office_count <=", value, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountIn(List<Integer> values) {
            addCriterion("branch_office_count in", values, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountNotIn(List<Integer> values) {
            addCriterion("branch_office_count not in", values, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountBetween(Integer value1, Integer value2) {
            addCriterion("branch_office_count between", value1, value2, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBranchOfficeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("branch_office_count not between", value1, value2, "branchOfficeCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountIsNull() {
            addCriterion("birth_gift_count is null");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountIsNotNull() {
            addCriterion("birth_gift_count is not null");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountEqualTo(Integer value) {
            addCriterion("birth_gift_count =", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountNotEqualTo(Integer value) {
            addCriterion("birth_gift_count <>", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountGreaterThan(Integer value) {
            addCriterion("birth_gift_count >", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("birth_gift_count >=", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountLessThan(Integer value) {
            addCriterion("birth_gift_count <", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountLessThanOrEqualTo(Integer value) {
            addCriterion("birth_gift_count <=", value, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountIn(List<Integer> values) {
            addCriterion("birth_gift_count in", values, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountNotIn(List<Integer> values) {
            addCriterion("birth_gift_count not in", values, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountBetween(Integer value1, Integer value2) {
            addCriterion("birth_gift_count between", value1, value2, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andBirthGiftCountNotBetween(Integer value1, Integer value2) {
            addCriterion("birth_gift_count not between", value1, value2, "birthGiftCount");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyIsNull() {
            addCriterion("new_currency is null");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyIsNotNull() {
            addCriterion("new_currency is not null");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyEqualTo(BigDecimal value) {
            addCriterion("new_currency =", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyNotEqualTo(BigDecimal value) {
            addCriterion("new_currency <>", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyGreaterThan(BigDecimal value) {
            addCriterion("new_currency >", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("new_currency >=", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyLessThan(BigDecimal value) {
            addCriterion("new_currency <", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("new_currency <=", value, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyIn(List<BigDecimal> values) {
            addCriterion("new_currency in", values, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyNotIn(List<BigDecimal> values) {
            addCriterion("new_currency not in", values, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_currency between", value1, value2, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewCurrencyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_currency not between", value1, value2, "newCurrency");
            return (Criteria) this;
        }

        public Criteria andNewBeansIsNull() {
            addCriterion("new_beans is null");
            return (Criteria) this;
        }

        public Criteria andNewBeansIsNotNull() {
            addCriterion("new_beans is not null");
            return (Criteria) this;
        }

        public Criteria andNewBeansEqualTo(BigDecimal value) {
            addCriterion("new_beans =", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansNotEqualTo(BigDecimal value) {
            addCriterion("new_beans <>", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansGreaterThan(BigDecimal value) {
            addCriterion("new_beans >", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("new_beans >=", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansLessThan(BigDecimal value) {
            addCriterion("new_beans <", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansLessThanOrEqualTo(BigDecimal value) {
            addCriterion("new_beans <=", value, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansIn(List<BigDecimal> values) {
            addCriterion("new_beans in", values, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansNotIn(List<BigDecimal> values) {
            addCriterion("new_beans not in", values, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_beans between", value1, value2, "newBeans");
            return (Criteria) this;
        }

        public Criteria andNewBeansNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_beans not between", value1, value2, "newBeans");
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

        public Criteria andFreezeBeansIsNull() {
            addCriterion("freeze_beans is null");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansIsNotNull() {
            addCriterion("freeze_beans is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansEqualTo(BigDecimal value) {
            addCriterion("freeze_beans =", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansNotEqualTo(BigDecimal value) {
            addCriterion("freeze_beans <>", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansGreaterThan(BigDecimal value) {
            addCriterion("freeze_beans >", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_beans >=", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansLessThan(BigDecimal value) {
            addCriterion("freeze_beans <", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_beans <=", value, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansIn(List<BigDecimal> values) {
            addCriterion("freeze_beans in", values, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansNotIn(List<BigDecimal> values) {
            addCriterion("freeze_beans not in", values, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_beans between", value1, value2, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeBeansNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_beans not between", value1, value2, "freezeBeans");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyIsNull() {
            addCriterion("freeze_currency is null");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyIsNotNull() {
            addCriterion("freeze_currency is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyEqualTo(BigDecimal value) {
            addCriterion("freeze_currency =", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyNotEqualTo(BigDecimal value) {
            addCriterion("freeze_currency <>", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyGreaterThan(BigDecimal value) {
            addCriterion("freeze_currency >", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_currency >=", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyLessThan(BigDecimal value) {
            addCriterion("freeze_currency <", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_currency <=", value, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyIn(List<BigDecimal> values) {
            addCriterion("freeze_currency in", values, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyNotIn(List<BigDecimal> values) {
            addCriterion("freeze_currency not in", values, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_currency between", value1, value2, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andFreezeCurrencyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_currency not between", value1, value2, "freezeCurrency");
            return (Criteria) this;
        }

        public Criteria andGiftStartIsNull() {
            addCriterion("gift_start is null");
            return (Criteria) this;
        }

        public Criteria andGiftStartIsNotNull() {
            addCriterion("gift_start is not null");
            return (Criteria) this;
        }

        public Criteria andGiftStartEqualTo(Integer value) {
            addCriterion("gift_start =", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartNotEqualTo(Integer value) {
            addCriterion("gift_start <>", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartGreaterThan(Integer value) {
            addCriterion("gift_start >", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_start >=", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartLessThan(Integer value) {
            addCriterion("gift_start <", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartLessThanOrEqualTo(Integer value) {
            addCriterion("gift_start <=", value, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartIn(List<Integer> values) {
            addCriterion("gift_start in", values, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartNotIn(List<Integer> values) {
            addCriterion("gift_start not in", values, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartBetween(Integer value1, Integer value2) {
            addCriterion("gift_start between", value1, value2, "giftStart");
            return (Criteria) this;
        }

        public Criteria andGiftStartNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_start not between", value1, value2, "giftStart");
            return (Criteria) this;
        }

        public Criteria andTreeCodeIsNull() {
            addCriterion("tree_code is null");
            return (Criteria) this;
        }

        public Criteria andTreeCodeIsNotNull() {
            addCriterion("tree_code is not null");
            return (Criteria) this;
        }

        public Criteria andTreeCodeEqualTo(String value) {
            addCriterion("tree_code =", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeNotEqualTo(String value) {
            addCriterion("tree_code <>", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeGreaterThan(String value) {
            addCriterion("tree_code >", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tree_code >=", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeLessThan(String value) {
            addCriterion("tree_code <", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeLessThanOrEqualTo(String value) {
            addCriterion("tree_code <=", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeLike(String value) {
            addCriterion("tree_code like", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeNotLike(String value) {
            addCriterion("tree_code not like", value, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeIn(List<String> values) {
            addCriterion("tree_code in", values, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeNotIn(List<String> values) {
            addCriterion("tree_code not in", values, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeBetween(String value1, String value2) {
            addCriterion("tree_code between", value1, value2, "treeCode");
            return (Criteria) this;
        }

        public Criteria andTreeCodeNotBetween(String value1, String value2) {
            addCriterion("tree_code not between", value1, value2, "treeCode");
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

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(Integer value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(Integer value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(Integer value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(Integer value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(Integer value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<Integer> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<Integer> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(Integer value1, Integer value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNull() {
            addCriterion("card_status is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNotNull() {
            addCriterion("card_status is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusEqualTo(Integer value) {
            addCriterion("card_status =", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotEqualTo(Integer value) {
            addCriterion("card_status <>", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThan(Integer value) {
            addCriterion("card_status >", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_status >=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThan(Integer value) {
            addCriterion("card_status <", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThanOrEqualTo(Integer value) {
            addCriterion("card_status <=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIn(List<Integer> values) {
            addCriterion("card_status in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotIn(List<Integer> values) {
            addCriterion("card_status not in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusBetween(Integer value1, Integer value2) {
            addCriterion("card_status between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("card_status not between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardIndateIsNull() {
            addCriterion("card_indate is null");
            return (Criteria) this;
        }

        public Criteria andCardIndateIsNotNull() {
            addCriterion("card_indate is not null");
            return (Criteria) this;
        }

        public Criteria andCardIndateEqualTo(Date value) {
            addCriterion("card_indate =", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateNotEqualTo(Date value) {
            addCriterion("card_indate <>", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateGreaterThan(Date value) {
            addCriterion("card_indate >", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateGreaterThanOrEqualTo(Date value) {
            addCriterion("card_indate >=", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateLessThan(Date value) {
            addCriterion("card_indate <", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateLessThanOrEqualTo(Date value) {
            addCriterion("card_indate <=", value, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateIn(List<Date> values) {
            addCriterion("card_indate in", values, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateNotIn(List<Date> values) {
            addCriterion("card_indate not in", values, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateBetween(Date value1, Date value2) {
            addCriterion("card_indate between", value1, value2, "cardIndate");
            return (Criteria) this;
        }

        public Criteria andCardIndateNotBetween(Date value1, Date value2) {
            addCriterion("card_indate not between", value1, value2, "cardIndate");
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