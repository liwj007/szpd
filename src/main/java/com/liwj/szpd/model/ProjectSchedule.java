package com.liwj.szpd.model;

import java.io.Serializable;
import java.util.Date;

public class ProjectSchedule implements Serializable {
    private Integer id;

    private Integer revision;

    private Integer createdBy;

    private Date createdTime;

    private Integer updatedBy;

    private Date updatedTime;

    private Date startDate;

    private Date planStartUpDate;

    private Date planMiddleDate;

    private Date planPreliminaryResult;

    private Date planReviewDate;

    private Date planFinalDate;

    private Date actualStartUpDate;

    private Date actualMiddleDate;

    private Date actualPreliminaryResult;

    private Date actualReviewDate;

    private Date actualFinalDate;

    private Integer projectId;

    private static final long serialVersionUID = 1L;

    public ProjectSchedule(Integer id, Integer revision, Integer createdBy, Date createdTime, Integer updatedBy, Date updatedTime, Date startDate, Date planStartUpDate, Date planMiddleDate, Date planPreliminaryResult, Date planReviewDate, Date planFinalDate, Date actualStartUpDate, Date actualMiddleDate, Date actualPreliminaryResult, Date actualReviewDate, Date actualFinalDate, Integer projectId) {
        this.id = id;
        this.revision = revision;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.startDate = startDate;
        this.planStartUpDate = planStartUpDate;
        this.planMiddleDate = planMiddleDate;
        this.planPreliminaryResult = planPreliminaryResult;
        this.planReviewDate = planReviewDate;
        this.planFinalDate = planFinalDate;
        this.actualStartUpDate = actualStartUpDate;
        this.actualMiddleDate = actualMiddleDate;
        this.actualPreliminaryResult = actualPreliminaryResult;
        this.actualReviewDate = actualReviewDate;
        this.actualFinalDate = actualFinalDate;
        this.projectId = projectId;
    }

    public ProjectSchedule() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPlanStartUpDate() {
        return planStartUpDate;
    }

    public void setPlanStartUpDate(Date planStartUpDate) {
        this.planStartUpDate = planStartUpDate;
    }

    public Date getPlanMiddleDate() {
        return planMiddleDate;
    }

    public void setPlanMiddleDate(Date planMiddleDate) {
        this.planMiddleDate = planMiddleDate;
    }

    public Date getPlanPreliminaryResult() {
        return planPreliminaryResult;
    }

    public void setPlanPreliminaryResult(Date planPreliminaryResult) {
        this.planPreliminaryResult = planPreliminaryResult;
    }

    public Date getPlanReviewDate() {
        return planReviewDate;
    }

    public void setPlanReviewDate(Date planReviewDate) {
        this.planReviewDate = planReviewDate;
    }

    public Date getPlanFinalDate() {
        return planFinalDate;
    }

    public void setPlanFinalDate(Date planFinalDate) {
        this.planFinalDate = planFinalDate;
    }

    public Date getActualStartUpDate() {
        return actualStartUpDate;
    }

    public void setActualStartUpDate(Date actualStartUpDate) {
        this.actualStartUpDate = actualStartUpDate;
    }

    public Date getActualMiddleDate() {
        return actualMiddleDate;
    }

    public void setActualMiddleDate(Date actualMiddleDate) {
        this.actualMiddleDate = actualMiddleDate;
    }

    public Date getActualPreliminaryResult() {
        return actualPreliminaryResult;
    }

    public void setActualPreliminaryResult(Date actualPreliminaryResult) {
        this.actualPreliminaryResult = actualPreliminaryResult;
    }

    public Date getActualReviewDate() {
        return actualReviewDate;
    }

    public void setActualReviewDate(Date actualReviewDate) {
        this.actualReviewDate = actualReviewDate;
    }

    public Date getActualFinalDate() {
        return actualFinalDate;
    }

    public void setActualFinalDate(Date actualFinalDate) {
        this.actualFinalDate = actualFinalDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProjectSchedule other = (ProjectSchedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRevision() == null ? other.getRevision() == null : this.getRevision().equals(other.getRevision()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getPlanStartUpDate() == null ? other.getPlanStartUpDate() == null : this.getPlanStartUpDate().equals(other.getPlanStartUpDate()))
            && (this.getPlanMiddleDate() == null ? other.getPlanMiddleDate() == null : this.getPlanMiddleDate().equals(other.getPlanMiddleDate()))
            && (this.getPlanPreliminaryResult() == null ? other.getPlanPreliminaryResult() == null : this.getPlanPreliminaryResult().equals(other.getPlanPreliminaryResult()))
            && (this.getPlanReviewDate() == null ? other.getPlanReviewDate() == null : this.getPlanReviewDate().equals(other.getPlanReviewDate()))
            && (this.getPlanFinalDate() == null ? other.getPlanFinalDate() == null : this.getPlanFinalDate().equals(other.getPlanFinalDate()))
            && (this.getActualStartUpDate() == null ? other.getActualStartUpDate() == null : this.getActualStartUpDate().equals(other.getActualStartUpDate()))
            && (this.getActualMiddleDate() == null ? other.getActualMiddleDate() == null : this.getActualMiddleDate().equals(other.getActualMiddleDate()))
            && (this.getActualPreliminaryResult() == null ? other.getActualPreliminaryResult() == null : this.getActualPreliminaryResult().equals(other.getActualPreliminaryResult()))
            && (this.getActualReviewDate() == null ? other.getActualReviewDate() == null : this.getActualReviewDate().equals(other.getActualReviewDate()))
            && (this.getActualFinalDate() == null ? other.getActualFinalDate() == null : this.getActualFinalDate().equals(other.getActualFinalDate()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRevision() == null) ? 0 : getRevision().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getPlanStartUpDate() == null) ? 0 : getPlanStartUpDate().hashCode());
        result = prime * result + ((getPlanMiddleDate() == null) ? 0 : getPlanMiddleDate().hashCode());
        result = prime * result + ((getPlanPreliminaryResult() == null) ? 0 : getPlanPreliminaryResult().hashCode());
        result = prime * result + ((getPlanReviewDate() == null) ? 0 : getPlanReviewDate().hashCode());
        result = prime * result + ((getPlanFinalDate() == null) ? 0 : getPlanFinalDate().hashCode());
        result = prime * result + ((getActualStartUpDate() == null) ? 0 : getActualStartUpDate().hashCode());
        result = prime * result + ((getActualMiddleDate() == null) ? 0 : getActualMiddleDate().hashCode());
        result = prime * result + ((getActualPreliminaryResult() == null) ? 0 : getActualPreliminaryResult().hashCode());
        result = prime * result + ((getActualReviewDate() == null) ? 0 : getActualReviewDate().hashCode());
        result = prime * result + ((getActualFinalDate() == null) ? 0 : getActualFinalDate().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }
}