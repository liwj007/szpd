package com.liwj.szpd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProjectScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectScheduleExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateIsNull() {
            addCriterion("PLAN_START_UP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateIsNotNull() {
            addCriterion("PLAN_START_UP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE =", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE <>", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE >", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE >=", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateLessThan(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE <", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE <=", value, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE in", values, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE not in", values, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE between", value1, value2, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartUpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_START_UP_DATE not between", value1, value2, "planStartUpDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateIsNull() {
            addCriterion("PLAN_MIDDLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateIsNotNull() {
            addCriterion("PLAN_MIDDLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE =", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE <>", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE >", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE >=", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateLessThan(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE <", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE <=", value, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE in", values, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE not in", values, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE between", value1, value2, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanMiddleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_MIDDLE_DATE not between", value1, value2, "planMiddleDate");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultIsNull() {
            addCriterion("PLAN_PRELIMINARY_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultIsNotNull() {
            addCriterion("PLAN_PRELIMINARY_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT =", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultNotEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT <>", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultGreaterThan(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT >", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT >=", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultLessThan(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT <", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT <=", value, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT in", values, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultNotIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT not in", values, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT between", value1, value2, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanPreliminaryResultNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_PRELIMINARY_RESULT not between", value1, value2, "planPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateIsNull() {
            addCriterion("PLAN_REVIEW_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateIsNotNull() {
            addCriterion("PLAN_REVIEW_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE =", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE <>", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateGreaterThan(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE >", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE >=", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateLessThan(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE <", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE <=", value, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE in", values, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE not in", values, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE between", value1, value2, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanReviewDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_REVIEW_DATE not between", value1, value2, "planReviewDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateIsNull() {
            addCriterion("PLAN_FINAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateIsNotNull() {
            addCriterion("PLAN_FINAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE =", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE <>", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE >", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE >=", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateLessThan(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE <", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE <=", value, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE in", values, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE not in", values, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE between", value1, value2, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PLAN_FINAL_DATE not between", value1, value2, "planFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateIsNull() {
            addCriterion("ACTUAL_START_UP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateIsNotNull() {
            addCriterion("ACTUAL_START_UP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE =", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE <>", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE >", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE >=", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateLessThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE <", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE <=", value, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE in", values, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE not in", values, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE between", value1, value2, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualStartUpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_START_UP_DATE not between", value1, value2, "actualStartUpDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateIsNull() {
            addCriterion("ACTUAL_MIDDLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateIsNotNull() {
            addCriterion("ACTUAL_MIDDLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE =", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE <>", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE >", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE >=", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateLessThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE <", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE <=", value, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE in", values, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE not in", values, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE between", value1, value2, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualMiddleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_MIDDLE_DATE not between", value1, value2, "actualMiddleDate");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultIsNull() {
            addCriterion("ACTUAL_PRELIMINARY_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultIsNotNull() {
            addCriterion("ACTUAL_PRELIMINARY_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT =", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT <>", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT >", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT >=", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultLessThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT <", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT <=", value, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT in", values, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT not in", values, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT between", value1, value2, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualPreliminaryResultNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_PRELIMINARY_RESULT not between", value1, value2, "actualPreliminaryResult");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateIsNull() {
            addCriterion("ACTUAL_REVIEW_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateIsNotNull() {
            addCriterion("ACTUAL_REVIEW_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE =", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE <>", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE >", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE >=", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateLessThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE <", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE <=", value, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE in", values, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE not in", values, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE between", value1, value2, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualReviewDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_REVIEW_DATE not between", value1, value2, "actualReviewDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateIsNull() {
            addCriterion("ACTUAL_FINAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateIsNotNull() {
            addCriterion("ACTUAL_FINAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE =", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE <>", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE >", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE >=", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateLessThan(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE <", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE <=", value, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE in", values, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE not in", values, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE between", value1, value2, "actualFinalDate");
            return (Criteria) this;
        }

        public Criteria andActualFinalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACTUAL_FINAL_DATE not between", value1, value2, "actualFinalDate");
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