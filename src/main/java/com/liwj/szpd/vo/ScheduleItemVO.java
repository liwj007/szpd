package com.liwj.szpd.vo;

import java.util.List;

public class ScheduleItemVO {
    private String planDate;
    private String actualDate;
    private List<ProjectFileVO> cFiles;
    private List<ProjectFileVO> pFiles;
    private List<ProjectFileVO> mFiles;
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

    public List<ProjectFileVO> getcFiles() {
        return cFiles;
    }

    public void setcFiles(List<ProjectFileVO> cFiles) {
        this.cFiles = cFiles;
    }

    public List<ProjectFileVO> getpFiles() {
        return pFiles;
    }

    public void setpFiles(List<ProjectFileVO> pFiles) {
        this.pFiles = pFiles;
    }

    public List<ProjectFileVO> getmFiles() {
        return mFiles;
    }

    public void setmFiles(List<ProjectFileVO> mFiles) {
        this.mFiles = mFiles;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
