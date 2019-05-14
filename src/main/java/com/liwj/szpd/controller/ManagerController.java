package com.liwj.szpd.controller;

import com.liwj.szpd.service.ProjectManagerService;
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
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private ProjectManagerService managerService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseData getManagersOfProject(@RequestParam(value = "token") String token,
                                            @RequestParam(value = "pid") Integer projectID) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = managerService.getManagers(projectID);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/search_user_for_manager", method = RequestMethod.GET)
    public ResponseData searchUsersForManager(@RequestParam(value = "token") String token,
                                             @RequestParam(value = "pid") Integer projectID,
                                             @RequestParam(value = "content") String content) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = managerService.searchUsersForManager(projectID, content);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/select_user_for_manager", method = RequestMethod.POST)
    public ResponseData selectUserForManager(@RequestParam(value = "token") String token,
                                            @RequestParam(value = "pid") Integer projectID,
                                            @RequestParam(value = "uid") Integer userID) {
        ResponseData responseData = new ResponseData();
        boolean flag = managerService.selectUserForManager(token,projectID, userID);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/delete_user_for_manager", method = RequestMethod.POST)
    public ResponseData deleteUserForManager(@RequestParam(value = "token") String token,
                                            @RequestParam(value = "id") Integer id) {
        ResponseData responseData = new ResponseData();
        boolean flag = managerService.deleteUserForManager(id);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }
}
