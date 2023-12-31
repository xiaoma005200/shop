package com.xiaoma.pojo;

import java.util.ArrayList;
import java.util.List;

public class SkuSaleAttrValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkuSaleAttrValueExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("sku_id =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("sku_id <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("sku_id >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sku_id >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("sku_id <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("sku_id <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("sku_id in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("sku_id not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("sku_id between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("sku_id not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdIsNull() {
            addCriterion("sale_attr_id is null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdIsNotNull() {
            addCriterion("sale_attr_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdEqualTo(Long value) {
            addCriterion("sale_attr_id =", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdNotEqualTo(Long value) {
            addCriterion("sale_attr_id <>", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdGreaterThan(Long value) {
            addCriterion("sale_attr_id >", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_attr_id >=", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdLessThan(Long value) {
            addCriterion("sale_attr_id <", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdLessThanOrEqualTo(Long value) {
            addCriterion("sale_attr_id <=", value, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdIn(List<Long> values) {
            addCriterion("sale_attr_id in", values, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdNotIn(List<Long> values) {
            addCriterion("sale_attr_id not in", values, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdBetween(Long value1, Long value2) {
            addCriterion("sale_attr_id between", value1, value2, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrIdNotBetween(Long value1, Long value2) {
            addCriterion("sale_attr_id not between", value1, value2, "saleAttrId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameIsNull() {
            addCriterion("sale_attr_name is null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameIsNotNull() {
            addCriterion("sale_attr_name is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameEqualTo(String value) {
            addCriterion("sale_attr_name =", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameNotEqualTo(String value) {
            addCriterion("sale_attr_name <>", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameGreaterThan(String value) {
            addCriterion("sale_attr_name >", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("sale_attr_name >=", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameLessThan(String value) {
            addCriterion("sale_attr_name <", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameLessThanOrEqualTo(String value) {
            addCriterion("sale_attr_name <=", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameLike(String value) {
            addCriterion("sale_attr_name like", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameNotLike(String value) {
            addCriterion("sale_attr_name not like", value, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameIn(List<String> values) {
            addCriterion("sale_attr_name in", values, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameNotIn(List<String> values) {
            addCriterion("sale_attr_name not in", values, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameBetween(String value1, String value2) {
            addCriterion("sale_attr_name between", value1, value2, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrNameNotBetween(String value1, String value2) {
            addCriterion("sale_attr_name not between", value1, value2, "saleAttrName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdIsNull() {
            addCriterion("sale_attr_value_id is null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdIsNotNull() {
            addCriterion("sale_attr_value_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdEqualTo(Long value) {
            addCriterion("sale_attr_value_id =", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdNotEqualTo(Long value) {
            addCriterion("sale_attr_value_id <>", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdGreaterThan(Long value) {
            addCriterion("sale_attr_value_id >", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_attr_value_id >=", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdLessThan(Long value) {
            addCriterion("sale_attr_value_id <", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdLessThanOrEqualTo(Long value) {
            addCriterion("sale_attr_value_id <=", value, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdIn(List<Long> values) {
            addCriterion("sale_attr_value_id in", values, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdNotIn(List<Long> values) {
            addCriterion("sale_attr_value_id not in", values, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdBetween(Long value1, Long value2) {
            addCriterion("sale_attr_value_id between", value1, value2, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueIdNotBetween(Long value1, Long value2) {
            addCriterion("sale_attr_value_id not between", value1, value2, "saleAttrValueId");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameIsNull() {
            addCriterion("sale_attr_value_name is null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameIsNotNull() {
            addCriterion("sale_attr_value_name is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameEqualTo(String value) {
            addCriterion("sale_attr_value_name =", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameNotEqualTo(String value) {
            addCriterion("sale_attr_value_name <>", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameGreaterThan(String value) {
            addCriterion("sale_attr_value_name >", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameGreaterThanOrEqualTo(String value) {
            addCriterion("sale_attr_value_name >=", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameLessThan(String value) {
            addCriterion("sale_attr_value_name <", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameLessThanOrEqualTo(String value) {
            addCriterion("sale_attr_value_name <=", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameLike(String value) {
            addCriterion("sale_attr_value_name like", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameNotLike(String value) {
            addCriterion("sale_attr_value_name not like", value, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameIn(List<String> values) {
            addCriterion("sale_attr_value_name in", values, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameNotIn(List<String> values) {
            addCriterion("sale_attr_value_name not in", values, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameBetween(String value1, String value2) {
            addCriterion("sale_attr_value_name between", value1, value2, "saleAttrValueName");
            return (Criteria) this;
        }

        public Criteria andSaleAttrValueNameNotBetween(String value1, String value2) {
            addCriterion("sale_attr_value_name not between", value1, value2, "saleAttrValueName");
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