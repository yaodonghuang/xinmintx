package com.xinmintx.agent.model;

import java.util.ArrayList;
import java.util.List;

public class ProcurementCommoditiesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcurementCommoditiesExample() {
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

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Integer value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Integer value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Integer value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Integer value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Integer> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Integer> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andSampleNameIsNull() {
            addCriterion("sample_name is null");
            return (Criteria) this;
        }

        public Criteria andSampleNameIsNotNull() {
            addCriterion("sample_name is not null");
            return (Criteria) this;
        }

        public Criteria andSampleNameEqualTo(String value) {
            addCriterion("sample_name =", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameNotEqualTo(String value) {
            addCriterion("sample_name <>", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameGreaterThan(String value) {
            addCriterion("sample_name >", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameGreaterThanOrEqualTo(String value) {
            addCriterion("sample_name >=", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameLessThan(String value) {
            addCriterion("sample_name <", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameLessThanOrEqualTo(String value) {
            addCriterion("sample_name <=", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameLike(String value) {
            addCriterion("sample_name like", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameNotLike(String value) {
            addCriterion("sample_name not like", value, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameIn(List<String> values) {
            addCriterion("sample_name in", values, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameNotIn(List<String> values) {
            addCriterion("sample_name not in", values, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameBetween(String value1, String value2) {
            addCriterion("sample_name between", value1, value2, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleNameNotBetween(String value1, String value2) {
            addCriterion("sample_name not between", value1, value2, "sampleName");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNull() {
            addCriterion("sample_type is null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNotNull() {
            addCriterion("sample_type is not null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeEqualTo(String value) {
            addCriterion("sample_type =", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotEqualTo(String value) {
            addCriterion("sample_type <>", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThan(String value) {
            addCriterion("sample_type >", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sample_type >=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThan(String value) {
            addCriterion("sample_type <", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThanOrEqualTo(String value) {
            addCriterion("sample_type <=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLike(String value) {
            addCriterion("sample_type like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotLike(String value) {
            addCriterion("sample_type not like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIn(List<String> values) {
            addCriterion("sample_type in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotIn(List<String> values) {
            addCriterion("sample_type not in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeBetween(String value1, String value2) {
            addCriterion("sample_type between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotBetween(String value1, String value2) {
            addCriterion("sample_type not between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIsNull() {
            addCriterion("tracking_number is null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIsNotNull() {
            addCriterion("tracking_number is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberEqualTo(String value) {
            addCriterion("tracking_number =", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotEqualTo(String value) {
            addCriterion("tracking_number <>", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberGreaterThan(String value) {
            addCriterion("tracking_number >", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_number >=", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLessThan(String value) {
            addCriterion("tracking_number <", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLessThanOrEqualTo(String value) {
            addCriterion("tracking_number <=", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberLike(String value) {
            addCriterion("tracking_number like", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotLike(String value) {
            addCriterion("tracking_number not like", value, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberIn(List<String> values) {
            addCriterion("tracking_number in", values, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotIn(List<String> values) {
            addCriterion("tracking_number not in", values, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberBetween(String value1, String value2) {
            addCriterion("tracking_number between", value1, value2, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andTrackingNumberNotBetween(String value1, String value2) {
            addCriterion("tracking_number not between", value1, value2, "trackingNumber");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
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

        public Criteria andDetailedAddressIsNull() {
            addCriterion("detailed_address is null");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressIsNotNull() {
            addCriterion("detailed_address is not null");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressEqualTo(String value) {
            addCriterion("detailed_address =", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressNotEqualTo(String value) {
            addCriterion("detailed_address <>", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressGreaterThan(String value) {
            addCriterion("detailed_address >", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressGreaterThanOrEqualTo(String value) {
            addCriterion("detailed_address >=", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressLessThan(String value) {
            addCriterion("detailed_address <", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressLessThanOrEqualTo(String value) {
            addCriterion("detailed_address <=", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressLike(String value) {
            addCriterion("detailed_address like", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressNotLike(String value) {
            addCriterion("detailed_address not like", value, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressIn(List<String> values) {
            addCriterion("detailed_address in", values, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressNotIn(List<String> values) {
            addCriterion("detailed_address not in", values, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressBetween(String value1, String value2) {
            addCriterion("detailed_address between", value1, value2, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andDetailedAddressNotBetween(String value1, String value2) {
            addCriterion("detailed_address not between", value1, value2, "detailedAddress");
            return (Criteria) this;
        }

        public Criteria andMarketValueIsNull() {
            addCriterion("market_value is null");
            return (Criteria) this;
        }

        public Criteria andMarketValueIsNotNull() {
            addCriterion("market_value is not null");
            return (Criteria) this;
        }

        public Criteria andMarketValueEqualTo(Double value) {
            addCriterion("market_value =", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueNotEqualTo(Double value) {
            addCriterion("market_value <>", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueGreaterThan(Double value) {
            addCriterion("market_value >", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueGreaterThanOrEqualTo(Double value) {
            addCriterion("market_value >=", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueLessThan(Double value) {
            addCriterion("market_value <", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueLessThanOrEqualTo(Double value) {
            addCriterion("market_value <=", value, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueIn(List<Double> values) {
            addCriterion("market_value in", values, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueNotIn(List<Double> values) {
            addCriterion("market_value not in", values, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueBetween(Double value1, Double value2) {
            addCriterion("market_value between", value1, value2, "marketValue");
            return (Criteria) this;
        }

        public Criteria andMarketValueNotBetween(Double value1, Double value2) {
            addCriterion("market_value not between", value1, value2, "marketValue");
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

        public Criteria andProcurementPriceEqualTo(Double value) {
            addCriterion("procurement_price =", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotEqualTo(Double value) {
            addCriterion("procurement_price <>", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceGreaterThan(Double value) {
            addCriterion("procurement_price >", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("procurement_price >=", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceLessThan(Double value) {
            addCriterion("procurement_price <", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceLessThanOrEqualTo(Double value) {
            addCriterion("procurement_price <=", value, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceIn(List<Double> values) {
            addCriterion("procurement_price in", values, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotIn(List<Double> values) {
            addCriterion("procurement_price not in", values, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceBetween(Double value1, Double value2) {
            addCriterion("procurement_price between", value1, value2, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andProcurementPriceNotBetween(Double value1, Double value2) {
            addCriterion("procurement_price not between", value1, value2, "procurementPrice");
            return (Criteria) this;
        }

        public Criteria andPictureIdIsNull() {
            addCriterion("picture_id is null");
            return (Criteria) this;
        }

        public Criteria andPictureIdIsNotNull() {
            addCriterion("picture_id is not null");
            return (Criteria) this;
        }

        public Criteria andPictureIdEqualTo(Integer value) {
            addCriterion("picture_id =", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotEqualTo(Integer value) {
            addCriterion("picture_id <>", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThan(Integer value) {
            addCriterion("picture_id >", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("picture_id >=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThan(Integer value) {
            addCriterion("picture_id <", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThanOrEqualTo(Integer value) {
            addCriterion("picture_id <=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdIn(List<Integer> values) {
            addCriterion("picture_id in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotIn(List<Integer> values) {
            addCriterion("picture_id not in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdBetween(Integer value1, Integer value2) {
            addCriterion("picture_id between", value1, value2, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("picture_id not between", value1, value2, "pictureId");
            return (Criteria) this;
        }

        public Criteria andCommononeIsNull() {
            addCriterion("commonone is null");
            return (Criteria) this;
        }

        public Criteria andCommononeIsNotNull() {
            addCriterion("commonone is not null");
            return (Criteria) this;
        }

        public Criteria andCommononeEqualTo(String value) {
            addCriterion("commonone =", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeNotEqualTo(String value) {
            addCriterion("commonone <>", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeGreaterThan(String value) {
            addCriterion("commonone >", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeGreaterThanOrEqualTo(String value) {
            addCriterion("commonone >=", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeLessThan(String value) {
            addCriterion("commonone <", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeLessThanOrEqualTo(String value) {
            addCriterion("commonone <=", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeLike(String value) {
            addCriterion("commonone like", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeNotLike(String value) {
            addCriterion("commonone not like", value, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeIn(List<String> values) {
            addCriterion("commonone in", values, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeNotIn(List<String> values) {
            addCriterion("commonone not in", values, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeBetween(String value1, String value2) {
            addCriterion("commonone between", value1, value2, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommononeNotBetween(String value1, String value2) {
            addCriterion("commonone not between", value1, value2, "commonone");
            return (Criteria) this;
        }

        public Criteria andCommontwoIsNull() {
            addCriterion("commontwo is null");
            return (Criteria) this;
        }

        public Criteria andCommontwoIsNotNull() {
            addCriterion("commontwo is not null");
            return (Criteria) this;
        }

        public Criteria andCommontwoEqualTo(String value) {
            addCriterion("commontwo =", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoNotEqualTo(String value) {
            addCriterion("commontwo <>", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoGreaterThan(String value) {
            addCriterion("commontwo >", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoGreaterThanOrEqualTo(String value) {
            addCriterion("commontwo >=", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoLessThan(String value) {
            addCriterion("commontwo <", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoLessThanOrEqualTo(String value) {
            addCriterion("commontwo <=", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoLike(String value) {
            addCriterion("commontwo like", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoNotLike(String value) {
            addCriterion("commontwo not like", value, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoIn(List<String> values) {
            addCriterion("commontwo in", values, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoNotIn(List<String> values) {
            addCriterion("commontwo not in", values, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoBetween(String value1, String value2) {
            addCriterion("commontwo between", value1, value2, "commontwo");
            return (Criteria) this;
        }

        public Criteria andCommontwoNotBetween(String value1, String value2) {
            addCriterion("commontwo not between", value1, value2, "commontwo");
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