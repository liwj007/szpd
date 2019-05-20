package com.liwj.szpd.controller;

import com.liwj.szpd.service.ProjectMemberService;
import com.liwj.szpd.service.ProjectService;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.UserItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseData getMembersOfProject(@RequestHeader(value = "token") String token,
                                            @RequestParam(value = "pid") Integer projectID) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = projectMemberService.getMembers(projectID);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/search_user_for_members", method = RequestMethod.GET)
    public ResponseData searchUsersForMember(@RequestHeader(value = "token") String token,
                                             @RequestParam(value = "pid") Integer projectID,
                                             @RequestParam(value = "content") String content) {
        ResponseData responseData = new ResponseData();
        List<UserItemVO> res = projectService.searchUsersForProject(projectID, content);
        return responseData.setSuccessData(res);
    }

    @RequestMapping(value = "/select_user_for_member", method = RequestMethod.POST)
    public ResponseData selectUserForMember(@RequestHeader(value = "token") String token,
                                            @RequestParam(value = "pid") Integer projectID,
                                            @RequestParam(value = "uid") Integer userID) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectMemberService.selectUserForMember(token,projectID, userID);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/delete_user_for_member", method = RequestMethod.POST)
    public ResponseData deleteUserForMember(@RequestHeader(value = "token") String token,
                                            @RequestParam(value = "id") Integer id) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectMemberService.deleteUserForMember(id);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }
}
