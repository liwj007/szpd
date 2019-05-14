package com.liwj.szpd.vo;

public class ProjectItemVO {
    private Integer id;
    private String name;
    private String iconName;
    private String date;
    private ProjectRightVO rights;

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
