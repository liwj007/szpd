package com.liwj.szpd.controller;

import com.liwj.szpd.service.UserService;
import com.liwj.szpd.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new_project", method = RequestMethod.GET)
    public JsonResult checkAddProject(@RequestHeader(value = "token") String token) {
        boolean flag = userService.checkAddProjectRight(token);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public JsonResult checkMangerRole(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkMangerRole(token,id);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/leader", method = RequestMethod.GET)
    public JsonResult checkLeaderRole(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkLeaderRole(token,id);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/treasurer", method = RequestMethod.GET)
    public JsonResult checkTreasurerRole(@RequestHeader(value = "token") String token,
                                         @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkTreasurerRole(token,id);
        return JsonResult.renderSuccess(flag);
    }
}
