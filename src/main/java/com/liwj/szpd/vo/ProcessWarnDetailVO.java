package com.liwj.szpd.vo;

import java.util.ArrayList;
import java.util.List;

public class ProcessWarnDetailVO {
    private Integer id;
    private String name;
    private List<String> outDateSteps = new ArrayList<>();
    private List<String> monthSteps = new ArrayList<>();

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

    public List<String> getOutDateSteps() {
        return outDateSteps;
    }

    public void setOutDateSteps(List<String> outDateSteps) {
        this.outDateSteps = outDateSteps;
    }

    public List<String> getMonthSteps() {
        return monthSteps;
    }

    public void setMonthSteps(List<String> monthSteps) {
        this.monthSteps = monthSteps;
    }
}
