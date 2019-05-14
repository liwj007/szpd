package com.liwj.szpd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectFee implements Serializable {
    private Integer id;

    private BigDecimal totalFee;

    private BigDecimal startPercent;

    private BigDecimal middlePercent;

    private BigDecimal preliminaryResultPercent;

    private BigDecimal reviewPercent;

    private BigDecimal finalPercent;

    private Integer revision;

    private Integer createdBy;

    private Date createdTime;

    private Integer updatedBy;

    private Date updatedTime;

    private Integer projectId;

    private static final long serialVersionUID = 1L;

    public ProjectFee(Integer id, BigDecimal totalFee, BigDecimal startPercent, BigDecimal middlePercent, BigDecimal preliminaryResultPercent, BigDecimal reviewPercent, BigDecimal finalPercent, Integer revision, Integer createdBy, Date createdTime, Integer updatedBy, Date updatedTime, Integer projectId) {
        this.id = id;
        this.totalFee = totalFee;
        this.startPercent = startPercent;
        this.middlePercent = middlePercent;
        this.preliminaryResultPercent = preliminaryResultPercent;
        this.reviewPercent = reviewPercent;
        this.finalPercent = finalPercent;
        this.revision = revision;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.projectId = projectId;
    }

    public ProjectFee() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getStartPercent() {
        return startPercent;
    }

    public void setStartPercent(BigDecimal startPercent) {
        this.startPercent = startPercent;
    }

    public BigDecimal getMiddlePercent() {
        return middlePercent;
    }

    public void setMiddlePercent(BigDecimal middlePercent) {
        this.middlePercent = middlePercent;
    }

    public BigDecimal getPreliminaryResultPercent() {
        return preliminaryResultPercent;
    }

    public void setPreliminaryResultPercent(BigDecimal preliminaryResultPercent) {
        this.preliminaryResultPercent = preliminaryResultPercent;
    }

    public BigDecimal getReviewPercent() {
        return reviewPercent;
    }

    public void setReviewPercent(BigDecimal reviewPercent) {
        this.reviewPercent = reviewPercent;
    }

    public BigDecimal getFinalPercent() {
        return finalPercent;
    }

    public void setFinalPercent(BigDecimal finalPercent) {
        this.finalPercent = finalPercent;
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
        ProjectFee other = (ProjectFee) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
            && (this.getStartPercent() == null ? other.getStartPercent() == null : this.getStartPercent().equals(other.getStartPercent()))
            && (this.getMiddlePercent() == null ? other.getMiddlePercent() == null : this.getMiddlePercent().equals(other.getMiddlePercent()))
            && (this.getPreliminaryResultPercent() == null ? other.getPreliminaryResultPercent() == null : this.getPreliminaryResultPercent().equals(other.getPreliminaryResultPercent()))
            && (this.getReviewPercent() == null ? other.getReviewPercent() == null : this.getReviewPercent().equals(other.getReviewPercent()))
            && (this.getFinalPercent() == null ? other.getFinalPercent() == null : this.getFinalPercent().equals(other.getFinalPercent()))
            && (this.getRevision() == null ? other.getRevision() == null : this.getRevision().equals(other.getRevision()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        result = prime * result + ((getStartPercent() == null) ? 0 : getStartPercent().hashCode());
        result = prime * result + ((getMiddlePercent() == null) ? 0 : getMiddlePercent().hashCode());
        result = prime * result + ((getPreliminaryResultPercent() == null) ? 0 : getPreliminaryResultPercent().hashCode());
        result = prime * result + ((getReviewPercent() == null) ? 0 : getReviewPercent().hashCode());
        result = prime * result + ((getFinalPercent() == null) ? 0 : getFinalPercent().hashCode());
        result = prime * result + ((getRevision() == null) ? 0 : getRevision().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }
}