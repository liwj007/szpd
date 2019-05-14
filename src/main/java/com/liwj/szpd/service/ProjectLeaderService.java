package com.liwj.szpd.service;

import com.liwj.szpd.vo.UserItemVO;

import java.util.List;

public interface ProjectLeaderService {
    List<UserItemVO> getLeaders(Integer projectID);

    List<UserItemVO> searchUsersForLeader(Integer projectID, String content);

    boolean selectUserForLeader(String token,Integer projectID, Integer userID);

    boolean deleteUserForLeader(Integer id);
}
