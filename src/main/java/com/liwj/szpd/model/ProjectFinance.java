package com.liwj.szpd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectFinance implements Serializable {
    private Integer id;

    private Integer revision;

    private Integer createdBy;

    private Date createdTime;

    private Integer updatedBy;

    private Date updatedTime;

    private BigDecimal invoice;

    private BigDecimal arrival;

    private BigDecimal noArrival;

    private BigDecimal stepArrival;

    private BigDecimal debt;

    private Integer projectId;

    private static final long serialVersionUID = 1L;

    public ProjectFinance(Integer id, Integer revision, Integer createdBy, Date createdTime, Integer updatedBy, Date updatedTime, BigDecimal invoice, BigDecimal arrival, BigDecimal noArrival, BigDecimal stepArrival, BigDecimal debt, Integer projectId) {
        this.id = id;
        this.revision = revision;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.invoice = invoice;
        this.arrival = arrival;
        this.noArrival = noArrival;
        this.stepArrival = stepArrival;
        this.debt = debt;
        this.projectId = projectId;
    }

    public ProjectFinance() {
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

    public BigDecimal getInvoice() {
        return invoice;
    }

    public void setInvoice(BigDecimal invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getArrival() {
        return arrival;
    }

    public void setArrival(BigDecimal arrival) {
        this.arrival = arrival;
    }

    public BigDecimal getNoArrival() {
        return noArrival;
    }

    public void setNoArrival(BigDecimal noArrival) {
        this.noArrival = noArrival;
    }

    public BigDecimal getStepArrival() {
        return stepArrival;
    }

    public void setStepArrival(BigDecimal stepArrival) {
        this.stepArrival = stepArrival;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
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
        ProjectFinance other = (ProjectFinance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRevision() == null ? other.getRevision() == null : this.getRevision().equals(other.getRevision()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getInvoice() == null ? other.getInvoice() == null : this.getInvoice().equals(other.getInvoice()))
            && (this.getArrival() == null ? other.getArrival() == null : this.getArrival().equals(other.getArrival()))
            && (this.getNoArrival() == null ? other.getNoArrival() == null : this.getNoArrival().equals(other.getNoArrival()))
            && (this.getStepArrival() == null ? other.getStepArrival() == null : this.getStepArrival().equals(other.getStepArrival()))
            && (this.getDebt() == null ? other.getDebt() == null : this.getDebt().equals(other.getDebt()))
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
        result = prime * result + ((getInvoice() == null) ? 0 : getInvoice().hashCode());
        result = prime * result + ((getArrival() == null) ? 0 : getArrival().hashCode());
        result = prime * result + ((getNoArrival() == null) ? 0 : getNoArrival().hashCode());
        result = prime * result + ((getStepArrival() == null) ? 0 : getStepArrival().hashCode());
        result = prime * result + ((getDebt() == null) ? 0 : getDebt().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }
}