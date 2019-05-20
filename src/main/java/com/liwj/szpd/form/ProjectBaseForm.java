package com.liwj.szpd.form;

public class ProjectBaseForm {
    private Integer id;
    private String name;
    private String code;
    private String contract;
    private String party;
    private Double totalFee;

    private String startPercent;
    private String middlePercent;
    private String finalPercent;
    private String reviewPercent;
    private String preliminaryPercent;
    private String remark;

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private boolean editable;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getStartPercent() {
        return startPercent;
    }

    public void setStartPercent(String startPercent) {
        this.startPercent = startPercent;
    }

    public String getMiddlePercent() {
        return middlePercent;
    }

    public void setMiddlePercent(String middlePercent) {
        this.middlePercent = middlePercent;
    }

    public String getFinalPercent() {
        return finalPercent;
    }

    public void setFinalPercent(String finalPercent) {
        this.finalPercent = finalPercent;
    }

    public String getReviewPercent() {
        return reviewPercent;
    }

    public void setReviewPercent(String reviewPercent) {
        this.reviewPercent = reviewPercent;
    }

    public String getPreliminaryPercent() {
        return preliminaryPercent;
    }

    public void setPreliminaryPercent(String preliminaryPercent) {
        this.preliminaryPercent = preliminaryPercent;
    }
}
