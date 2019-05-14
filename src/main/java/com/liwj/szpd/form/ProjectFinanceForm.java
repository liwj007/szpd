package com.liwj.szpd.form;

public class ProjectFinanceForm {
    private String invoice;
    private String arrival;
    private String noArrival;
    private String stepArrival;
    private String debt;
    private Integer id;
    private  boolean editable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getNoArrival() {
        return noArrival;
    }

    public void setNoArrival(String noArrival) {
        this.noArrival = noArrival;
    }

    public String getStepArrival() {
        return stepArrival;
    }

    public void setStepArrival(String stepArrival) {
        this.stepArrival = stepArrival;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }
}
