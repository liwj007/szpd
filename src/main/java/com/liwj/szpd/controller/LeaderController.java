package com.liwj.szpd.controller;

import com.liwj.szpd.service.ProjectLeaderService;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.UserItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/leader")
public class LeaderController {

    @Autowired
    private ProjectLeaderService projectLeaderService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseData getLeadersOfProject(@RequestParam(value = "token") String token,
                                            @RequestParam(value = "pid") Integer projectID) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = projectLeaderService.getLeaders(projectID);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/search_user_for_leader", method = RequestMethod.GET)
    public ResponseData searchUsersForLeader(@RequestParam(value = "token") String token,
                                             @RequestParam(value = "pid") Integer projectID,
                                             @RequestParam(value = "content") String content) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = projectLeaderService.searchUsersForLeader(projectID, content);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/select_user_for_leader", method = RequestMethod.POST)
    public ResponseData selectUserForLeader(@RequestParam(value = "token") String token,
                                             @RequestParam(value = "pid") Integer projectID,
                                             @RequestParam(value = "uid") Integer userID) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectLeaderService.selectUserForLeader(token,projectID, userID);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/delete_user_for_leader", method = RequestMethod.POST)
    public ResponseData deleteUserForLeader(@RequestParam(value = "token") String token,
                                             @RequestParam(value = "id") Integer id) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectLeaderService.deleteUserForLeader(id);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }
}
