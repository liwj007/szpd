package com.liwj.szpd.vo;

import com.liwj.szpd.model.ProjectFile;

import java.util.List;

public class FinanceStepVO {
    private Integer step;
    private Integer id;
    private String stepName;
    private Double invoiceMoney;
    private Double invoicePercent;
    private String invoiceDate;
    private Double accountMoney;
    private Double accountPercent;
    private String accountDate;

    private List<ProjectFileVO> files;

    public List<ProjectFileVO> getFiles() {
        return files;
    }

    public void setFiles(List<ProjectFileVO> files) {
        this.files = files;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public Double getInvoicePercent() {
        return invoicePercent;
    }

    public void setInvoicePercent(Double invoicePercent) {
        this.invoicePercent = invoicePercent;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getAccountPercent() {
        return accountPercent;
    }

    public void setAccountPercent(Double accountPercent) {
        this.accountPercent = accountPercent;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }
}
