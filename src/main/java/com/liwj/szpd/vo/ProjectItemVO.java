package com.liwj.szpd.vo;

public class ProjectItemVO {
    private Integer key;
    private Integer id;
    private String name;
    private String iconName;
    private String date;
    private ProjectRightVO rights;
    private String no;

    private Integer status;
    private Double progress;
    private Double financeProgress;

    public Double getFinanceProgress() {
        return financeProgress;
    }

    public void setFinanceProgress(Double financeProgress) {
        this.financeProgress = financeProgress;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    private boolean initStatus;

    public boolean isInitStatus() {
        return initStatus;
    }

    public void setInitStatus(boolean initStatus) {
        this.initStatus = initStatus;
    }

    public ProjectItemVO() {
        rights = new ProjectRightVO();
    }

    public ProjectRightVO getRights() {
        return rights;
    }

    public void setRights(ProjectRightVO rights) {
        this.rights = rights;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        this.key = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
