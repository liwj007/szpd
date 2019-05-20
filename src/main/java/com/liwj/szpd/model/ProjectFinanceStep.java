package com.liwj.szpd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectFinanceStep implements Serializable {
    private Integer id;

    private Date invoiceDate;

    private BigDecimal invoiceMoney;

    private Double invoicePercent;

    private Date accountDate;

    private BigDecimal accountMoney;

    private Double accountPercent;

    private Integer step;

    private Integer projectId;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public ProjectFinanceStep(Integer id, Date invoiceDate, BigDecimal invoiceMoney, Double invoicePercent, Date accountDate, BigDecimal accountMoney, Double accountPercent, Integer step, Integer projectId, Integer createBy, Date createTime, Integer updateBy, Date updateTime) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.invoiceMoney = invoiceMoney;
        this.invoicePercent = invoicePercent;
        this.accountDate = accountDate;
        this.accountMoney = accountMoney;
        this.accountPercent = accountPercent;
        this.step = step;
        this.projectId = projectId;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public ProjectFinanceStep() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public Double getInvoicePercent() {
        return invoicePercent;
    }

    public void setInvoicePercent(Double invoicePercent) {
        this.invoicePercent = invoicePercent;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getAccountPercent() {
        return accountPercent;
    }

    public void setAccountPercent(Double accountPercent) {
        this.accountPercent = accountPercent;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        ProjectFinanceStep other = (ProjectFinanceStep) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInvoiceDate() == null ? other.getInvoiceDate() == null : this.getInvoiceDate().equals(other.getInvoiceDate()))
            && (this.getInvoiceMoney() == null ? other.getInvoiceMoney() == null : this.getInvoiceMoney().equals(other.getInvoiceMoney()))
            && (this.getInvoicePercent() == null ? other.getInvoicePercent() == null : this.getInvoicePercent().equals(other.getInvoicePercent()))
            && (this.getAccountDate() == null ? other.getAccountDate() == null : this.getAccountDate().equals(other.getAccountDate()))
            && (this.getAccountMoney() == null ? other.getAccountMoney() == null : this.getAccountMoney().equals(other.getAccountMoney()))
            && (this.getAccountPercent() == null ? other.getAccountPercent() == null : this.getAccountPercent().equals(other.getAccountPercent()))
            && (this.getStep() == null ? other.getStep() == null : this.getStep().equals(other.getStep()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInvoiceDate() == null) ? 0 : getInvoiceDate().hashCode());
        result = prime * result + ((getInvoiceMoney() == null) ? 0 : getInvoiceMoney().hashCode());
        result = prime * result + ((getInvoicePercent() == null) ? 0 : getInvoicePercent().hashCode());
        result = prime * result + ((getAccountDate() == null) ? 0 : getAccountDate().hashCode());
        result = prime * result + ((getAccountMoney() == null) ? 0 : getAccountMoney().hashCode());
        result = prime * result + ((getAccountPercent() == null) ? 0 : getAccountPercent().hashCode());
        result = prime * result + ((getStep() == null) ? 0 : getStep().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}