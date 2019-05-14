package com.liwj.szpd.controller;

import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public JsonResult swtich(@RequestParam(value = "type") int type) {
        switch (type) {
            case 0:
                return JsonResult.renderSuccess(Constants.SUCCESS, "3E4ZXAMxbkipm9gm5PiJSg==");
            case 1:
                return JsonResult.renderSuccess(Constants.SUCCESS, "1");
            case 2:
                return JsonResult.renderSuccess(Constants.SUCCESS, "2");
            case 3:
                return JsonResult.renderSuccess(Constants.SUCCESS, "3");
        }
        return JsonResult.renderFail();
    }
}
