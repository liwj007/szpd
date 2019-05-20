package com.liwj.szpd.vo;

import java.util.List;

public class MembersVO {
    private List<UserVO> managers;
    private boolean canEditManager;

    private List<UserVO> leaders;
    private boolean canEditLeader;

    private List<UserVO> members;
    private boolean canEditMember;

    public List<UserVO> getManagers() {
        return managers;
    }

    public void setManagers(List<UserVO> managers) {
        this.managers = managers;
    }

    public boolean isCanEditManager() {
        return canEditManager;
    }

    public void setCanEditManager(boolean canEditManager) {
        this.canEditManager = canEditManager;
    }

    public List<UserVO> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<UserVO> leaders) {
        this.leaders = leaders;
    }

    public boolean isCanEditLeader() {
        return canEditLeader;
    }

    public void setCanEditLeader(boolean canEditLeader) {
        this.canEditLeader = canEditLeader;
    }

    public List<UserVO> getMembers() {
        return members;
    }

    public void setMembers(List<UserVO> members) {
        this.members = members;
    }

    public boolean isCanEditMember() {
        return canEditMember;
    }

    public void setCanEditMember(boolean canEditMember) {
        this.canEditMember = canEditMember;
    }
}
