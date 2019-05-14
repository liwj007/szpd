package com.liwj.szpd.controller;

import com.liwj.szpd.service.UserService;
import com.liwj.szpd.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new_project", method = RequestMethod.GET)
    public JsonResult checkAddProject(@RequestParam(value = "token") String token) {
        boolean flag = userService.checkAddProjectRight(token);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public JsonResult checkMangerRole(@RequestParam(value = "token") String token,
                                      @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkMangerRole(token,id);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/leader", method = RequestMethod.GET)
    public JsonResult checkLeaderRole(@RequestParam(value = "token") String token,
                                      @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkLeaderRole(token,id);
        return JsonResult.renderSuccess(flag);
    }

    @RequestMapping(value = "/treasurer", method = RequestMethod.GET)
    public JsonResult checkTreasurerRole(@RequestParam(value = "token") String token,
                                         @RequestParam(value = "id") Integer id) {
        boolean flag = userService.checkTreasurerRole(token,id);
        return JsonResult.renderSuccess(flag);
    }
}
