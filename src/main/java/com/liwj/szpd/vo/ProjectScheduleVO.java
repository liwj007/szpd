package com.liwj.szpd.vo;

import java.util.List;

public class ProjectScheduleVO {
    private Integer id;
    private String startDate;
    private boolean editable;

    private List<ScheduleItemVO> schedules;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<ScheduleItemVO> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleItemVO> schedules) {
        this.schedules = schedules;
    }
}
