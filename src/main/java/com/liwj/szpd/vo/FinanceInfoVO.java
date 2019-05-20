package com.liwj.szpd.vo;

public class FinanceInfoVO {
    private FinanceStepVO info;
    protected boolean editable;
    private double totalFee;

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public FinanceStepVO getInfo() {
        return info;
    }

    public void setInfo(FinanceStepVO info) {
        this.info = info;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
