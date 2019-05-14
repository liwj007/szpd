package com.liwj.szpd.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectFinanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectFinanceExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRevisionIsNull() {
            addCriterion("REVISION is null");
            return (Criteria) this;
        }

        public Criteria andRevisionIsNotNull() {
            addCriterion("REVISION is not null");
            return (Criteria) this;
        }

        public Criteria andRevisionEqualTo(Integer value) {
            addCriterion("REVISION =", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotEqualTo(Integer value) {
            addCriterion("REVISION <>", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionGreaterThan(Integer value) {
            addCriterion("REVISION >", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionGreaterThanOrEqualTo(Integer value) {
            addCriterion("REVISION >=", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionLessThan(Integer value) {
            addCriterion("REVISION <", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionLessThanOrEqualTo(Integer value) {
            addCriterion("REVISION <=", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionIn(List<Integer> values) {
            addCriterion("REVISION in", values, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotIn(List<Integer> values) {
            addCriterion("REVISION not in", values, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionBetween(Integer value1, Integer value2) {
            addCriterion("REVISION between", value1, value2, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotBetween(Integer value1, Integer value2) {
            addCriterion("REVISION not between", value1, value2, "revision");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(Integer value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(Integer value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(Integer value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(Integer value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(Integer value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<Integer> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<Integer> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(Integer value1, Integer value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNull() {
            addCriterion("UPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("UPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(Integer value) {
            addCriterion("UPDATED_BY =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(Integer value) {
            addCriterion("UPDATED_BY <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(Integer value) {
            addCriterion("UPDATED_BY >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("UPDATED_BY >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(Integer value) {
            addCriterion("UPDATED_BY <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(Integer value) {
            addCriterion("UPDATED_BY <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<Integer> values) {
            addCriterion("UPDATED_BY in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<Integer> values) {
            addCriterion("UPDATED_BY not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(Integer value1, Integer value2) {
            addCriterion("UPDATED_BY between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("UPDATED_BY not between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("UPDATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("UPDATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("UPDATED_TIME =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("UPDATED_TIME <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("UPDATED_TIME >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATED_TIME >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("UPDATED_TIME <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATED_TIME <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("UPDATED_TIME in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("UPDATED_TIME not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATED_TIME between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATED_TIME not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceIsNull() {
            addCriterion("INVOICE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIsNotNull() {
            addCriterion("INVOICE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEqualTo(BigDecimal value) {
            addCriterion("INVOICE =", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotEqualTo(BigDecimal value) {
            addCriterion("INVOICE <>", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceGreaterThan(BigDecimal value) {
            addCriterion("INVOICE >", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INVOICE >=", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceLessThan(BigDecimal value) {
            addCriterion("INVOICE <", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INVOICE <=", value, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceIn(List<BigDecimal> values) {
            addCriterion("INVOICE in", values, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotIn(List<BigDecimal> values) {
            addCriterion("INVOICE not in", values, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INVOICE between", value1, value2, "invoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INVOICE not between", value1, value2, "invoice");
            return (Criteria) this;
        }

        public Criteria andArrivalIsNull() {
            addCriterion("ARRIVAL is null");
            return (Criteria) this;
        }

        public Criteria andArrivalIsNotNull() {
            addCriterion("ARRIVAL is not null");
            return (Criteria) this;
        }

        public Criteria andArrivalEqualTo(BigDecimal value) {
            addCriterion("ARRIVAL =", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalNotEqualTo(BigDecimal value) {
            addCriterion("ARRIVAL <>", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalGreaterThan(BigDecimal value) {
            addCriterion("ARRIVAL >", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ARRIVAL >=", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalLessThan(BigDecimal value) {
            addCriterion("ARRIVAL <", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ARRIVAL <=", value, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalIn(List<BigDecimal> values) {
            addCriterion("ARRIVAL in", values, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalNotIn(List<BigDecimal> values) {
            addCriterion("ARRIVAL not in", values, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ARRIVAL between", value1, value2, "arrival");
            return (Criteria) this;
        }

        public Criteria andArrivalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ARRIVAL not between", value1, value2, "arrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalIsNull() {
            addCriterion("NO_ARRIVAL is null");
            return (Criteria) this;
        }

        public Criteria andNoArrivalIsNotNull() {
            addCriterion("NO_ARRIVAL is not null");
            return (Criteria) this;
        }

        public Criteria andNoArrivalEqualTo(BigDecimal value) {
            addCriterion("NO_ARRIVAL =", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalNotEqualTo(BigDecimal value) {
            addCriterion("NO_ARRIVAL <>", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalGreaterThan(BigDecimal value) {
            addCriterion("NO_ARRIVAL >", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NO_ARRIVAL >=", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalLessThan(BigDecimal value) {
            addCriterion("NO_ARRIVAL <", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NO_ARRIVAL <=", value, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalIn(List<BigDecimal> values) {
            addCriterion("NO_ARRIVAL in", values, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalNotIn(List<BigDecimal> values) {
            addCriterion("NO_ARRIVAL not in", values, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NO_ARRIVAL between", value1, value2, "noArrival");
            return (Criteria) this;
        }

        public Criteria andNoArrivalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NO_ARRIVAL not between", value1, value2, "noArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalIsNull() {
            addCriterion("STEP_ARRIVAl is null");
            return (Criteria) this;
        }

        public Criteria andStepArrivalIsNotNull() {
            addCriterion("STEP_ARRIVAl is not null");
            return (Criteria) this;
        }

        public Criteria andStepArrivalEqualTo(BigDecimal value) {
            addCriterion("STEP_ARRIVAl =", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalNotEqualTo(BigDecimal value) {
            addCriterion("STEP_ARRIVAl <>", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalGreaterThan(BigDecimal value) {
            addCriterion("STEP_ARRIVAl >", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STEP_ARRIVAl >=", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalLessThan(BigDecimal value) {
            addCriterion("STEP_ARRIVAl <", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STEP_ARRIVAl <=", value, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalIn(List<BigDecimal> values) {
            addCriterion("STEP_ARRIVAl in", values, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalNotIn(List<BigDecimal> values) {
            addCriterion("STEP_ARRIVAl not in", values, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STEP_ARRIVAl between", value1, value2, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andStepArrivalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STEP_ARRIVAl not between", value1, value2, "stepArrival");
            return (Criteria) this;
        }

        public Criteria andDebtIsNull() {
            addCriterion("DEBT is null");
            return (Criteria) this;
        }

        public Criteria andDebtIsNotNull() {
            addCriterion("DEBT is not null");
            return (Criteria) this;
        }

        public Criteria andDebtEqualTo(BigDecimal value) {
            addCriterion("DEBT =", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtNotEqualTo(BigDecimal value) {
            addCriterion("DEBT <>", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtGreaterThan(BigDecimal value) {
            addCriterion("DEBT >", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DEBT >=", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtLessThan(BigDecimal value) {
            addCriterion("DEBT <", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DEBT <=", value, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtIn(List<BigDecimal> values) {
            addCriterion("DEBT in", values, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtNotIn(List<BigDecimal> values) {
            addCriterion("DEBT not in", values, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEBT between", value1, value2, "debt");
            return (Criteria) this;
        }

        public Criteria andDebtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEBT not between", value1, value2, "debt");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("PROJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("PROJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("PROJECT_ID =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("PROJECT_ID <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("PROJECT_ID >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROJECT_ID >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("PROJECT_ID <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("PROJECT_ID <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("PROJECT_ID in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("PROJECT_ID not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("PROJECT_ID between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PROJECT_ID not between", value1, value2, "projectId");
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