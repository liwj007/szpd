package com.liwj.szpd.vo;

public class ProjectRightVO {
    private boolean update;
    private boolean manager;
    private boolean leader;
    private boolean delete;
    private boolean close;
    private boolean member;

    public ProjectRightVO() {
        this.update = false;
        this.manager = false;
        this.leader = false;
        this.delete = false;
        this.close = false;
        this.member = false;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}
