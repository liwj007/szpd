package com.liwj.szpd.controller;

import com.liwj.szpd.service.UserService;
import com.liwj.szpd.third.api.WeiXinAPI;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonResult getOpenid(@RequestParam(value = "code") String code,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "avatar") String avatar) {
        Map<String, String> weInfo = WeiXinAPI.getSessionFromCode(code);
        if (weInfo == null) {
            return JsonResult.renderError("微信请求错误");
        }


        String openid = weInfo.get("openid");
        String session_key = weInfo.get("session_key");

        String token = userService.login(openid, session_key, name, avatar);

        return JsonResult.renderSuccess(Constants.SUCCESS, token);
    }

    @RequestMapping(value = "/web_login", method = RequestMethod.POST)
    public JsonResult getOpenid(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password) {

        String token = userService.webLogin(username, password);
        if (token != null)
            return JsonResult.renderSuccess(Constants.SUCCESS, token);
        else
            return JsonResult.renderFail();
    }

    @RequestMapping(value = "/isActive", method = RequestMethod.GET)
    public JsonResult isActive(@RequestHeader(value = "token") String token) {
        String newToken = userService.checkActive(token);
        if (newToken == null)
            return JsonResult.renderFail();
        if (newToken.equals("no_user"))
            return JsonResult.renderFail("no_user");
        return JsonResult.renderSuccess(Constants.SUCCESS, newToken);
    }

    @RequestMapping(value = "/get_verification_code", method = RequestMethod.GET)
    public JsonResult getVerificationCode(@RequestHeader(value = "token") String token,
                                          @RequestParam(value = "phone") String phone) {
        String code = userService.generateVerifyCode(token, phone);
        if (code != null) {
            return JsonResult.renderSuccess(Constants.SUCCESS, code);
        } else {
            return JsonResult.renderError("获取验证码失败");
        }
    }

    //web
    @RequestMapping(value = "/get_sms_captcha", method = RequestMethod.GET)
    public JsonResult getSmsCaptcha(@RequestParam(value = "mobile") String mobile) {
        String code = userService.generateVerifyCodeForWeb(mobile);
        if (code != null) {
            return JsonResult.renderSuccess(Constants.SUCCESS, code);
        } else {
            return JsonResult.renderError("获取验证码失败");
        }
    }

    //web
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public JsonResult updatePassword(@RequestParam(value = "phone") String phone,
                                @RequestParam(value = "code") String code,
                                @RequestParam(value = "password") String password) {
        boolean flag = userService.updatePassword(phone, code,password);
        if (flag) {
            return JsonResult.renderSuccess(Constants.SUCCESS);
        } else {
            return JsonResult.renderError("绑定手机失败");
        }
    }

    @RequestMapping(value = "/bind_phone", method = RequestMethod.POST)
    public JsonResult bindPhone(@RequestHeader(value = "token") String token,
                                @RequestParam(value = "phone") String phone,
                                @RequestParam(value = "code") String code) {
        String name = userService.bindPhone(token, phone, code);
        if (name != null) {
            return JsonResult.renderSuccess(Constants.SUCCESS, name);
        } else {
            return JsonResult.renderError("绑定手机失败");
        }
    }

    @RequestMapping(value = "/check_name", method = RequestMethod.POST)
    public JsonResult checkName(@RequestHeader(value = "token") String token,
                                @RequestParam(value = "name") String name) {
        boolean flag = userService.checkName(token, name);
        if (flag) {
            return JsonResult.renderSuccess(Constants.SUCCESS );
        } else {
            return JsonResult.renderError("");
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getInfo(@RequestHeader(value = "token") String token) {

        UserVO vo = userService.getInfo(token);

        return JsonResult.renderSuccess(Constants.SUCCESS, vo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult logout(@RequestHeader(value = "token") String token) {
        return JsonResult.renderSuccess(Constants.SUCCESS);
    }
}
