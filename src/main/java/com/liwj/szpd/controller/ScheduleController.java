package com.liwj.szpd.controller;

import com.liwj.szpd.form.ProjectDateForm;
import com.liwj.szpd.service.ProjectScheduleService;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Autowired
    private ProjectScheduleService projectScheduleService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getScheduleInfo(@RequestHeader(value = "token") String token,
                                      @RequestParam(value = "id") Integer projectId) {

        ProjectScheduleVO vo = projectScheduleService.getScheduleInfo(token, projectId);
        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/step_info", method = RequestMethod.GET)
    public JsonResult getScheduleStepInfo(@RequestHeader(value = "token") String token,
                                          @RequestParam(value = "id") Integer scheduleId,
                                          @RequestParam(value = "step") Integer step) {

        ScheduleItemVO vo = projectScheduleService.getScheduleStepInfo(token, scheduleId, step);
        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/update_step", method = RequestMethod.POST)
    public JsonResult updateStep(@RequestHeader(value = "token") String token,
                                 @RequestParam(value = "id") Integer scheduleId,
                                 @RequestParam(value = "step") Integer step,
                                 @RequestParam(value = "actualDate") String actualDate,
                                 @RequestParam(value = "cfiles") List<String> cFiles,
                                 @RequestParam(value = "pfiles") List<String> pFiles,
                                 @RequestParam(value = "mfiles") List<String> mFiles) {
        if (actualDate != null && "null".equals(actualDate))
            actualDate = null;
        boolean flag = projectScheduleService.updateStep(token, scheduleId, step, actualDate, cFiles,pFiles,mFiles);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail();
        }
    }

    @RequestMapping(value = "/init_date", method = RequestMethod.POST)
    public JsonResult initDate(@RequestHeader(value = "token") String token,
                               @RequestBody ProjectDateForm projectDateForm) {
        boolean flag = projectScheduleService.updateDates(token, projectDateForm);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail();
        }
    }

    @RequestMapping(value = "/plan_date", method = RequestMethod.GET)
    public JsonResult getPlanDates(@RequestHeader(value = "token") String token,
                                   @RequestParam(value = "id") Integer projectId) {
        ProjectDateForm projectDateForm = projectScheduleService.getPlanDates(token, projectId);
        return JsonResult.renderSuccess(projectDateForm);
    }


    @RequestMapping(value = "/start_date", method = RequestMethod.POST)
    public JsonResult updateStartDate(@RequestHeader(value = "token") String token,
                                        @RequestParam(value = "id") Integer id,
                                        @RequestParam(value = "date") String date) {

        boolean flag = projectScheduleService.updateStartDate(token, id, date);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail();
        }
    }

    @RequestMapping(value = "/plan_date", method = RequestMethod.POST)
    public ResponseData updatePlanDate(@RequestHeader(value = "token") String token,
                                       @RequestParam(value = "id") Integer id,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "type") String type) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectScheduleService.updatePlanDate(token, id, date, type);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/actual_date", method = RequestMethod.POST)
    public ResponseData updateActualDate(@RequestHeader(value = "token") String token,
                                         @RequestParam(value = "id") Integer id,
                                         @RequestParam(value = "date") String date,
                                         @RequestParam(value = "type") String type) {
        ResponseData responseData = new ResponseData();
        boolean flag = projectScheduleService.updateActualDate(token, id, date, type);
        if (flag) {
            responseData.setSuccessData(null);
        } else {
            responseData.setFail(ErrorCode.DEFAULT, "失败");
        }
        return responseData;
    }

}
