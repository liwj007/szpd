package com.liwj.szpd.vo;

import java.util.List;

public class ScheduleItemVO {
    private String planDate;
    private String actualDate;
    private List<ProjectFileVO> files;
    private Integer step;
    private String stepName;

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

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
}
