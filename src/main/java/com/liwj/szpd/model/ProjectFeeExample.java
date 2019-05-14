package com.liwj.szpd.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectFeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectFeeExample() {
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

        public Criteria andTotalFeeIsNull() {
            addCriterion("TOTAL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIsNotNull() {
            addCriterion("TOTAL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeEqualTo(BigDecimal value) {
            addCriterion("TOTAL_FEE =", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_FEE <>", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_FEE >", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_FEE >=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThan(BigDecimal value) {
            addCriterion("TOTAL_FEE <", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_FEE <=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIn(List<BigDecimal> values) {
            addCriterion("TOTAL_FEE in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_FEE not in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_FEE between", value1, value2, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_FEE not between", value1, value2, "totalFee");
            return (Criteria) this;
        }

        public Criteria andStartPercentIsNull() {
            addCriterion("START_PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andStartPercentIsNotNull() {
            addCriterion("START_PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andStartPercentEqualTo(BigDecimal value) {
            addCriterion("START_PERCENT =", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentNotEqualTo(BigDecimal value) {
            addCriterion("START_PERCENT <>", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentGreaterThan(BigDecimal value) {
            addCriterion("START_PERCENT >", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_PERCENT >=", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentLessThan(BigDecimal value) {
            addCriterion("START_PERCENT <", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_PERCENT <=", value, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentIn(List<BigDecimal> values) {
            addCriterion("START_PERCENT in", values, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentNotIn(List<BigDecimal> values) {
            addCriterion("START_PERCENT not in", values, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_PERCENT between", value1, value2, "startPercent");
            return (Criteria) this;
        }

        public Criteria andStartPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_PERCENT not between", value1, value2, "startPercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentIsNull() {
            addCriterion("MIDDLE_PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentIsNotNull() {
            addCriterion("MIDDLE_PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentEqualTo(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT =", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentNotEqualTo(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT <>", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentGreaterThan(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT >", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT >=", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentLessThan(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT <", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MIDDLE_PERCENT <=", value, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentIn(List<BigDecimal> values) {
            addCriterion("MIDDLE_PERCENT in", values, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentNotIn(List<BigDecimal> values) {
            addCriterion("MIDDLE_PERCENT not in", values, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MIDDLE_PERCENT between", value1, value2, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andMiddlePercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MIDDLE_PERCENT not between", value1, value2, "middlePercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentIsNull() {
            addCriterion("PRELIMINARY_RESULT_PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentIsNotNull() {
            addCriterion("PRELIMINARY_RESULT_PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentEqualTo(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT =", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentNotEqualTo(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT <>", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentGreaterThan(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT >", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT >=", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentLessThan(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT <", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRELIMINARY_RESULT_PERCENT <=", value, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentIn(List<BigDecimal> values) {
            addCriterion("PRELIMINARY_RESULT_PERCENT in", values, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentNotIn(List<BigDecimal> values) {
            addCriterion("PRELIMINARY_RESULT_PERCENT not in", values, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRELIMINARY_RESULT_PERCENT between", value1, value2, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andPreliminaryResultPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRELIMINARY_RESULT_PERCENT not between", value1, value2, "preliminaryResultPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentIsNull() {
            addCriterion("REVIEW_PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andReviewPercentIsNotNull() {
            addCriterion("REVIEW_PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andReviewPercentEqualTo(BigDecimal value) {
            addCriterion("REVIEW_PERCENT =", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentNotEqualTo(BigDecimal value) {
            addCriterion("REVIEW_PERCENT <>", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentGreaterThan(BigDecimal value) {
            addCriterion("REVIEW_PERCENT >", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REVIEW_PERCENT >=", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentLessThan(BigDecimal value) {
            addCriterion("REVIEW_PERCENT <", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REVIEW_PERCENT <=", value, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentIn(List<BigDecimal> values) {
            addCriterion("REVIEW_PERCENT in", values, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentNotIn(List<BigDecimal> values) {
            addCriterion("REVIEW_PERCENT not in", values, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REVIEW_PERCENT between", value1, value2, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andReviewPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REVIEW_PERCENT not between", value1, value2, "reviewPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentIsNull() {
            addCriterion("FINAL_PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andFinalPercentIsNotNull() {
            addCriterion("FINAL_PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andFinalPercentEqualTo(BigDecimal value) {
            addCriterion("FINAL_PERCENT =", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentNotEqualTo(BigDecimal value) {
            addCriterion("FINAL_PERCENT <>", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentGreaterThan(BigDecimal value) {
            addCriterion("FINAL_PERCENT >", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FINAL_PERCENT >=", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentLessThan(BigDecimal value) {
            addCriterion("FINAL_PERCENT <", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FINAL_PERCENT <=", value, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentIn(List<BigDecimal> values) {
            addCriterion("FINAL_PERCENT in", values, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentNotIn(List<BigDecimal> values) {
            addCriterion("FINAL_PERCENT not in", values, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FINAL_PERCENT between", value1, value2, "finalPercent");
            return (Criteria) this;
        }

        public Criteria andFinalPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FINAL_PERCENT not between", value1, value2, "finalPercent");
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