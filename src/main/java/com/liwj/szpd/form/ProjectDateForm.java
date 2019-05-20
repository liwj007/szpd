package com.liwj.szpd.form;

public class ProjectDateForm {
    private Integer projectId;
    private String startDate;
    private String middleDate;
    private String finalDate;
    private String reviewDate;
    private String preliminaryDate;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMiddleDate() {
        return middleDate;
    }

    public void setMiddleDate(String middleDate) {
        this.middleDate = middleDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getPreliminaryDate() {
        return preliminaryDate;
    }

    public void setPreliminaryDate(String preliminaryDate) {
        this.preliminaryDate = preliminaryDate;
    }
}
