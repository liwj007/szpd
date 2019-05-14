package com.liwj.szpd.controller;

import com.liwj.szpd.service.ProjectScheduleService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.ResponseData;
import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Autowired
    private ProjectScheduleService projectScheduleService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getScheduleInfo(@RequestParam(value = "token") String token,
                                      @RequestParam(value = "id") Integer projectId) {

        ProjectScheduleVO vo = projectScheduleService.getScheduleInfo(token, projectId);
        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/step_info", method = RequestMethod.GET)
    public JsonResult getScheduleStepInfo(@RequestParam(value = "token") String token,
                                          @RequestParam(value = "id") Integer scheduleId,
                                          @RequestParam(value = "step") Integer step) {

        ScheduleItemVO vo = projectScheduleService.getScheduleStepInfo(token, scheduleId, step);
        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/update_step", method = RequestMethod.POST)
    public JsonResult updateStep(@RequestParam(value = "token") String token,
                                 @RequestParam(value = "id") Integer scheduleId,
                                 @RequestParam(value = "step") Integer step,
                                 @RequestParam(value = "planDate") String planDate,
                                 @RequestParam(value = "actualDate") String actualDate,
                                 @RequestParam(value = "files") List<String> files) {
        if (planDate != null && "null".equals(planDate))
            planDate = null;
        if (actualDate != null && "null".equals(actualDate))
            actualDate = null;
        boolean flag = projectScheduleService.updateStep(token, scheduleId, step, planDate, actualDate, files);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail();
        }
    }


    @RequestMapping(value = "/start_date", method = RequestMethod.POST)
    public JsonResult updateStartDate(@RequestParam(value = "token") String token,
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
    public ResponseData updatePlanDate(@RequestParam(value = "token") String token,
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
    public ResponseData updateActualDate(@RequestParam(value = "token") String token,
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

    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam(value = "token",required = false) String token,
                                   @RequestParam(value = "file") MultipartFile file) {
        String filePath = projectScheduleService.uploadFile(token, file);

        return JsonResult.renderSuccess(Constants.SUCCESS,filePath);
    }

    @RequestMapping(value = "/get_uploads", method = RequestMethod.GET)
    public ResponseData get_uploads(@RequestParam(value = "token") String token,
                                    @RequestParam(value = "pid") Integer projectID) {
        ResponseData responseData = new ResponseData();
        List<String> res = projectScheduleService.getUploads(projectID);
        return responseData.setSuccessData(res);
    }
}
