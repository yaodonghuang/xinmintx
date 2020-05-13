package com.xinmintx.system.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andRelateIdIsNull() {
            addCriterion("relate_id is null");
            return (Criteria) this;
        }

        public Criteria andRelateIdIsNotNull() {
            addCriterion("relate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelateIdEqualTo(Integer value) {
            addCriterion("relate_id =", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotEqualTo(Integer value) {
            addCriterion("relate_id <>", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdGreaterThan(Integer value) {
            addCriterion("relate_id >", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relate_id >=", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdLessThan(Integer value) {
            addCriterion("relate_id <", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdLessThanOrEqualTo(Integer value) {
            addCriterion("relate_id <=", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdIn(List<Integer> values) {
            addCriterion("relate_id in", values, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotIn(List<Integer> values) {
            addCriterion("relate_id not in", values, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdBetween(Integer value1, Integer value2) {
            addCriterion("relate_id between", value1, value2, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relate_id not between", value1, value2, "relateId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andChoicenessIsNull() {
            addCriterion("choiceness is null");
            return (Criteria) this;
        }

        public Criteria andChoicenessIsNotNull() {
            addCriterion("choiceness is not null");
            return (Criteria) this;
        }

        public Criteria andChoicenessEqualTo(Integer value) {
            addCriterion("choiceness =", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessNotEqualTo(Integer value) {
            addCriterion("choiceness <>", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessGreaterThan(Integer value) {
            addCriterion("choiceness >", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessGreaterThanOrEqualTo(Integer value) {
            addCriterion("choiceness >=", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessLessThan(Integer value) {
            addCriterion("choiceness <", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessLessThanOrEqualTo(Integer value) {
            addCriterion("choiceness <=", value, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessIn(List<Integer> values) {
            addCriterion("choiceness in", values, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessNotIn(List<Integer> values) {
            addCriterion("choiceness not in", values, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessBetween(Integer value1, Integer value2) {
            addCriterion("choiceness between", value1, value2, "choiceness");
            return (Criteria) this;
        }

        public Criteria andChoicenessNotBetween(Integer value1, Integer value2) {
            addCriterion("choiceness not between", value1, value2, "choiceness");
            return (Criteria) this;
        }

        public Criteria andHotSaleIsNull() {
            addCriterion("hot_sale is null");
            return (Criteria) this;
        }

        public Criteria andHotSaleIsNotNull() {
            addCriterion("hot_sale is not null");
            return (Criteria) this;
        }

        public Criteria andHotSaleEqualTo(Integer value) {
            addCriterion("hot_sale =", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleNotEqualTo(Integer value) {
            addCriterion("hot_sale <>", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleGreaterThan(Integer value) {
            addCriterion("hot_sale >", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_sale >=", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleLessThan(Integer value) {
            addCriterion("hot_sale <", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleLessThanOrEqualTo(Integer value) {
            addCriterion("hot_sale <=", value, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleIn(List<Integer> values) {
            addCriterion("hot_sale in", values, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleNotIn(List<Integer> values) {
            addCriterion("hot_sale not in", values, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleBetween(Integer value1, Integer value2) {
            addCriterion("hot_sale between", value1, value2, "hotSale");
            return (Criteria) this;
        }

        public Criteria andHotSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_sale not between", value1, value2, "hotSale");
            return (Criteria) this;
        }

        public Criteria andPreorderIsNull() {
            addCriterion("preorder is null");
            return (Criteria) this;
        }

        public Criteria andPreorderIsNotNull() {
            addCriterion("preorder is not null");
            return (Criteria) this;
        }

        public Criteria andPreorderEqualTo(Integer value) {
            addCriterion("preorder =", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderNotEqualTo(Integer value) {
            addCriterion("preorder <>", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderGreaterThan(Integer value) {
            addCriterion("preorder >", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("preorder >=", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderLessThan(Integer value) {
            addCriterion("preorder <", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderLessThanOrEqualTo(Integer value) {
            addCriterion("preorder <=", value, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderIn(List<Integer> values) {
            addCriterion("preorder in", values, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderNotIn(List<Integer> values) {
            addCriterion("preorder not in", values, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderBetween(Integer value1, Integer value2) {
            addCriterion("preorder between", value1, value2, "preorder");
            return (Criteria) this;
        }

        public Criteria andPreorderNotBetween(Integer value1, Integer value2) {
            addCriterion("preorder not between", value1, value2, "preorder");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameIsNull() {
            addCriterion("goods_list_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameIsNotNull() {
            addCriterion("goods_list_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameEqualTo(String value) {
            addCriterion("goods_list_name =", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameNotEqualTo(String value) {
            addCriterion("goods_list_name <>", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameGreaterThan(String value) {
            addCriterion("goods_list_name >", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_list_name >=", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameLessThan(String value) {
            addCriterion("goods_list_name <", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameLessThanOrEqualTo(String value) {
            addCriterion("goods_list_name <=", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameLike(String value) {
            addCriterion("goods_list_name like", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameNotLike(String value) {
            addCriterion("goods_list_name not like", value, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameIn(List<String> values) {
            addCriterion("goods_list_name in", values, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameNotIn(List<String> values) {
            addCriterion("goods_list_name not in", values, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameBetween(String value1, String value2) {
            addCriterion("goods_list_name between", value1, value2, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsListNameNotBetween(String value1, String value2) {
            addCriterion("goods_list_name not between", value1, value2, "goodsListName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
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

        public Criteria andTurnsPhotoIsNull() {
            addCriterion("turns_photo is null");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoIsNotNull() {
            addCriterion("turns_photo is not null");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoEqualTo(String value) {
            addCriterion("turns_photo =", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoNotEqualTo(String value) {
            addCriterion("turns_photo <>", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoGreaterThan(String value) {
            addCriterion("turns_photo >", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("turns_photo >=", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoLessThan(String value) {
            addCriterion("turns_photo <", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoLessThanOrEqualTo(String value) {
            addCriterion("turns_photo <=", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoLike(String value) {
            addCriterion("turns_photo like", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoNotLike(String value) {
            addCriterion("turns_photo not like", value, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoIn(List<String> values) {
            addCriterion("turns_photo in", values, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoNotIn(List<String> values) {
            addCriterion("turns_photo not in", values, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoBetween(String value1, String value2) {
            addCriterion("turns_photo between", value1, value2, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andTurnsPhotoNotBetween(String value1, String value2) {
            addCriterion("turns_photo not between", value1, value2, "turnsPhoto");
            return (Criteria) this;
        }

        public Criteria andSpeTypeIsNull() {
            addCriterion("spe_type is null");
            return (Criteria) this;
        }

        public Criteria andSpeTypeIsNotNull() {
            addCriterion("spe_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpeTypeEqualTo(Integer value) {
            addCriterion("spe_type =", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeNotEqualTo(Integer value) {
            addCriterion("spe_type <>", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeGreaterThan(Integer value) {
            addCriterion("spe_type >", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("spe_type >=", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeLessThan(Integer value) {
            addCriterion("spe_type <", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("spe_type <=", value, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeIn(List<Integer> values) {
            addCriterion("spe_type in", values, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeNotIn(List<Integer> values) {
            addCriterion("spe_type not in", values, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeBetween(Integer value1, Integer value2) {
            addCriterion("spe_type between", value1, value2, "speType");
            return (Criteria) this;
        }

        public Criteria andSpeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("spe_type not between", value1, value2, "speType");
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

        public Criteria andAgencyPriceIsNull() {
            addCriterion("agency_price is null");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceIsNotNull() {
            addCriterion("agency_price is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceEqualTo(BigDecimal value) {
            addCriterion("agency_price =", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceNotEqualTo(BigDecimal value) {
            addCriterion("agency_price <>", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceGreaterThan(BigDecimal value) {
            addCriterion("agency_price >", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_price >=", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceLessThan(BigDecimal value) {
            addCriterion("agency_price <", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agency_price <=", value, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceIn(List<BigDecimal> values) {
            addCriterion("agency_price in", values, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceNotIn(List<BigDecimal> values) {
            addCriterion("agency_price not in", values, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_price between", value1, value2, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andAgencyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agency_price not between", value1, value2, "agencyPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceIsNull() {
            addCriterion("bazaar_price is null");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceIsNotNull() {
            addCriterion("bazaar_price is not null");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceEqualTo(BigDecimal value) {
            addCriterion("bazaar_price =", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceNotEqualTo(BigDecimal value) {
            addCriterion("bazaar_price <>", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceGreaterThan(BigDecimal value) {
            addCriterion("bazaar_price >", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bazaar_price >=", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceLessThan(BigDecimal value) {
            addCriterion("bazaar_price <", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bazaar_price <=", value, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceIn(List<BigDecimal> values) {
            addCriterion("bazaar_price in", values, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceNotIn(List<BigDecimal> values) {
            addCriterion("bazaar_price not in", values, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bazaar_price between", value1, value2, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andBazaarPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bazaar_price not between", value1, value2, "bazaarPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceIsNull() {
            addCriterion("procurement_price is null");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceIsNotNull() {
            addCriterion("procurement_price is not null");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceEqualTo(BigDecimal value) {
            addCriterion("procurement_price =", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotEqualTo(BigDecimal value) {
            addCriterion("procurement_price <>", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceGreaterThan(BigDecimal value) {
            addCriterion("procurement_price >", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("procurement_price >=", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceLessThan(BigDecimal value) {
            addCriterion("procurement_price <", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("procurement_price <=", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceIn(List<BigDecimal> values) {
            addCriterion("procurement_price in", values, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotIn(List<BigDecimal> values) {
            addCriterion("procurement_price not in", values, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("procurement_price between", value1, value2, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("procurement_price not between", value1, value2, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceIsNull() {
            addCriterion("line_price is null");
            return (Criteria) this;
        }

        public Criteria andLinePriceIsNotNull() {
            addCriterion("line_price is not null");
            return (Criteria) this;
        }

        public Criteria andLinePriceEqualTo(BigDecimal value) {
            addCriterion("line_price =", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceNotEqualTo(BigDecimal value) {
            addCriterion("line_price <>", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceGreaterThan(BigDecimal value) {
            addCriterion("line_price >", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("line_price >=", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceLessThan(BigDecimal value) {
            addCriterion("line_price <", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("line_price <=", value, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceIn(List<BigDecimal> values) {
            addCriterion("line_price in", values, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceNotIn(List<BigDecimal> values) {
            addCriterion("line_price not in", values, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("line_price between", value1, value2, "linePrice");
            return (Criteria) this;
        }

        public Criteria andLinePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("line_price not between", value1, value2, "linePrice");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNull() {
            addCriterion("stock_num is null");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNotNull() {
            addCriterion("stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andStockNumEqualTo(Integer value) {
            addCriterion("stock_num =", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotEqualTo(Integer value) {
            addCriterion("stock_num <>", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThan(Integer value) {
            addCriterion("stock_num >", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_num >=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThan(Integer value) {
            addCriterion("stock_num <", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("stock_num <=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumIn(List<Integer> values) {
            addCriterion("stock_num in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotIn(List<Integer> values) {
            addCriterion("stock_num not in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumBetween(Integer value1, Integer value2) {
            addCriterion("stock_num between", value1, value2, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_num not between", value1, value2, "stockNum");
            return (Criteria) this;
        }

        public Criteria andSalesInitialIsNull() {
            addCriterion("sales_initial is null");
            return (Criteria) this;
        }

        public Criteria andSalesInitialIsNotNull() {
            addCriterion("sales_initial is not null");
            return (Criteria) this;
        }

        public Criteria andSalesInitialEqualTo(Integer value) {
            addCriterion("sales_initial =", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialNotEqualTo(Integer value) {
            addCriterion("sales_initial <>", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialGreaterThan(Integer value) {
            addCriterion("sales_initial >", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_initial >=", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialLessThan(Integer value) {
            addCriterion("sales_initial <", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialLessThanOrEqualTo(Integer value) {
            addCriterion("sales_initial <=", value, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialIn(List<Integer> values) {
            addCriterion("sales_initial in", values, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialNotIn(List<Integer> values) {
            addCriterion("sales_initial not in", values, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialBetween(Integer value1, Integer value2) {
            addCriterion("sales_initial between", value1, value2, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andSalesInitialNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_initial not between", value1, value2, "salesInitial");
            return (Criteria) this;
        }

        public Criteria andActivityTitleIsNull() {
            addCriterion("activity_title is null");
            return (Criteria) this;
        }

        public Criteria andActivityTitleIsNotNull() {
            addCriterion("activity_title is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTitleEqualTo(String value) {
            addCriterion("activity_title =", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotEqualTo(String value) {
            addCriterion("activity_title <>", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleGreaterThan(String value) {
            addCriterion("activity_title >", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleGreaterThanOrEqualTo(String value) {
            addCriterion("activity_title >=", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLessThan(String value) {
            addCriterion("activity_title <", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLessThanOrEqualTo(String value) {
            addCriterion("activity_title <=", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLike(String value) {
            addCriterion("activity_title like", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotLike(String value) {
            addCriterion("activity_title not like", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleIn(List<String> values) {
            addCriterion("activity_title in", values, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotIn(List<String> values) {
            addCriterion("activity_title not in", values, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleBetween(String value1, String value2) {
            addCriterion("activity_title between", value1, value2, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotBetween(String value1, String value2) {
            addCriterion("activity_title not between", value1, value2, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andSalesActualIsNull() {
            addCriterion("sales_actual is null");
            return (Criteria) this;
        }

        public Criteria andSalesActualIsNotNull() {
            addCriterion("sales_actual is not null");
            return (Criteria) this;
        }

        public Criteria andSalesActualEqualTo(Integer value) {
            addCriterion("sales_actual =", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualNotEqualTo(Integer value) {
            addCriterion("sales_actual <>", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualGreaterThan(Integer value) {
            addCriterion("sales_actual >", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_actual >=", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualLessThan(Integer value) {
            addCriterion("sales_actual <", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualLessThanOrEqualTo(Integer value) {
            addCriterion("sales_actual <=", value, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualIn(List<Integer> values) {
            addCriterion("sales_actual in", values, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualNotIn(List<Integer> values) {
            addCriterion("sales_actual not in", values, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualBetween(Integer value1, Integer value2) {
            addCriterion("sales_actual between", value1, value2, "salesActual");
            return (Criteria) this;
        }

        public Criteria andSalesActualNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_actual not between", value1, value2, "salesActual");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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

        public Criteria andOnePriceIsNull() {
            addCriterion("one_price is null");
            return (Criteria) this;
        }

        public Criteria andOnePriceIsNotNull() {
            addCriterion("one_price is not null");
            return (Criteria) this;
        }

        public Criteria andOnePriceEqualTo(Long value) {
            addCriterion("one_price =", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceNotEqualTo(Long value) {
            addCriterion("one_price <>", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceGreaterThan(Long value) {
            addCriterion("one_price >", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("one_price >=", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceLessThan(Long value) {
            addCriterion("one_price <", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceLessThanOrEqualTo(Long value) {
            addCriterion("one_price <=", value, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceIn(List<Long> values) {
            addCriterion("one_price in", values, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceNotIn(List<Long> values) {
            addCriterion("one_price not in", values, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceBetween(Long value1, Long value2) {
            addCriterion("one_price between", value1, value2, "onePrice");
            return (Criteria) this;
        }

        public Criteria andOnePriceNotBetween(Long value1, Long value2) {
            addCriterion("one_price not between", value1, value2, "onePrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceIsNull() {
            addCriterion("two_price is null");
            return (Criteria) this;
        }

        public Criteria andTwoPriceIsNotNull() {
            addCriterion("two_price is not null");
            return (Criteria) this;
        }

        public Criteria andTwoPriceEqualTo(Long value) {
            addCriterion("two_price =", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceNotEqualTo(Long value) {
            addCriterion("two_price <>", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceGreaterThan(Long value) {
            addCriterion("two_price >", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("two_price >=", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceLessThan(Long value) {
            addCriterion("two_price <", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceLessThanOrEqualTo(Long value) {
            addCriterion("two_price <=", value, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceIn(List<Long> values) {
            addCriterion("two_price in", values, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceNotIn(List<Long> values) {
            addCriterion("two_price not in", values, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceBetween(Long value1, Long value2) {
            addCriterion("two_price between", value1, value2, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andTwoPriceNotBetween(Long value1, Long value2) {
            addCriterion("two_price not between", value1, value2, "twoPrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceIsNull() {
            addCriterion("three_price is null");
            return (Criteria) this;
        }

        public Criteria andThreePriceIsNotNull() {
            addCriterion("three_price is not null");
            return (Criteria) this;
        }

        public Criteria andThreePriceEqualTo(Long value) {
            addCriterion("three_price =", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceNotEqualTo(Long value) {
            addCriterion("three_price <>", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceGreaterThan(Long value) {
            addCriterion("three_price >", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("three_price >=", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceLessThan(Long value) {
            addCriterion("three_price <", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceLessThanOrEqualTo(Long value) {
            addCriterion("three_price <=", value, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceIn(List<Long> values) {
            addCriterion("three_price in", values, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceNotIn(List<Long> values) {
            addCriterion("three_price not in", values, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceBetween(Long value1, Long value2) {
            addCriterion("three_price between", value1, value2, "threePrice");
            return (Criteria) this;
        }

        public Criteria andThreePriceNotBetween(Long value1, Long value2) {
            addCriterion("three_price not between", value1, value2, "threePrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceIsNull() {
            addCriterion("four_price is null");
            return (Criteria) this;
        }

        public Criteria andFourPriceIsNotNull() {
            addCriterion("four_price is not null");
            return (Criteria) this;
        }

        public Criteria andFourPriceEqualTo(Long value) {
            addCriterion("four_price =", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceNotEqualTo(Long value) {
            addCriterion("four_price <>", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceGreaterThan(Long value) {
            addCriterion("four_price >", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("four_price >=", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceLessThan(Long value) {
            addCriterion("four_price <", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceLessThanOrEqualTo(Long value) {
            addCriterion("four_price <=", value, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceIn(List<Long> values) {
            addCriterion("four_price in", values, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceNotIn(List<Long> values) {
            addCriterion("four_price not in", values, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceBetween(Long value1, Long value2) {
            addCriterion("four_price between", value1, value2, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFourPriceNotBetween(Long value1, Long value2) {
            addCriterion("four_price not between", value1, value2, "fourPrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceIsNull() {
            addCriterion("five_price is null");
            return (Criteria) this;
        }

        public Criteria andFivePriceIsNotNull() {
            addCriterion("five_price is not null");
            return (Criteria) this;
        }

        public Criteria andFivePriceEqualTo(Long value) {
            addCriterion("five_price =", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceNotEqualTo(Long value) {
            addCriterion("five_price <>", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceGreaterThan(Long value) {
            addCriterion("five_price >", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("five_price >=", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceLessThan(Long value) {
            addCriterion("five_price <", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceLessThanOrEqualTo(Long value) {
            addCriterion("five_price <=", value, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceIn(List<Long> values) {
            addCriterion("five_price in", values, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceNotIn(List<Long> values) {
            addCriterion("five_price not in", values, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceBetween(Long value1, Long value2) {
            addCriterion("five_price between", value1, value2, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andFivePriceNotBetween(Long value1, Long value2) {
            addCriterion("five_price not between", value1, value2, "fivePrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceIsNull() {
            addCriterion("six_price is null");
            return (Criteria) this;
        }

        public Criteria andSixPriceIsNotNull() {
            addCriterion("six_price is not null");
            return (Criteria) this;
        }

        public Criteria andSixPriceEqualTo(Long value) {
            addCriterion("six_price =", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceNotEqualTo(Long value) {
            addCriterion("six_price <>", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceGreaterThan(Long value) {
            addCriterion("six_price >", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("six_price >=", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceLessThan(Long value) {
            addCriterion("six_price <", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceLessThanOrEqualTo(Long value) {
            addCriterion("six_price <=", value, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceIn(List<Long> values) {
            addCriterion("six_price in", values, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceNotIn(List<Long> values) {
            addCriterion("six_price not in", values, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceBetween(Long value1, Long value2) {
            addCriterion("six_price between", value1, value2, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSixPriceNotBetween(Long value1, Long value2) {
            addCriterion("six_price not between", value1, value2, "sixPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceIsNull() {
            addCriterion("seven_price is null");
            return (Criteria) this;
        }

        public Criteria andSevenPriceIsNotNull() {
            addCriterion("seven_price is not null");
            return (Criteria) this;
        }

        public Criteria andSevenPriceEqualTo(Long value) {
            addCriterion("seven_price =", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceNotEqualTo(Long value) {
            addCriterion("seven_price <>", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceGreaterThan(Long value) {
            addCriterion("seven_price >", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("seven_price >=", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceLessThan(Long value) {
            addCriterion("seven_price <", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceLessThanOrEqualTo(Long value) {
            addCriterion("seven_price <=", value, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceIn(List<Long> values) {
            addCriterion("seven_price in", values, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceNotIn(List<Long> values) {
            addCriterion("seven_price not in", values, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceBetween(Long value1, Long value2) {
            addCriterion("seven_price between", value1, value2, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andSevenPriceNotBetween(Long value1, Long value2) {
            addCriterion("seven_price not between", value1, value2, "sevenPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceIsNull() {
            addCriterion("eight_price is null");
            return (Criteria) this;
        }

        public Criteria andEightPriceIsNotNull() {
            addCriterion("eight_price is not null");
            return (Criteria) this;
        }

        public Criteria andEightPriceEqualTo(Long value) {
            addCriterion("eight_price =", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceNotEqualTo(Long value) {
            addCriterion("eight_price <>", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceGreaterThan(Long value) {
            addCriterion("eight_price >", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("eight_price >=", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceLessThan(Long value) {
            addCriterion("eight_price <", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceLessThanOrEqualTo(Long value) {
            addCriterion("eight_price <=", value, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceIn(List<Long> values) {
            addCriterion("eight_price in", values, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceNotIn(List<Long> values) {
            addCriterion("eight_price not in", values, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceBetween(Long value1, Long value2) {
            addCriterion("eight_price between", value1, value2, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andEightPriceNotBetween(Long value1, Long value2) {
            addCriterion("eight_price not between", value1, value2, "eightPrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceIsNull() {
            addCriterion("nine_price is null");
            return (Criteria) this;
        }

        public Criteria andNinePriceIsNotNull() {
            addCriterion("nine_price is not null");
            return (Criteria) this;
        }

        public Criteria andNinePriceEqualTo(Long value) {
            addCriterion("nine_price =", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceNotEqualTo(Long value) {
            addCriterion("nine_price <>", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceGreaterThan(Long value) {
            addCriterion("nine_price >", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("nine_price >=", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceLessThan(Long value) {
            addCriterion("nine_price <", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceLessThanOrEqualTo(Long value) {
            addCriterion("nine_price <=", value, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceIn(List<Long> values) {
            addCriterion("nine_price in", values, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceNotIn(List<Long> values) {
            addCriterion("nine_price not in", values, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceBetween(Long value1, Long value2) {
            addCriterion("nine_price between", value1, value2, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andNinePriceNotBetween(Long value1, Long value2) {
            addCriterion("nine_price not between", value1, value2, "ninePrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceIsNull() {
            addCriterion("ten_price is null");
            return (Criteria) this;
        }

        public Criteria andTenPriceIsNotNull() {
            addCriterion("ten_price is not null");
            return (Criteria) this;
        }

        public Criteria andTenPriceEqualTo(Long value) {
            addCriterion("ten_price =", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceNotEqualTo(Long value) {
            addCriterion("ten_price <>", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceGreaterThan(Long value) {
            addCriterion("ten_price >", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("ten_price >=", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceLessThan(Long value) {
            addCriterion("ten_price <", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceLessThanOrEqualTo(Long value) {
            addCriterion("ten_price <=", value, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceIn(List<Long> values) {
            addCriterion("ten_price in", values, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceNotIn(List<Long> values) {
            addCriterion("ten_price not in", values, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceBetween(Long value1, Long value2) {
            addCriterion("ten_price between", value1, value2, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andTenPriceNotBetween(Long value1, Long value2) {
            addCriterion("ten_price not between", value1, value2, "tenPrice");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(String value) {
            addCriterion("parameter =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(String value) {
            addCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(String value) {
            addCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(String value) {
            addCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(String value) {
            addCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(String value) {
            addCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLike(String value) {
            addCriterion("parameter like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotLike(String value) {
            addCriterion("parameter not like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<String> values) {
            addCriterion("parameter in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<String> values) {
            addCriterion("parameter not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(String value1, String value2) {
            addCriterion("parameter between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(String value1, String value2) {
            addCriterion("parameter not between", value1, value2, "parameter");
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