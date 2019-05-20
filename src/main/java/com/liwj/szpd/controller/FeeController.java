package com.liwj.szpd.controller;

import com.liwj.szpd.service.ProjectFeeService;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.vo.FinanceInfoVO;
import com.liwj.szpd.vo.FinanceStepVO;
import com.liwj.szpd.vo.FinanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fee")
public class FeeController {
    @Autowired
    private ProjectFeeService projectFeeService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateFee(@RequestHeader(value = "token") String token,
                                @RequestBody FinanceStepVO form) {
        boolean flag = projectFeeService.update(token, form);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail("更新项目失败");
        }
    }


    @RequestMapping(value = "/finance_info", method = RequestMethod.GET)
    public JsonResult getFinanceInfo(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "pid") Integer projectID) {
        FinanceVO res=  projectFeeService.getFinanceInfo(projectID);
        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/finance_step_info", method = RequestMethod.GET)
    public JsonResult getFinanceStepInfo(@RequestHeader(value = "token") String token,
                                     @RequestParam(value = "id") Integer stepId) {
        FinanceInfoVO res=  projectFeeService.getFinanceStepInfo(token,stepId);
        return JsonResult.renderSuccess(res);
    }
}
