package com.liwj.szpd.vo;

public class FinanceStatisticVO {
    private double stepNoInvoice;
    private double invoice;
    private double account;
    private double invoiceNoAccount;
    private double debt;

    public double getStepNoInvoice() {
        return stepNoInvoice;
    }

    public void setStepNoInvoice(double stepNoInvoice) {
        this.stepNoInvoice = stepNoInvoice;
    }

    public double getInvoice() {
        return invoice;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public double getInvoiceNoAccount() {
        return invoiceNoAccount;
    }

    public void setInvoiceNoAccount(double invoiceNoAccount) {
        this.invoiceNoAccount = invoiceNoAccount;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }
}
