package com.liwj.szpd.service;

import com.liwj.szpd.vo.UserItemVO;

import java.util.List;

public interface ProjectMemberService {
    List<UserItemVO> getMembers(Integer projectID);

    List<UserItemVO> searchUsersForMember(Integer projectID, String content);

    boolean selectUserForMember(String token, Integer projectID, Integer userID);

    boolean deleteUserForMember(Integer id);
}
