package com.liwj.szpd.service;

import com.liwj.szpd.model.User;
import com.liwj.szpd.vo.UserVO;

import java.util.List;

public interface UserService {
    String login(String openid, String session_key,String name,String avatar);

    String checkActive(String token);

    String generateVerifyCode(String token,String phone);

    String bindPhone(String token, String phone, String code);

    boolean checkAddProjectRight(String token);

    boolean checkMangerRole(String token,Integer projectId);

    boolean checkLeaderRole(String token,Integer projectId);

    boolean checkTreasurerRole(String token,Integer projectId);

    UserVO getInfo(String token);

    String webLogin(String username, String password);

    boolean checkName(String token, String name);

    String generateVerifyCodeForWeb(String mobile);

    boolean updatePassword(String phone, String code, String password);
}
