package com.liwj.szpd.controller;

import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.User;
import com.liwj.szpd.model.UserExample;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public JsonResult swtich(@RequestParam(value = "type") int type) {
        User user = null;
        switch (type) {
            case 0:
                user = userMapper.selectByPrimaryKey(28);
                break;
            case 1:
                user = userMapper.selectByPrimaryKey(22);
                break;
            case 2:
                user = userMapper.selectByPrimaryKey(13);
                break;
            case 3:
                user = userMapper.selectByPrimaryKey(26);
                break;
        }
        Calendar c = Calendar.getInstance();
        String t = TokenProcessor.getInstance().sign(user.getName(),user.getId().toString());
        c.add(Calendar.DAY_OF_MONTH, 1);
        user.setToken(t);
        user.setToeknExperie(c.getTimeInMillis());
        userMapper.updateByPrimaryKeySelective(user);
        if (user != null)
            return JsonResult.renderSuccess(Constants.SUCCESS, user.getToken());
        else
            return JsonResult.renderFail();
    }
}
