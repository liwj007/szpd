package com.liwj.szpd.service;

import com.liwj.szpd.vo.UserItemVO;

import java.util.List;

public interface ProjectManagerService {
    List<UserItemVO> getManagers(Integer projectID);


    boolean selectUserForManager(String token, Integer projectID, Integer userID);

    boolean deleteUserForManager(Integer id);
}
